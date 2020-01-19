package com.serendipity.controller;

import com.serendipity.service.IRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private IRedis IRedis;

    @GetMapping("/set")
    public String setMsg(@RequestParam(value = "key") String key, @RequestParam(value = "msg") String msg) throws Exception{
        return IRedis.set(key, msg)?"true":"false";
    }

    @GetMapping("/get")
    public String getMsg(@RequestParam(value = "key") String key)throws Exception {
        return IRedis.get(key).toString();
    }

}