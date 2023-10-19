package com.Products.Products.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class SomeController {

    @GetMapping("hello")
//    @PreAuthorize("hasAuthority('admin')")
    public String get(){
        return "I am get";
    }

}
