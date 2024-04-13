package cn.mono.purchase.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.mono.purchase.dto.Login;
import cn.mono.purchase.dto.LoginRes;
import cn.mono.purchase.dto.Message;
import cn.mono.purchase.exception.ServiceException;
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
import java.util.function.Function;

import static cn.mono.purchase.constants.Constants.*;
import static cn.mono.purchase.exception.ErrorCodeEnum.AUTH_LOGIN_BAD_CREDENTIALS;
import static cn.mono.purchase.exception.ErrorCodeEnum.AUTH_LOGIN_USERNAME_OR_PASSWORD_EMPTY;

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
    private RedisTemplate<String, String> redisTemplate;


    Message message = new Message();

    @Override
    public Message supplierLogin(Login supplier) {
        message.clear();
        Supplier supplier1 = supplierMapper.selectByName(supplier.getName());

        if (ObjectUtil.isEmpty(supplier1)) {
            throw new ServiceException(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!supplier1.getPwd().equals(supplier.getPwd())) {
            throw new ServiceException(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier1);

        String token = createToken();
        LoginRes<List<Supplier>> loginRes = new LoginRes<>(token);

        redisTemplate.opsForValue().set(ADMIN_TOKEN_PREFIX + token, JSON.toJSONString(supplier1), TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
        loginRes.setData(suppliers);

        List<LoginRes<List<Supplier>>> data = new ArrayList<>();
        data.add(loginRes);
        message.setDate(data);
        message.setSuccess(true);
        message.setMsg(SUCCESS_LOGIN);
        message.setP(1);

        return message;
    }


    @Override
    public Message presidentLogin(Login president) {
        message.clear();
        President president1 = presidentMapper.selectByName(president.getName());
        List<President> list = new ArrayList<>();


        if (ObjectUtil.isEmpty(president1)) {
            throw new ServiceException(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!president1.getPwd().equals(president.getPwd())) {
            throw new ServiceException(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        list.add(president1);

        String token = createToken();
        LoginRes<List<President>> loginRes = new LoginRes<>(token);
        loginRes.setData(list);
        redisTemplate.opsForValue().set(ADMIN_TOKEN_PREFIX + token, JSON.toJSONString(president1), TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
        List<LoginRes<List<President>>> result = new ArrayList<>();
        result.add(loginRes);

        message.setDate(result);
        message.setSuccess(true);
        message.setMsg(SUCCESS_LOGIN);
        message.setP(3);
        return message;
    }

    /**
     * 学校管理员
     */
    @Override
    public Message schoolAdministratorLogin(Login schoolAdministrator) {
        message.clear();
        SchoolAdministrator schoolAdministrator1 = schoolAdministratorMapper.selectByName(schoolAdministrator.getName());
        List<SchoolAdministrator> list = new ArrayList<>();
        if (ObjectUtil.isEmpty(schoolAdministrator1)) {
            throw new ServiceException(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!schoolAdministrator1.getPwd().equals(schoolAdministrator.getPwd())) {
            throw new ServiceException(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        list.add(schoolAdministrator1);
        String token = createToken();
        LoginRes<List<SchoolAdministrator>> loginRes = new LoginRes<>(token);
        loginRes.setData(list);
        redisTemplate.opsForValue().set(ADMIN_TOKEN_PREFIX + token, JSON.toJSONString(schoolAdministrator1), TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
        List<LoginRes<List<SchoolAdministrator>>> result = new ArrayList<>();
        result.add(loginRes);
        message.setDate(result);
        message.setSuccess(true);
        message.setMsg(SUCCESS_LOGIN);
        message.setP(4);
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
        if (ObjectUtil.isEmpty(purchaser1)) {
            throw new ServiceException(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!purchaser.getPwd().equals(purchaser1.getPwd())) {
            throw new ServiceException(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        list.add(purchaser1);
        String token = createToken();
        LoginRes<List<Purchaser>> loginRes = new LoginRes<>(token);
        loginRes.setData(list);
        redisTemplate.opsForValue().set(ADMIN_TOKEN_PREFIX + token, JSON.toJSONString(purchaser1), TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
        List<LoginRes<List<Purchaser>>> result = new ArrayList<>();
        result.add(loginRes);
        message.setDate(result);
        message.setSuccess(true);
        message.setMsg(SUCCESS_LOGIN);
        message.setP(2);
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
        if (ObjectUtil.isEmpty(supper1)) {
            throw new ServiceException(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!supper.getPwd().equals(supper1.getPwd())) {
            throw new ServiceException(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        list.add(supper1);
        String token = createToken();
        LoginRes<List<Supper>> loginRes = new LoginRes<>(token);
        loginRes.setData(list);
        redisTemplate.opsForValue().set(ADMIN_TOKEN_PREFIX + token, JSON.toJSONString(supper1), TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
        List<LoginRes<List<Supper>>> result = new ArrayList<>();
        result.add(loginRes);
        message.setDate(result);
        message.setSuccess(true);
        message.setMsg(SUCCESS_LOGIN);
        message.setP(5);
        return message;
    }

    List<Function<Login, Message>> loginMethods = List.of(
            this::purchaserLogin,
            this::presidentLogin,
            this::supperLogin,
            this::schoolAdministratorLogin
    );


    @Override
    public Message administerLogin(Login login) {
        for (Function<Login, Message> loginMethod : loginMethods) {
            Message message = loginMethod.apply(login);
            if (message.isSuccess()) {
                return message;
            }
        }
        return Message.error(AUTH_LOGIN_BAD_CREDENTIALS);
    }

    @Override
    public Message logout(String token) {
        redisTemplate.delete(ADMIN_TOKEN_PREFIX + token);
        return new Message(Boolean.TRUE, "退出成功");
    }

    String createToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
