package com.zerobvase.convpay.service;

import com.zerobvase.convpay.type.CancelPaymentResult;
import com.zerobvase.convpay.type.PayMethodType;
import com.zerobvase.convpay.type.PaymentResult;

public interface PaymentInterface {
    PayMethodType getPayMethodType();
    PaymentResult payment(Integer payAmount);
    CancelPaymentResult cancelPayment(Integer cancelAmount);


}
