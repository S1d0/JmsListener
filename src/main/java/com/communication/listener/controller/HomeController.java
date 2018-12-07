package com.communication.listener.controller;

import com.communication.listener.model.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/getMsg")
    public Payment getNewest() {
        return null;
    }
}
