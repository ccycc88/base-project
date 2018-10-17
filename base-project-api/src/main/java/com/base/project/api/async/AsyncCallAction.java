package com.base.project.api.async;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class AsyncCallAction {

    @ApiOperation(value = "restful并发", notes = "使用Callable方式进行rest并发")
    @GetMapping(value = "/async_call")
    public Callable<String> getCall(){

        //2400
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {

                //do something
                return "call";
            }
        };
        return callable;
    }
}
