package com.zerobvase.convpay.service;

import com.zerobvase.convpay.dto.PayRequest;

public interface DiscountInterface{
    Integer getDiscountedAmount(PayRequest payRequest);



}
