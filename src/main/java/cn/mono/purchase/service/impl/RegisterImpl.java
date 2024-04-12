package cn.mono.purchase.service.impl;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.mapper.*;
import cn.mono.purchase.pojo.*;
import cn.mono.purchase.service.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nihao
 * @time 2021/3/18
 */
@Service
public class RegisterImpl implements Register {
    @Autowired
    SupplierMapper supplierMapper;
    @Autowired
    PresidentMapper presidentMapper;
    @Autowired
    School_administratorMapper school_administratorMapper;
    @Autowired
    SupperMapper supperMapper;
    @Autowired
    PurchaserMapper purchaserMapper;

    @Autowired
    MutiTableSelectMapper  mutiTableSelectMapper;
    Message message = new Message();

    @Override
    public Message administerCheck(String name) {
        
        String pwd = mutiTableSelectMapper.selectCheckByName(name);
        if (name==null){
            message.setSuccess(false);
            message.setMsg("用户名不能为空");
            return message;
        }
        if (pwd!=null){
            message.setSuccess(false);
            message.setMsg("该用户名不可用");
        }else {
            message.setSuccess(true);
            message.setMsg("该用户名可用");
        }
        return message;
    }

    @Override
    public Message supplierCheckUser(String name) {
        name = name.substring(0,name.length()-1);
        
        message.clear();
        if (name==null){
            message.setSuccess(false);
            message.setMsg("用户名不能为空");
            return message;
        }
         Supplier supplier =supplierMapper.selectByName(name);
        if (supplier!=null){
            message.setSuccess(false);
            message.setMsg("该用户名不可用");
        }else {
            message.setSuccess(true);
            message.setMsg("该用户名可用");
        }
        return message;
    }

    @Override
    public Message superCheckUser(String name) {
        message.clear();
        if (name==null){
            message.setSuccess(false);
            message.setMsg("用户名不能为空");
            return message;
        }

       Supper supper =supperMapper.selectByName(name);
        if (supper.getPwd()!=null){
            message.setSuccess(false);
            message.setMsg("该用户名不可用");
        }else {
            message.setSuccess(true);
            message.setMsg("该用户名可用");
        }
        return message;
    }

    @Override
    public Message schoolCheckUser(String name) {

        if (name==null){
            message.setSuccess(false);
            message.setMsg("用户名不能为空");
            return message;
        }

        SchoolAdministrator school_administrator =school_administratorMapper.selectByName(name);
        if (school_administrator.getPwd()!=null){
            message.setSuccess(false);
            message.setMsg("该用户名不可用");
        }else {
            message.setSuccess(true);
            message.setMsg("该用户名可用");
        }
        return message;
    }

    @Override
    public Message presidentCheckUser(String name) {
        if (name==null){
            message.setSuccess(false);
            message.setMsg("用户名不能为空");
            return message;
        }

       President president =presidentMapper.selectByName(name);
        if (president.getPwd()!=null){
            message.setSuccess(false);
            message.setMsg("该用户名不可用");
        }else {
            message.setSuccess(true);
            message.setMsg("该用户名可用");
        }
        return message;
    }

    @Override
    public Message supplierRegister(Supplier supplier) {
        message.clear();
        if (supplierMapper.selectByName(supplier.getName())!=null){
            message.setMsg("该用户名不可用");
            message.setSuccess(false);
            return message;
        }
        try {
            int i = supplierMapper.insert(supplier);message.setSuccess(true);
            message.setMsg("恭喜你注册成功");
        }
            catch (Exception e){

            message.setSuccess(false);
            message.setMsg("注册失败");
        }
        return message;
    }



    @Override
    public Message superRegister(Supper supper) {
        int i = supperMapper.insert(supper);
        if (i==1){
            message.setSuccess(true);
            message.setMsg("恭喜你注册成功");
        }else {
            message.setSuccess(false);
            message.setMsg("组测失败");
        }
        return message;
    }

    @Override
    public Message schoolRegister(SchoolAdministrator school_administrator) {
        int i = school_administratorMapper.insert(school_administrator);
        if (i==1){
            message.setSuccess(true);
            message.setMsg("恭喜你注册成功");
        }else {
            message.setSuccess(false);
            message.setMsg("组测失败");
        }
        return message;
    }

    @Override
    public Message presidentRegister(President president) {
        int i = presidentMapper.insert(president);
        if (i==1){
            message.setSuccess(true);
            message.setMsg("恭喜你注册成功");
        }else {
            message.setSuccess(false);
            message.setMsg("组测失败");
        }
        return message;
    }

    @Override
    public Message purchaserRegister(Purchaser purchaser) {
        int i = purchaserMapper.insert(purchaser);
        if (i==1){
            message.setSuccess(true);
            message.setMsg("恭喜你注册成功");
        }else {
            message.setSuccess(false);
            message.setMsg("组测失败");
        }
        return message;
    }
}
