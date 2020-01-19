package com.serendipity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 王宇杰
 * @date 2020/1/14 20:20
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    public static void main(String[] args) {
        System.out.println("sb");
    }

}
