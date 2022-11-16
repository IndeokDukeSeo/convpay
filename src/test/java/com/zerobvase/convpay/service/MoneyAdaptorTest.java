package com.zerobvase.convpay.service;

import com.zerobvase.convpay.type.MoneyUseCancelResult;
import com.zerobvase.convpay.type.MoneyUseResult;
import org.junit.jupiter.api.Test;

import static com.zerobvase.convpay.type.MoneyUseResult.USE_FAIL;
import static com.zerobvase.convpay.type.MoneyUseResult.USE_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

class MoneyAdaptorTest {
    MoneyAdaptor moneyAdaptor = new MoneyAdaptor();

    @Test
    void money_use_fail() {
        //given
        Integer payAmount = 1000_001;
        //whe
        MoneyUseResult moneyUseResult = moneyAdaptor.use(payAmount);
        //then
        assertEquals(USE_FAIL, moneyUseResult);

    }

    @Test
    void money_use_success() {
        //given
        Integer payAmount = 1000_000;
        //whe
        MoneyUseResult moneyUseResult = moneyAdaptor.use(payAmount);
        //then
        assertEquals(USE_SUCCESS, moneyUseResult);

    }

    @Test
    void money_use_cancel_success() {
        //given
        Integer payCancelAmount = 100;
        //when
        MoneyUseCancelResult moneyUseCancelResult = moneyAdaptor.useCancel((payCancelAmount));
        //then
        assertEquals(MoneyUseCancelResult.MONEY_USE_CANCEL_SUCCESS, moneyUseCancelResult);
    }

    @Test
    void money_use_cancel_fail() {
        //given
        Integer payCancelAmount = 99;
        //when
        MoneyUseCancelResult moneyUseCancelResult = moneyAdaptor.useCancel((payCancelAmount));
        //then
        assertEquals(MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL, moneyUseCancelResult);
    }

}