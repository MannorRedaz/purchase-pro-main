package cn.mono.purchase.controller;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.pojo.ApplyForShortlist;
import cn.mono.purchase.pojo.BiddingApplication;
import cn.mono.purchase.pojo.President;

import cn.mono.purchase.service.PresidentService;
import cn.mono.purchase.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author nihao
 * @time 2021/4/14
 */

@RestController
@RequestMapping("president")
@CrossOrigin(origins = "*")
public class PresidentController {
    @Autowired
    PresidentService presidentService;


    @Autowired
    SupplierService supplierService;

    @RequestMapping(value = "purchaseScreen", method = RequestMethod.POST)
    @ResponseBody
    public Message purchaseScreen(@RequestBody Selecte selecte) {
        return presidentService.purchaseScreen(selecte);
    }
    @RequestMapping(value = "nihao")
    @ResponseBody
    public String updateAttr(@RequestParam ("id") int id) {
        return "success"+id;
    }

    //供应商 审核管理 提交
    @RequestMapping(value = "supManageSubmit", method = RequestMethod.POST)
    @ResponseBody
    public Message supManageSubmit(@RequestParam("sid") int sid) {
        return supplierService.supApplys(sid);
    }

    //供应商 审核管理 更新
    @RequestMapping(value = "supManageUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Message supManageUpdate(@RequestBody BiddingApplication ba) {
        return supplierService.applyUpdate(ba);
    }


    //实验室管理员 审核管理 查询
    @RequestMapping(value = "purManageSelect", method = RequestMethod.POST)
    @ResponseBody
    public Message purManageSelect( @RequestBody BiddingApplication ba) {
        return supplierService.supplierApply(ba);
    }

    //实验室管理员 审核管理 提交
    @RequestMapping(value = "purManageSubmit", method = RequestMethod.POST)
    @ResponseBody
    public Message purManageSubmit(@RequestBody int sid) {
        return supplierService.supApplys(sid);
    }

    //实验室管理员 审核管理 更新
    @RequestMapping(value = "purManageUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Message purManageUpdate(@RequestBody BiddingApplication ba) {
        return supplierService.applyUpdate(ba);
    }

    //预算查询
    @RequestMapping(value = "getBudget")
    @ResponseBody
    public Message getBudget(int sid) {
        return presidentService.getBudget(sid);
    }

    //已花金额查询
    @RequestMapping(value = "getUsedMoney")
    @ResponseBody
    public Message getUsedMoney(int cid) {
        return presidentService.getUsedMoney(cid);
    }

    //剩余余额
    @RequestMapping(value = "getBalance", method = RequestMethod.POST)
    @ResponseBody
    public Message getBalance(@RequestBody ApplyForShortlist afs) {
        return supplierService.applyShortList(afs);
    }

    //账单的查询 就是该学院花钱明细
    @RequestMapping(value = "getResultList", method = RequestMethod.POST)
    @ResponseBody
    public Message getResultList(@RequestBody ApplyForShortlist afs) {
        return supplierService.applyShortList(afs);
    }

    //    //applyShortListSelect
//    @RequestMapping(value = "applyShortListSelect ", method = RequestMethod.POST)
//    @ResponseBody
//    public Message applyShortListSelect() {
//        return supplierService.applyShortListSelect()
//    }
// 供应商入围更新
    @RequestMapping(value = "applyShortUpdate ", method = RequestMethod.POST)
    @ResponseBody
    public Message applyShortUpdate(@RequestBody ApplyForShortlist afs) {
        return supplierService.applyShortListUpdate(afs);
    }

    //入围申请删除
    @RequestMapping(value = "applyShortListDelete ", method = RequestMethod.POST)
    @ResponseBody
    public Message applyShortListDelete(@RequestBody int id) {
        return supplierService.applyShortListDelete(id);
    }

    //获取该学院的采购列表
    @RequestMapping(value = "getPurchaseList")
    @ResponseBody
    public Message getPurchaseList(int cid) {
        return presidentService.getPurchaseList( cid);
        //return null;

    }

    //获取采购申请列表
    @RequestMapping(value = "getPurchaseList1")
    @ResponseBody
    public Message getPurchaseList1(int cid) {
        return presidentService.getPurchaseList1(cid);
        //return null;
    }//获取采购申请列表
    //同意其采购申请
    @RequestMapping(value = "purchaseApprove")
    @ResponseBody
    public Message purchaseApprove(int id) {
        return presidentService.purchaseApprove(id);
    }

    //年度 查询
    @RequestMapping(value = "selectDetail",method = RequestMethod.POST)
    @ResponseBody
    public Message selectDetail(@RequestBody Selecte selecte) {
        return presidentService.selectDetail(selecte);
        //return null;
    }

    // 供应商申请结果接口
    @RequestMapping(value = "infoUpdate ", method = RequestMethod.POST)
    @ResponseBody
    public Message infoUpdate(@RequestBody President president) {
        return presidentService.infoUpdate(president);
    }


}
