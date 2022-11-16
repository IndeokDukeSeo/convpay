package com.zerobvase.convpay;

import com.zerobvase.convpay.dto.PayCancelRequest;
import com.zerobvase.convpay.dto.PayCancelResponse;
import com.zerobvase.convpay.service.CardAdaptor;
import com.zerobvase.convpay.service.DiscountByConvenience;
import com.zerobvase.convpay.service.MoneyAdaptor;
import com.zerobvase.convpay.type.ConvenienceType;
import com.zerobvase.convpay.dto.PayRequest;
import com.zerobvase.convpay.dto.PayResponse;
import com.zerobvase.convpay.type.PayCancelResult;
import com.zerobvase.convpay.type.PayMethodType;
import com.zerobvase.convpay.type.PayResult;
import com.zerobvase.convpay.service.ConveniencePayService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

class ConveniencePayServiceTest {
    ConveniencePayService conveniencePayService = new ConveniencePayService(
            new HashSet<>(
                    Arrays.asList(new MoneyAdaptor(), new CardAdaptor())
            ),
            new DiscountByConvenience()
    );

    @Test
    void pay_success() {
        //given
        PayRequest payRequest = new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 50);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        Assertions.assertEquals(PayResult.SUCCESS, payResponse.getPayResult());
        Assertions.assertEquals(100, payResponse.getPaidAmount());

    }

    @Test
    void pay_fail() {
        //given
        PayRequest payRequest = new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 1000_001);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        Assertions.assertEquals(PayResult.FAIL, payResponse.getPayResult());
        Assertions.assertEquals(0, payResponse.getPaidAmount());

    }

    @Test
    void pay_cancel_success() {
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, ConvenienceType.G25, 1000);

        //when
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);

        //then
        Assertions.assertEquals(PayCancelResult.PAY_CANCEL_SUCCESS, payCancelResponse.getPayCancelResult());
        Assertions.assertEquals(1000, payCancelResponse.getPayCanceledAmount());

    }

    @Test
    void pay_cancel_fail() {
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, ConvenienceType.G25, 99);

        //when
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);

        //then
        Assertions.assertEquals(PayCancelResult.PAY_CANCEL_FAIL, payCancelResponse.getPayCancelResult());
        Assertions.assertEquals(0, payCancelResponse.getPayCanceledAmount());

    }
}