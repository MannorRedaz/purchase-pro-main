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
    @ResponseBody
    public Message getApplyBoard() {
        return supplierService.getApplyBoard();
    }


    //申请竞标接口  增
    @RequestMapping(value = "getSearchBidingList", method = RequestMethod.POST)
    @ResponseBody
    public Message getSearchBidingList(@RequestBody Selecte selecte) {
        return supplierService.getSearchBidingList(selecte);
    }

    //申请竞标接口  增
    @RequestMapping(value = "applyListScreen", method = RequestMethod.POST)
    @ResponseBody
    public Message applyListScreen(@RequestBody Selecte selecte) {
        return supplierService.applyListScreen(selecte);
    }

    //申请竞标接口  增
    @RequestMapping(value = "addApply", method = RequestMethod.POST)
    @ResponseBody
    public Message supplierApply(@RequestBody BiddingApplication ba) {
        return supplierService.supplierApply(ba);
    }

    //供应商申请列表接口 查
    @RequestMapping(value = "getApplyList")
    @ResponseBody
    public Message getApplyList(int id) {
        return supplierService.getApplyList(id);
    }

    //供应商申请列表接口 查
    @RequestMapping(value = "deleteApply", method = RequestMethod.POST)
    @ResponseBody
    public Message deleteApply(@RequestBody BiddingApplication ba) {
        return supplierService.deleteApply(ba);
    }

    //供应商申请列表接口 查
    @RequestMapping(value = "getResultListBySupplierId")
    @ResponseBody
    public Message getResultListBySupplierId(int sid) {
        return supplierService.getResultListBySupplierId(sid);
    }

    //供应商申请列表接口 查
    @RequestMapping(value = "getApplyResultList")
    @ResponseBody
    public Message getApplyResultList(int sid) {
        return supplierService.getApplyResultList(sid);
    }

    //供应商申请列表接口 查
    @RequestMapping(value = "supApplys", method = RequestMethod.POST)
    @ResponseBody
    public Message supApplys(@RequestBody int sid) {
        return supplierService.supApplys(sid);
    }

    //获得采购列表
    @RequestMapping(value = "getPurchaseList", method = RequestMethod.POST)
    @ResponseBody
    public Message getPurchaseList() {
        return supplierService.getPurchaseList();
    }


    //(2)供应商申请列表更新
    @RequestMapping(value = "applyUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Message applyUpdate(@RequestBody BiddingApplication ba) {
        return supplierService.applyUpdate(ba);
    }

    // 申请查询功能
    @RequestMapping(value = "applySelect", method = RequestMethod.POST)
    @ResponseBody
    public Message applySelect(@RequestBody String t1, String t2, int sid) {
        return supplierService.applySelect(t1, t2, sid);
    }

    // 供应商申请结果接口
    @RequestMapping(value = "supResult", method = RequestMethod.POST)
    @ResponseBody
    public Message supResult(@RequestBody int sid) {
        return supplierService.supResult(sid);
    }

    // 供应商申请结果接口
    @RequestMapping(value = "infoUpdate", method = RequestMethod.POST)
    //@PostMapping("supplierLogin")
    @ResponseBody
    public Message infoUpdate(@RequestBody Supplier supplier) {
        return supplierService.infoUpdate(supplier);
    }

    // 供应商入围申请
    @RequestMapping(value = "applyShortList", method = RequestMethod.POST)
    @ResponseBody
    public Message applyShortList(@RequestBody ApplyForShortlist afs) {
        return supplierService.applyShortList(afs);
    }

    // 供应商入围查询
    @RequestMapping(value = "getApplyForList")
    @ResponseBody
    public Message getApplyForList(int sid) {
        return supplierService.getApplyForList(sid);
    }

    //    //applyShortListSelect
//    @RequestMapping(value = "applyShortListSelect ", method = RequestMethod.POST)
//    @ResponseBody
//    public Message applyShortListSelect() {
//        return supplierService.applyShortListSelect()
//    }
// 供应商入围更新
    @RequestMapping(value = "applyShortUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Message applyShortUpdate(@RequestBody ApplyForShortlist afs) {
        return supplierService.applyShortListUpdate(afs);
    }

    //入围申请删除
    @RequestMapping(value = "applyShortListDelete")
    @ResponseBody
    public Message applyShortListDelete(int id) {
        return supplierService.applyShortListDelete(id);
    }
}
