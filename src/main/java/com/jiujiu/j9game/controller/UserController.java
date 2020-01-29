package com.jiujiu.j9game.controller;


import com.jiujiu.j9game.model.LoginResult;
import com.jiujiu.j9game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/login")
    public LoginResult login(String jiujiuToken){
        return userService.checkLogin(jiujiuToken);
    }

}
