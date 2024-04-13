package cn.mono.purchase.controller;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.mapper.Purchasing_itemsMapper;
import cn.mono.purchase.pojo.BiddingApplication;
import cn.mono.purchase.pojo.PurchasingItems;
import cn.mono.purchase.service.BaseAcademyService;
import cn.mono.purchase.service.SupplierService;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;

/**
 * @author nihao
 * @time 2021/4/16
 */
//@Api(value = "公共Controller", tags = { "公共访问接口" })
@RestController
@RequestMapping("/")
public class CommonController {
    @Resource
    private BaseAcademyService baseAcademyService;
    @Resource
    private SupplierService supplierService;
    @Resource
    private Purchasing_itemsMapper purchasing_itemsMapper;
    Message message;
    @RequestMapping(value = "getSupplierChart")
    public Message getSupplierChart(int id) {
        return baseAcademyService.getSupplierChart(id);
    }

    //获得供应商的情况
    @RequestMapping(value = "getChartSupplier")
    public Message getChartSupplier() {
        return baseAcademyService.getChartSupplier();
    }

    @RequestMapping(value = "getChartAllList")
    public Message getChartAllList() {
        return baseAcademyService.getChartAllList();
    }

    @RequestMapping(value = "getChartList")
    public Message getChartList(int id) {
        return baseAcademyService.getChartList(id);
    }

    @RequestMapping(value = "addResultBoard")
    public Message addResultBoard(int id) {
        return baseAcademyService.addResultBoard(id);
    }


    @RequestMapping(value = "getApplyCount")
    public Message getApplyCount(int id) {
        return baseAcademyService.getApplyCount(id);
    }

    // 产品类别接口
    @RequestMapping(value = "getMinimumPrice")
    public Message getMinimumPrice(int id) {
        return baseAcademyService.getMinimumPrice(id);
    }


    //  学院类别接口
    @RequestMapping(value = "academys", method = RequestMethod.POST)
    public Message getAcademyCategory() {
        return baseAcademyService.GetAcademyCategory();
    }

    // 产品类别接口
    @RequestMapping(value = "categories", method = RequestMethod.POST)
    public Message getPurchasingCategory() {
        return baseAcademyService.GetProductCategory();
    }

    //   采购列表接口
    @RequestMapping(value = "purchasingList", method = RequestMethod.POST)
    public Message getPurchasingList() {
        return baseAcademyService.GetPurchasingCategory();
    }

    //   获得学院单独的采购公告
    @RequestMapping(value = "getBoardListByCid")
    public Message getBoardListByCid(int cid) {
        return baseAcademyService.getBoardListByCid(cid);
    }

    //   结果接口
    @RequestMapping(value = "resultList", method = RequestMethod.POST)
    public Message getResultCategory() {
        return baseAcademyService.GetResultCategory();
    }


    //   获得公告接口
    @RequestMapping(value = "getBoardList", method = RequestMethod.POST)
    public Message boardListSelect() {
        return baseAcademyService.GetBoardList();
    }

    //   获得某条公告
    @RequestMapping(value = "getBoard")
    public Message getBoard(int id) {
        return baseAcademyService.GetBoard(id);
    }

    @RequestMapping(value = "updateBoard", method = RequestMethod.POST)
    public Message updateBoard(@RequestBody PurchasingItems purchasing_items) {
        return baseAcademyService.updateBoard(purchasing_items);
    }

    @RequestMapping(value = "getResult")
    public Message getResult(int id) {
        return baseAcademyService.getResult(id);
    }

    @RequestMapping(value = "getApplyList")
    public Message getApplyList(int id) {
        return baseAcademyService.getApplyList(id);
    }

    //获得中标用户
    @RequestMapping(value = "getSupplier")
    public Message getSupplier(int id) {
        return baseAcademyService.getSupplier(id);
    }

    //获得中标用户
    @RequestMapping(value = "getAllBoardListByCid")
    public Message getAllBoardListByCid(int cid) {
        return baseAcademyService.getAllBoardListByCid(cid);
    }

    //全部公告的 筛选功能
    @RequestMapping(value = "boardScreen", method = RequestMethod.POST)
    public Message boardScreen(@RequestBody Selecte selecte) {
        
        return baseAcademyService.boardScreen(selecte);
    }

    //某个学院的 筛选功能
    @RequestMapping(value = "boardScreen1", method = RequestMethod.POST)
    public Message boardScreen1(@RequestBody Selecte selecte) {
        
        return baseAcademyService.boardScreen1(selecte);
    }

    //添加采购公告
    @RequestMapping(value = "addPurBoard", method = RequestMethod.POST)
    public Message addPurBoard(@RequestBody PurchasingItems purchasing_items) {

        return baseAcademyService.addPurBoard(purchasing_items);
    }

    //修改采购公告
    @RequestMapping(value = "editPurBoard", method = RequestMethod.POST)
    public Message editPurBoard(@RequestBody PurchasingItems purchasing_items) {
        return baseAcademyService.editPurBoard(purchasing_items);
    }

    //删除采购公告
    @RequestMapping(value = "deletePurBoard", method = RequestMethod.POST)
    public Message deletePurBoard(@RequestBody PurchasingItems purchasing_items) {

        return baseAcademyService.deletePurBoard(purchasing_items);
    }

    //添加结果公告
    @RequestMapping(value = "addResBoard", method = RequestMethod.POST)
    public Message addResBoard(@RequestBody BiddingApplication bidding_application) {
        //return  baseAcademyService.addResBoard(purchasing_items);
        return baseAcademyService.addResBoard(bidding_application);
    }

}
