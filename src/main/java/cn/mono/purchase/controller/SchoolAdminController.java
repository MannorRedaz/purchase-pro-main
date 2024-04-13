package cn.mono.purchase.controller;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.PreAdmin;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.pojo.ApplyBoard;
import cn.mono.purchase.pojo.SchoolAdministrator;

import cn.mono.purchase.service.SchoolAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author nihao
 * @time 2021/4/14
 */

@RestController
@RequestMapping("schoolAdmin")

public class SchoolAdminController {
    @Autowired
    SchoolAdminService schoolAdminService;
    // 删除入围公告
    @RequestMapping(value = "deleteApplyBoard")
    public Message deleteApplyBoard(int   id) {
        return schoolAdminService.deleteApplyBoard( id);
    }
    // 添加入围公告
    @RequestMapping(value = "addApplyBoard", method = RequestMethod.POST)
    public Message addApplyBoard(@RequestBody ApplyBoard ab) {
        return schoolAdminService.addApplyBoard( ab);
    }

    // 供应商申请结果接口
    @RequestMapping(value = "infoUpdate", method = RequestMethod.POST)
    public Message infoUpdate(@RequestBody SchoolAdministrator school_administrator) {
        return schoolAdminService.infoUpdate(school_administrator);
    }


    //通过学院名查询
    @RequestMapping(value = "SearchByAcademyName")
    public Message SearchByAcademyName(String name) {
        return schoolAdminService.SearchByAcademyName(name);
    }
    //添加学院管理员
    @RequestMapping(value = "addPresidentAdmin",method = RequestMethod.POST)
    public Message addPresidentAdmin(@RequestBody PreAdmin preAdmin) {
        return schoolAdminService.addPresidentAdmin(preAdmin);
    }

    //重置密码
    @RequestMapping(value = "resetPresidentPwd")
    public Message resetPresidentPwd(int id) {
        return schoolAdminService.resetPresidentPwd(id);
    }
    //删除密码
    @RequestMapping(value = "deletePresident")
    public Message deletePresident(int id) {
        return schoolAdminService.deletePresident(id);
    }
    //通过学院名查询实验室管理员
    @RequestMapping(value = "searchPurchaserByAcademyName")
    public Message searchPurchaserByAcademyName(String name) {
        return schoolAdminService.searchPurchaserByAcademyName(name);
    }
    //添加学院管理员
    @RequestMapping(value = "addPurchaserAdmin",method = RequestMethod.POST)
    public Message addPurchaserAdmin(@RequestBody  PreAdmin preAdmin) {
        return schoolAdminService.addPurchaserAdmin(preAdmin);
    }
    //重置密码
    @RequestMapping(value = "resetPurchaserPwd")
    public Message resetPurchaserPwd(int id) {
        return schoolAdminService.resetPurchaserPwd(id);
    }
    //删除密码
    @RequestMapping(value = "deletePurchaser")
    public Message deletePurchaser(int id) {
        return schoolAdminService.deletePurchaser(id);
    }
    //通过学院名查询
    @RequestMapping(value = "getSupApplyList")
    public Message getSupApplyList(String name) {
        return schoolAdminService.getSupApplyList(name);
    }
    //获取供应商列表
    @RequestMapping(value = "getSupplierList")
    public Message getSupplierList() {
        return schoolAdminService.getSupplierList();
        //return null;

    }
    //设置同意或者不同意申请
    @RequestMapping(value = "supplyApprove")
    public Message supplyApprove(int id) {
        return schoolAdminService.supplyApprove(id);
    }
    //获取已经通过学院的采购申请列表
    @RequestMapping(value = "getPurchaseList",method = RequestMethod.POST)
    public Message getPurchaseList() {
        return schoolAdminService.getPurchaseList();
    }
    //获取已经通过学院的采购申请列表
    @RequestMapping(value = "getPurchaseList1",method = RequestMethod.POST)
    public Message getPurchaseList1() {
        return schoolAdminService.getPurchaseList1();
    }
    //同意其采购申请
    @RequestMapping(value = "purchaseApprove")
    public Message purchaseApprove(int id) {
        return schoolAdminService.purchaseApprove(id);
    }
    //学院 年度 查询
    @RequestMapping(value = "selectDetail",method = RequestMethod.POST)
    public Message selectDetail(@RequestBody Selecte selecte) {
        return schoolAdminService.selectDetail(selecte);
    }
}
