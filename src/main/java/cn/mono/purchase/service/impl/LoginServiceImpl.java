package cn.mono.purchase.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.mono.purchase.dto.Login;
import cn.mono.purchase.dto.LoginRes;
import cn.mono.purchase.dto.Message;
import cn.mono.purchase.mapper.*;
import cn.mono.purchase.pojo.*;

import cn.mono.purchase.service.LoginService;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static cn.mono.purchase.constants.Constants.*;

/**
 * @author nihao
 * @time 2021/3/13
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    SupplierMapper supplierMapper;
    @Autowired
    PresidentMapper presidentMapper;
    @Autowired
    School_administratorMapper schoolAdministratorMapper;
    @Autowired
    SupperMapper supperMapper;
    @Autowired
    PurchaserMapper purchaserMapper;
    @Resource
    private RedisTemplate<String,String> redisTemplate;


    Message message= new Message();

    @Override
    public Message supplierLogin(Login supplier) {
        message.clear();
        if (StrUtil.isEmpty(supplier.getName())||StrUtil.isEmpty(supplier.getPwd())){
            message.setSuccess(false);
            message.setMsg(PASSWORD_OR_USERNAME_NOTEMPTY);
            return message;
        }
        Supplier supplier1 = supplierMapper.selectByName(supplier.getName());

        if (ObjectUtil.isEmpty(supplier1)){
            message.setSuccess(false);
            message.setMsg(ACCOUNT_NOT_EXIST);
            return message;
        }
        if (supplier1.getPwd().equals(supplier.getPwd())){

            List<Supplier> suppliers = new ArrayList<>();
            suppliers.add(supplier1);

            String token = createToken();
            LoginRes<List<Supplier>> loginRes = new LoginRes<>(token);

            redisTemplate.opsForValue().set(ADMIN_TOKEN_PREFIX+token, JSON.toJSONString(supplier1), TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
            loginRes.setData(suppliers);

            List<LoginRes<List<Supplier>>> data = new ArrayList<>();
            data.add(loginRes);
            message.setDate(data);
            message.setSuccess(true);
            message.setMsg("恭喜你，登陆成功");
            message.setP(1);
        }else {
            message.setSuccess(false);
            message.setMsg("密码或验证码不正确");
        }
        return message;
    }



    @Override
    public Message presidentLogin(Login president) {
        message.clear();
        President supplier1 = presidentMapper.selectByName(president.getName());
        List<President> list = new ArrayList<>();


        if (ObjectUtil.isEmpty(supplier1)){
            message.setSuccess(false);
            message.setMsg("该账号不存在");
            return message;
        }
        if (supplier1.getPwd().equals(president.getPwd())){
            list.add(supplier1);
            message.setDate(list);
            message.setSuccess(true);
            message.setMsg("恭喜你，登陆成功");
            message.setP(3);
        }
        else {
            message.setSuccess(false);
            message.setMsg("密码或验证码不正确");
        }
        return message;
    }

    /**
     * 学校管理员
     */
    @Override
    public Message schoolAdministratorLogin(Login school_administrator) {
        message.clear();
        SchoolAdministrator school_administrator1 = schoolAdministratorMapper.selectByName(school_administrator.getName());
        List<SchoolAdministrator> list = new ArrayList<>();
        if (school_administrator1==null){
            message.setSuccess(false);
            message.setMsg("该账号不存在");
            return message;
        }
        if (school_administrator1.getPwd().equals(school_administrator.getPwd())){
            list.add(school_administrator1);
            message.setDate(list);
            message.setSuccess(true);
            message.setMsg("恭喜你，登陆成功");
            message.setP(4);
        }else {
            message.setSuccess(false);

                message.setMsg("密码或验证码不正确");
        }
        return message;
    }


    /**
     * 学院管理员登录
     */
    @Override
    public Message purchaserLogin(Login purchaser) {

        message.clear();
        Purchaser purchaser1 = purchaserMapper.selectByName(purchaser.getName());
        List<Purchaser> list = new ArrayList<>();
        if (purchaser1==null){
            message.setSuccess(false);
            message.setMsg("该账号不存在");
            return message;
        }
        if (purchaser.getPwd().equals(purchaser1.getPwd())){
            list.add(purchaser1);
            message.setDate(list);
            message.setSuccess(true);
            message.setMsg("恭喜你，登陆成功");
            message.setP(2);
        }else {
            message.setSuccess(false);
            message.setMsg("密码或验证码不正确");
        }
        return message;
    }

    /**
     * 超级管理员登录
     */
    @Override
    public Message supperLogin(Login supper) {
        message.clear();
        Supper supper1 = supperMapper.selectByName(supper.getName());
        List<Supper> list = new ArrayList<>();
        if (supper1==null){
            message.setSuccess(false);
            message.setMsg("该账号不存在");
            return message;
        }
        if (supper.getPwd().equals(supper1.getPwd())){
            list.add(supper1);
            message.setDate(list);
            message.setSuccess(true);
            message.setMsg("恭喜你，登陆成功");
            message.setP(5);
        }else {
            message.setSuccess(false);
            message.setMsg("密码或验证码不正确");
        }
        return message;
    }


    @Override
    public Message administerLogin(Login login) {
        message.clear();
        
        if(StrUtil.isEmpty(login.getName())||StrUtil.isEmpty(login.getPwd())){
            message.setSuccess(false);
            message.setMsg("账号或密码不能为空");
            return message;
        }
        message= purchaserLogin(login);
        if (message.isSuccess()){
            return message;
        }else if (message.getMsg().equals("密码或验证码不正确")){
            return message;
        }
        message= presidentLogin(login);
        if (message.isSuccess()){
            return message;
        }else if (message.getMsg().equals("密码或验证码不正确")){
            return message;
        }
        message= supperLogin(login);
        if (message.isSuccess()){
            return message;
        }else if (message.getMsg().equals("密码或验证码不正确")){
            return message;
        }
        message= schoolAdministratorLogin(login);
        if (message.isSuccess()){
            return message;
        }else if (message.getMsg().equals("密码或验证码不正确")){
            return message;
        }
        return message;
    }
    String createToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
