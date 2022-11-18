package com.zerobvase.convpay.service;

import com.zerobvase.convpay.dto.PayRequest;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class DiscountByPayMethod implements DiscountInterface {

    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {
        System.out.println("DiscountByMethod called");
        switch (payRequest.getPayMethodType()) {

            case MONEY:
                return payRequest.getPayAmount() * 7 / 10;
            case CARD:
                return payRequest.getPayAmount();
        }
        return payRequest.getPayAmount();
    }
}
