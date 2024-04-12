package cn.mono.purchase.controller;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nihao
 * @time 2021/5/15
 */

@RestController
@RequestMapping("notify")
@CrossOrigin(origins = "*")
public class NotifyController {
    @Autowired
    NotifyService notifyService;
    Message message;

    @RequestMapping(value = "addResultBoard")
    @ResponseBody
    public Message addResultBoard(int id){
        return null;
    }

}
