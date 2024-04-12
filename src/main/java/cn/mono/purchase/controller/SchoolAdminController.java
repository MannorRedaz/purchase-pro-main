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
@CrossOrigin(origins = "*")
public class SchoolAdminController {
    @Autowired
    SchoolAdminService schoolAdminService;
    // 删除入围公告
    @RequestMapping(value = "deleteApplyBoard ")
    @ResponseBody
    public Message deleteApplyBoard(int   id) {
        System.out.println(id);
        return schoolAdminService.deleteApplyBoard( id);
    }
    // 添加入围公告
    @RequestMapping(value = "addApplyBoard ", method = RequestMethod.POST)
    @ResponseBody
    public Message addApplyBoard(@RequestBody ApplyBoard ab) {
        return schoolAdminService.addApplyBoard( ab);
    }

    // 供应商申请结果接口
    @RequestMapping(value = "infoUpdate ", method = RequestMethod.POST)
    @ResponseBody
    public Message infoUpdate(@RequestBody SchoolAdministrator school_administrator) {
        return schoolAdminService.infoUpdate(school_administrator);
    }


    //通过学院名查询
    @RequestMapping(value = "SearchByAcademyName")
    @ResponseBody
    public Message SearchByAcademyName(String name) {
        return schoolAdminService.SearchByAcademyName(name);
    }
    //添加学院管理员
    @RequestMapping(value = "addPresidentAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Message addPresidentAdmin(@RequestBody PreAdmin preAdmin) {
        return schoolAdminService.addPresidentAdmin(preAdmin);
    }

    //重置密码
    @RequestMapping(value = "resetPresidentPwd")
    @ResponseBody
    public Message resetPresidentPwd(int id) {
        return schoolAdminService.resetPresidentPwd(id);
    }
    //删除密码
    @RequestMapping(value = "deletePresident")
    @ResponseBody
    public Message deletePresident(int id) {
        return schoolAdminService.deletePresident(id);
    }



    //通过学院名查询实验室管理员
    @RequestMapping(value = "searchPurchaserByAcademyName")
    @ResponseBody
    public Message searchPurchaserByAcademyName(String name) {
        return schoolAdminService.searchPurchaserByAcademyName(name);
    }
    //添加学院管理员
    @RequestMapping(value = "addPurchaserAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Message addPurchaserAdmin(@RequestBody  PreAdmin preAdmin) {
        return schoolAdminService.addPurchaserAdmin(preAdmin);
    }

    //重置密码
    @RequestMapping(value = "resetPurchaserPwd")
    @ResponseBody
    public Message resetPurchaserPwd(int id) {
        return schoolAdminService.resetPurchaserPwd(id);
    }
    //删除密码
    @RequestMapping(value = "deletePurchaser")
    @ResponseBody
    public Message deletePurchaser(int id) {
        return schoolAdminService.deletePurchaser(id);
    }





    //通过学院名查询
    @RequestMapping(value = "getSupApplyList")
    @ResponseBody
    public Message getSupApplyList(String name) {
        return schoolAdminService.getSupApplyList(name);
        //return null;

    }

    //获取供应商列表
    @RequestMapping(value = "getSupplierList")
    @ResponseBody
    public Message getSupplierList() {
        return schoolAdminService.getSupplierList();
        //return null;

    }

    //设置同意或者不同意申请
    @RequestMapping(value = "supplyApprove")
    @ResponseBody
    public Message supplyApprove(int id) {
        return schoolAdminService.supplyApprove(id);
        //return null;

    }

    //获取已经通过学院的采购申请列表
    @RequestMapping(value = "getPurchaseList",method = RequestMethod.POST)
    @ResponseBody
    public Message getPurchaseList() {
        return schoolAdminService.getPurchaseList();
        //return null;

    }
    //获取已经通过学院的采购申请列表
    @RequestMapping(value = "getPurchaseList1",method = RequestMethod.POST)
    @ResponseBody
    public Message getPurchaseList1() {
        return schoolAdminService.getPurchaseList1();
        //return null;

    }
    //同意其采购申请
    @RequestMapping(value = "purchaseApprove")
    @ResponseBody
    public Message purchaseApprove(int id) {
        return schoolAdminService.purchaseApprove(id);
        //return null;

    }
    //学院 年度 查询
    @RequestMapping(value = "selectDetail",method = RequestMethod.POST)
    @ResponseBody
    public Message selectDetail(@RequestBody Selecte selecte) {
        return schoolAdminService.selectDetail(selecte);
        //return null;
    }
}
