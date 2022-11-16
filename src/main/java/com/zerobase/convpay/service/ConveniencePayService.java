package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.type.*;
import com.zerobvase.convpay.dto.*;
import com.zerobvase.convpay.type.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConveniencePayService {
    private final Map<PayMethodType, PaymentInterface> paymentInterfaceMap = new HashMap<>();
    private final DiscountInterface discountInterface ;

    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet, DiscountInterface discountInterface) {
        paymentInterfaceSet.forEach(
                paymentInterface -> paymentInterfaceMap.put(
                        paymentInterface.getPayMethodType(),
                        paymentInterface
                )
        );
        this.discountInterface = discountInterface;
    }


    public PayResponse pay(PayRequest payRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payRequest.getPayMethodType());
        Integer discountedAmount = discountInterface.getDiscountedAmount(payRequest);

        PaymentResult payment = paymentInterface.payment(payRequest.getPayAmount());


////        CardUseResult cardUseResult;
////        MoneyUseResult moneyUseResult;
//
//        if (payRequest.getPayMethodType() == PayMethodType.CARD) {
//
//            cardAdaptor.authorization();
//            cardAdaptor.approval();
//            cardUseResult = cardAdaptor.capture(payRequest.getPayAmount());
//
//        } else {
//            moneyUseResult =
//                    moneyAdaptor.use(payRequest.getPayAmount());
//
//        }
//        //성공케이스를 가장 마지막에!('fail fast' 를 통해 성공케이스 일관성 유지)
//
        if (payment == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }

        return new PayResponse(PayResult.SUCCESS, 100);
    }
//        if (moneyUseResult == MoneyUseResult.USE_FAIL ||
//                CardUseResult == CardUseResult.USE_FAIL) {
//            return new PayResponse(PayResult.FAIL, 0);
//        }
//
//        return new PayResponse(PayResult.SUCCESS, 100);
//    }


    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payCancelRequest.getPayMethodType());


        CancelPaymentResult cancelPaymentResult =
                paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());

        if (cancelPaymentResult == cancelPaymentResult.CANCEL_PAYMENT_FAIL) {
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL, 0);
        }

        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS, payCancelRequest.getPayCancelAmount());
    }


}
