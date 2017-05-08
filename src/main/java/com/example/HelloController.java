package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/test")
    public String testString() {
        return "Testing";
    }


    @GetMapping("/about")
    public String about() {
        return "About - Wakeupsid";
    }


    @GetMapping("/contact")
    public String contact() {
        return "Contact - wakeupsid9 :)";
    }

}