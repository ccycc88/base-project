package com.base.project.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorAction {

    @GetMapping(value = "/error1")
    public void error(){

        int i = 1/0;
    }
}
