/**
 * Created by saikiranmothe on 5/2/17.
 */


package com.example.controller;


import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.lang.String;


@RestController
public class EndpointsController {


    @GetMapping("/math/pi")
    public String PIvalue()
    {
        return  "3.141592653589793";
    }

}


