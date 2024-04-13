package cn.mono.purchase.controller;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.pojo.*;
import cn.mono.purchase.service.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author nihao
 * @time 2021/3/20
 */


@RestController
public class RegisterController {
    @Autowired
    Register register;


    /**
     * 超级用户的检测
     * @param name
     * @return
     */
    @RequestMapping(value = "administerCheck",method = RequestMethod.POST)
    @ResponseBody
    public Message administerCheck(@RequestBody String name){
        return register.administerCheck(name);
    }

    /**
     * 对将要注册的供应商用户名进行检测
     * 传入一个Login对象
     * 返回一个message
     * @param name
     * @return
     */
    @RequestMapping(value = "supplierCheck",method = RequestMethod.POST)
    @ResponseBody
    public Message supplierCheck(@RequestBody String name){
        return register.supplierCheckUser(name);
    }




    /**
     * 传入一个supplier
     * 返回一个message
     * @param supplier
     * @return
     */
    @RequestMapping(value = "supplierRegister",method = RequestMethod.POST)
    @ResponseBody
    public Message supplierRegister(@RequestBody Supplier supplier)  {



        return register.supplierRegister(supplier);
       // return null;
    }

    @RequestMapping(value = "purchaserRegister",method = RequestMethod.POST)
    @ResponseBody
    public Message purchaserRegister(@RequestBody Purchaser purchaser)  {
        return register.purchaserRegister(purchaser);
    }
    @RequestMapping(value = "presidentRegister",method = RequestMethod.POST)
    @ResponseBody
    public Message presidentRegister(@RequestBody President president)  {
        return register.presidentRegister(president);
    }
    @RequestMapping(value = "schoolRegister",method = RequestMethod.POST)
    @ResponseBody
    public Message schoolRegister(@RequestBody SchoolAdministrator school_administrator)  {
        return register.schoolRegister(school_administrator);
    }
    @RequestMapping(value = "supperRegister",method = RequestMethod.POST)
    @ResponseBody
    public Message supperRegister(@RequestBody Supper supper)  {
        return register.superRegister(supper);
    }

}
