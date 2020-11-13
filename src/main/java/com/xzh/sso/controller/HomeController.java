package com.xzh.sso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author 向振华
 * @date 2020/09/27 16:11
 */
@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    public String home() {
        return "welcome home";
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo success";
    }
}
