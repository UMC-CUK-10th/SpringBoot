package com.example.umc10th.domain.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    // Query Parameter
    @PostMapping("/query-parameter")
    public String exception(
            @RequestParam String queryParameter
    ){
        return memberService
    }

}
