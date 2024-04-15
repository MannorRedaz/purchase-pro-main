package cn.mono.purchase.controller;


import cn.mono.purchase.dto.Login;
import cn.mono.purchase.dto.Message;
import cn.mono.purchase.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author nihao
 * @time 2021/3/13
 */

@RestController
@RequestMapping("/")
@Tag(name = "认证")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping(value = "supplierLogin")
    public Message supplierLogin(@RequestBody Login login){
        return  loginService.supplierLogin(login);
    }

    @PostMapping(value = "administerLogin")
    public Message administerLogin(@RequestBody Login login){
        return loginService.administerLogin(login);
    }

    @GetMapping("/logout")
    public Message logout(@RequestHeader("Authorization") String token) {
        return loginService.logout(token);
    }

}
