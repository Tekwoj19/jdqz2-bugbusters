package com.infoshare.bug_busters.random;

import com.infoshare.bug_busters.payment.PaymentData;
import com.infoshare.bug_busters.registration.UserData;
import com.infoshare.bug_busters.shipping.ShippingData;

public class DdtDataGenerator {

    private UserData userData;
    private PaymentData paymentData;
    private ShippingData shippingData;

    public DdtDataGenerator(UserData userData, PaymentData paymentData, ShippingData shippingData) {
        this.userData = userData;
        this.paymentData = paymentData;
        this.shippingData = shippingData;
    }    public UserData getUserData() {
        return userData;
    }    public PaymentData getPaymentData() {
        return paymentData;
    }    public ShippingData getShippingData() {
        return shippingData;
    }



}


