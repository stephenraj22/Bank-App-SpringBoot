package com.progobot.practicespringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Greet {
    //GetMapping will have below RequestMapping inside it
    //@RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String sayHello(){
        return "Hello user";
    }
}
