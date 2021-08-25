package com.web.demo.controls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("common")
public class CommonRestController {

    @Value("${user.address.name}")
    private String userName;

    @GetMapping("/userDetails")
    public String userDetails() {
        System.out.println("CommonRestController userDetails===" + userName);
        return userName;
    }
}
