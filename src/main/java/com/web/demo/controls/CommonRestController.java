package com.web.demo.controls;

import com.web.demo.read.csv.ReadCSVProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("common")
public class CommonRestController {

    @Value("${user.address.name}")
    private String userName;

    private ReadCSVProperties readCSVProperties;

    @Autowired
    public void setReadCSVProperties(ReadCSVProperties readCSVProperties) {
        this.readCSVProperties = readCSVProperties;
    }

    @GetMapping("/userDetails")
    public String userDetails() {
        System.out.println("CommonRestController userDetails===" + userName
                +"====readCSVProperties==="+readCSVProperties);
        return userName;
    }
}
