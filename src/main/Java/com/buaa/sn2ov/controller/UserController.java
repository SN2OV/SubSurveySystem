package com.buaa.sn2ov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by SN2OV on 2016/3/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/1")
    public String toIndex(){
        return "index";
    }
}
