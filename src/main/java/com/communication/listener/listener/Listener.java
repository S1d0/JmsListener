package com.communication.listener.listener;

import com.communication.listener.model.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Listener {
    Logger logger = LoggerFactory.getLogger(Listener.class);
    static int nr = 0;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @JmsListener(destination = "myQueue", containerFactory = "myFactory")
    public void readFromQueue(String paymentJson) {
        logger.info("json payment : {}",paymentJson);
        Payment p = getPayment(paymentJson);
        logger.info("Retrieved object: {}",p);
        jmsMessagingTemplate.receive("myQueue");
    }

    private String getJson(Object payment) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(payment);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
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
