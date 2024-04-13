package cn.mono.purchase.controller;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.pojo.ApplyForShortlist;
import cn.mono.purchase.pojo.BiddingApplication;
import cn.mono.purchase.pojo.Supplier;
import cn.mono.purchase.service.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author nihao
 * @time 2021/4/14
 */

@RestController
@RequestMapping("supplier")

public class SupplierController {
    @Autowired
    SupplierService supplierService;


    @RequestMapping(value = "getApplyBoard", method = RequestMethod.POST)
    public Message getApplyBoard() {
        return supplierService.getApplyBoard();
    }


    //申请竞标接口  增
    @RequestMapping(value = "getSearchBidingList", method = RequestMethod.POST)
    public Message getSearchBidingList(@RequestBody Selecte selecte) {
        return supplierService.getSearchBidingList(selecte);
    }

    //申请竞标接口  增
    @RequestMapping(value = "applyListScreen", method = RequestMethod.POST)
    public Message applyListScreen(@RequestBody Selecte selecte) {
        return supplierService.applyListScreen(selecte);
    }

    //申请竞标接口  增
    @RequestMapping(value = "addApply", method = RequestMethod.POST)
    public Message supplierApply(@RequestBody BiddingApplication ba) {
        return supplierService.supplierApply(ba);
    }

    //供应商申请列表接口 查
    @RequestMapping(value = "getApplyList")
    public Message getApplyList(int id) {
        return supplierService.getApplyList(id);
    }

    //供应商申请列表接口 查
    @RequestMapping(value = "deleteApply", method = RequestMethod.POST)
    public Message deleteApply(@RequestBody BiddingApplication ba) {
        return supplierService.deleteApply(ba);
    }

    //供应商申请列表接口 查
    @RequestMapping(value = "getResultListBySupplierId")
    public Message getResultListBySupplierId(int sid) {
        return supplierService.getResultListBySupplierId(sid);
    }

    //供应商申请列表接口 查
    @RequestMapping(value = "getApplyResultList")
    public Message getApplyResultList(int sid) {
        return supplierService.getApplyResultList(sid);
    }

    //供应商申请列表接口 查
    @RequestMapping(value = "supApplys", method = RequestMethod.POST)
    public Message supApplys(@RequestBody int sid) {
        return supplierService.supApplys(sid);
    }

    //获得采购列表
    @RequestMapping(value = "getPurchaseList", method = RequestMethod.POST)
    public Message getPurchaseList() {
        return supplierService.getPurchaseList();
    }


    //(2)供应商申请列表更新
    @RequestMapping(value = "applyUpdate", method = RequestMethod.POST)
    public Message applyUpdate(@RequestBody BiddingApplication ba) {
        return supplierService.applyUpdate(ba);
    }

    // 申请查询功能
    @RequestMapping(value = "applySelect", method = RequestMethod.POST)
    public Message applySelect(@RequestBody String t1, String t2, int sid) {
        return supplierService.applySelect(t1, t2, sid);
    }

    // 供应商申请结果接口
    @RequestMapping(value = "supResult", method = RequestMethod.POST)
    public Message supResult(@RequestBody int sid) {
        return supplierService.supResult(sid);
    }

    // 供应商申请结果接口
    @RequestMapping(value = "infoUpdate", method = RequestMethod.POST)
    //@PostMapping("supplierLogin")
    public Message infoUpdate(@RequestBody Supplier supplier) {
        return supplierService.infoUpdate(supplier);
    }

    // 供应商入围申请
    @RequestMapping(value = "applyShortList", method = RequestMethod.POST)
    public Message applyShortList(@RequestBody ApplyForShortlist afs) {
        return supplierService.applyShortList(afs);
    }

    // 供应商入围查询
    @RequestMapping(value = "getApplyForList")
    public Message getApplyForList(int sid) {
        return supplierService.getApplyForList(sid);
    }

// 供应商入围更新
    @RequestMapping(value = "applyShortUpdate", method = RequestMethod.POST)
    public Message applyShortUpdate(@RequestBody ApplyForShortlist afs) {
        return supplierService.applyShortListUpdate(afs);
    }

    //入围申请删除
    @RequestMapping(value = "applyShortListDelete")
    public Message applyShortListDelete(int id) {
        return supplierService.applyShortListDelete(id);
    }
}
