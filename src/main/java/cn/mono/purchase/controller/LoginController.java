package cn.mono.purchase.controller;


import cn.mono.purchase.dto.Login;
import cn.mono.purchase.dto.Message;
import cn.mono.purchase.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author nihao
 * @time 2021/3/13
 */

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    LoginService loginService;
    Message message;

    @PostMapping(value = "supplierLogin")
    public Message supplierLogin(@RequestBody Login login){
        return  loginService.supplierLogin(login);
    }

    @PostMapping(value = "administerLogin")
    public Message administerLogin(@RequestBody Login login){
        return loginService.administerLogin(login);
    }

}
