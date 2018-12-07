package com.communication.listener.listener;

import com.communication.listener.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class ListenerTest {

    @Test
    public void jsonToObject() {
        String json = "{\"from\":\"queen\",\"to\":\"ek\",\"amount\":\"12\"}";
        Payment p = getPayment(json);
        System.out.println(p.getClass());
    }

    private Payment getPayment(String jsonPayment) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonPayment, Payment.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Payment();
    }
}