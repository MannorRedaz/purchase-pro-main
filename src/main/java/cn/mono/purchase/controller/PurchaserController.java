package cn.mono.purchase.controller;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.dto.TimeSelectDto;
import cn.mono.purchase.pojo.Purchaser;
import cn.mono.purchase.pojo.PurchasingItems;

import cn.mono.purchase.service.PurchaserService;
import cn.mono.purchase.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author nihao
 * @time 2021/4/14
 */

@RestController
@RequestMapping("purchaser")
@CrossOrigin(origins = "*")
public class PurchaserController {
    @Autowired
    SupplierService supplierService;
    @Autowired
    PurchaserService purchaserService;

    //采购者申请采购
    @RequestMapping(value = "getPurchaseById", method = RequestMethod.POST)
    @ResponseBody
    public Message getPurchaseById(@RequestBody PurchasingItems pi) {
        return purchaserService.getPurchaseById(pi);
    }

    //采购者申请采购
    @RequestMapping(value = "addPurchase", method = RequestMethod.POST)
    @ResponseBody
    public Message addPurchase( @RequestBody PurchasingItems pi) {
        return purchaserService.purchaseApply(pi);
    }


    // 采购者申请采购更新接口
    @RequestMapping(value = "purchaseApplyUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Message purchaseApplyUpdate (@RequestBody PurchasingItems pi) {
        return purchaserService.purchaseApplyUpdate(pi);
    }

    // 采购者申请采购查询(只显示 还在审核 和  未通过 和 还没有结果的申请（还没有供应商的中标）)
    @RequestMapping(value = "purchaseApplySelect", method = RequestMethod.POST)
    @ResponseBody
    public Message purchaseApplySelect ( @RequestBody int id) {
        return purchaserService.purchaseApplySelect(id);
    }
    // 采购者申请采购删除（取消申请）
    @RequestMapping(value = "deletePurchase")
    @ResponseBody
    public Message deletePurchase ( int id) {
        return purchaserService.purchaseApplyDelete(id);
    }

    // 采购者申请采购查询接口 所有查询
    @RequestMapping(value = "purchaseApplyAllSelect", method = RequestMethod.POST)
    @ResponseBody
    public Message purchaseApplyAllSelect    ( int id) {
        return purchaserService.purchaseApplyAllSelect(id);
    }
    // 采购者申请采购查询接口 所有查询
    @RequestMapping(value = "purchaseScreen", method = RequestMethod.POST)
    @ResponseBody
    public Message purchaseScreen( @RequestBody Selecte selecte) {
        return purchaserService.purchaseScreen(selecte);
    }

    // 采购者申请采购查询接口 查询每一个学院的订单
    @RequestMapping(value = "purchaseScreen1", method = RequestMethod.POST)
    @ResponseBody
    public Message purchaseScreen1( @RequestBody  Selecte selecte) {
        return purchaserService.purchaseScreen1(selecte);
    }
// 采购者申请采购查询接口
    /**
     * b.开始时间，截至时间
     * 商品类别编号 pid（化学类 传1）
     */
    @RequestMapping(value = "purchaseApplyAllSelectFilter", method = RequestMethod.POST)
    @ResponseBody
    public Message purchaseApplyAllSelectFilter   (@RequestBody TimeSelectDto tsd) {
        return purchaserService.purchaseApplyAllSelectFilter(tsd);
    }




    // 采购者申请采购查询 时间选择器 和类别选择器
    @RequestMapping(value = "purchaseApplyResultFilter", method = RequestMethod.POST)
    //@PostMapping("supplierLogin")
    @ResponseBody
    public Message purchaseApplyResultFilter (@RequestBody TimeSelectDto tsd) {
        return purchaserService.purchaseApplyResultFilter(tsd);
    }


    // 采购者申请采购的结果通知（对他的采购的商品中标后通知他）
    @RequestMapping(value = "purchaseApplyResult", method = RequestMethod.POST)
    @ResponseBody
    public Message purchaseApplyResult (@RequestBody  int id) {
        return purchaserService.purchaseApplyResult(id);
    }


    // 采购者个人信息查询
    @RequestMapping(value = "InfoSelect", method = RequestMethod.POST)
    @ResponseBody
    public Message InfoSelect(@RequestBody  int id) {
        return purchaserService.InfoSelect(id);
    }


// ②采购者个人信息修改
    @RequestMapping(value = "infoUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Message InfoUpdate(@RequestBody Purchaser purchaser) {
        return purchaserService.InfoUpdate(purchaser);
    }
}
