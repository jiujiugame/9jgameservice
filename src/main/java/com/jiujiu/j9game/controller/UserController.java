package com.jiujiu.j9game.controller;


import com.jiujiu.j9game.model.LoginResult;
import com.jiujiu.j9game.model.JsonResult;
import com.jiujiu.j9game.service.GMService;
import com.jiujiu.j9game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GMService gmService;

    @RequestMapping("/user/login")
    public LoginResult login(String jiujiuToken){
        return userService.checkLogin(jiujiuToken);
    }


    @PostMapping("/deposit")
    public JsonResult deposit(@RequestParam("productId") Integer productId,
                              @RequestParam("gameRoleId") Long uid,
                              @RequestParam("orderId") String orderId,
                              @RequestParam("gameZoneId") int gameZoneId) {
        JsonResult result = new JsonResult();
        result.setOrderId(orderId);
        try {
            String rs = gmService.deposit(uid, productId, gameZoneId - 1);
            result.setMsg(rs);
            if (!"SUCCESS".equals(rs)) {
                result.setCode(-1);
            }
        } catch (Exception e) {
            result.setCode(-1);
            result.setMsg(e.getMessage());
        }
        return result;
    }

}
