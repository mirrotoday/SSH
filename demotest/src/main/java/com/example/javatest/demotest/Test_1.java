package com.example.javatest.demotest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class Test_1 {
    @GetMapping(value = "/")
    public String getIndex(){
        return "index";
    }

    @GetMapping(value = "/test")
    public String getTest(){
        return "test";
    }
}
