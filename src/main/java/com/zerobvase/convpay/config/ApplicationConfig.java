package com.zerobvase.convpay.config;

import com.zerobvase.convpay.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class ApplicationConfig {

    @Bean
    public ConveniencePayService conveniencePayService() {
        return new ConveniencePayService(
                new HashSet<>(
                        Arrays.asList(moneyAdaptor(), cardAdaptor())
                ),
                discountByConvenience()
        );
    }
    @Bean
    private static CardAdaptor cardAdaptor() {
        return new CardAdaptor();
    }

    @Bean
    private static MoneyAdaptor moneyAdaptor() {
        return new MoneyAdaptor();
    }

    @Bean
    public DiscountByConvenience discountByConvenience() {
        return new DiscountByConvenience();
    }
}
