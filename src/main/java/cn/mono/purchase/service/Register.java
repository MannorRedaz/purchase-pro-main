package cn.mono.purchase.service;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.pojo.*;

/**
 * @author nihao
 * @time 2021/3/18
 */
public interface Register {

    //检测用户名是否被占用
     Message administerCheck(String name);
      Message supplierCheckUser(String name);
    Message superCheckUser(String name);
    Message schoolCheckUser(String name);
    Message presidentCheckUser(String name);
    //1.注册
    public Message supplierRegister(Supplier supplier);
    public Message superRegister(Supper supper);
    public Message schoolRegister(SchoolAdministrator school_administrator);
    Message presidentRegister(President president);
    Message purchaserRegister(Purchaser purchaser);
}
