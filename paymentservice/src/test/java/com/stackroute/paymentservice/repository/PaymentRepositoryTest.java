package com.stackroute.paymentservice.repository;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.stackroute.paymentservice.model.PaymentServiceModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


@DataMongoTest
public class PaymentRepositoryTest {

    @Autowired
    private PaymentServiceRepository paymentRepository;
    private PaymentServiceModel payment;

    @BeforeAll
    public void setUp() throws Exception {

        payment = new PaymentServiceModel();
        payment.setCardNo("123456789");
        payment.setCvv("789");
        payment.setExpDate("08/21");

    }
    @Test
    public void ceatePaymentTest() {
        paymentRepository.insert(payment);
        PaymentServiceModel actualPayment = paymentRepository.findById("123456789").get();
        assertEquals(actualPayment.getCardNo(),payment.getCardNo());
    }
    @AfterAll
    public void tearDown() throws Exception {

        paymentRepository.deleteAll();
    }

}