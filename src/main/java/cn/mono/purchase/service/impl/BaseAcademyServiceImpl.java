package cn.mono.purchase.service.impl;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.mapper.*;
import cn.mono.purchase.pojo.*;

import cn.mono.purchase.service.BaseAcademyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author nihao
 * @time 2021/4/14
 */
@Service
public class BaseAcademyServiceImpl implements BaseAcademyService {
    @Resource
    Academy_categoryMapper academy_categoryMapper;
    @Resource
    Product_categoryMapper product_categoryMapper;
    @Resource
    ResultMapper resultMapper;
    @Resource
    Purchasing_itemsMapper purchasingItemsMapper;
    @Resource
    SupplierMapper supplierMapper;
    @Resource
    Bidding_applicationMapper bidding_applicationMapper;
    @Resource
    PurchaserMapper purchaserMapper;
    Message message;

    @Override
    public Message GetProductCategory() {
        List<ProductCategory> product_categories = product_categoryMapper.selectAll();
        if (!product_categories.isEmpty()) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取产品类别成功");
            message.setDate(product_categories);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取产品类别失败");
        }
        return message;
    }

    @Override
    public Message GetAcademyCategory() {
        List<AcademyCategory> academy_categories = academy_categoryMapper.selectAll();
        if (academy_categories.size() != 0) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取产品类别成功");
            message.setDate(academy_categories);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取产品类别失败");
        }
        return message;
    }

    @Override
    public Message GetResultCategory() {
        List<Result> results = resultMapper.selectAll();
        if (results.size() != 0) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取结果列表成功");
            message.setDate(results);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取结果列表失败");
        }
        return message;
    }

    @Override
    public Message GetPurchasingCategory() {
        List<PurchasingItems> purchasing_items = purchasingItemsMapper.selectAll();
        if (!purchasing_items.isEmpty()) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取采购列表成功");
            message.setDate(purchasing_items);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取采购列表失败");
        }
        return message;
    }

    @Override
    public Message GetPurchasingCategory1() {
        List<PurchasingItems> purchasing_items = purchasingItemsMapper.selectAll();

        if (!purchasing_items.isEmpty()) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取采购列表成功");
            message.setDate(purchasing_items);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取采购列表失败");
        }
        return message;
    }

    @Override
    public Message GetBoardList() {
        List<PurchasingItems> purchasing_items = purchasingItemsMapper.selectAll();
        List<PurchasingItems> list = new ArrayList<>();
        for (PurchasingItems p : purchasing_items) {

            if (p.getAcceess() == 11 && p.getPurchase_name() != null && p.getPurchase_name().length() > 0) {
                
                list.add(p);
            }
        }

        if (!purchasing_items.isEmpty()) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取到" + list.size() + "条数据");
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取采购列表失败");
        }
        return message;
    }

    @Override
    public Message GetBoard(int id) {
        PurchasingItems purchasing_items = null;
        List<PurchasingItems> list = new ArrayList<>();

        try {
            purchasing_items = purchasingItemsMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
            return message;
        }
        if (purchasing_items != null) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取采购列表成功");
            list.add(purchasing_items);
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取采购列表失败");
        }
        return message;
    }

    @Override
    public Message updateBoard(PurchasingItems purchasing_items) {

        int i = purchasingItemsMapper.updateByPrimaryKey(purchasing_items);
        if (i == 1) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("更新成功");

            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获更新失败");
        }
        return message;
    }

    @Override
    public Message getResult(int id) {
        Result result = null;
        List<Result> list = new ArrayList<>();

        try {
            result = resultMapper.selectByPIId(id);
        } catch (Exception e) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
            return message;
        }
        if (result != null) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取结果成功");
            list.add(result);
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取结果失败");
        }
        return message;
    }

    @Override
    public Message getSupplier(int id) {
        Supplier supplier = null;
        List<Supplier> list = new ArrayList<>();

        try {
            supplier = supplierMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
            return message;
        }
        if (supplier != null) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取中标供应商成功");
            list.add(supplier);
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取结中标供应商失败");
        }
        return message;
    }

    @Override
    public String getFileName(int id) {
        //Result result = resultMapper.selectByPrimaryKey(id);
        PurchasingItems purchasing_items = purchasingItemsMapper.selectByPrimaryKey(id);
        String name = purchasing_items.getProduct_name() + "采购审批表";
        return name;
        //return "采购审批表";
    }

    @Override
    public Message boardScreen(Selecte selecte) {
        
        List<PurchasingItems> purchasing_items = purchasingItemsMapper.selectAll();
        List<PurchasingItems> purchasing_items1 = new ArrayList<>();
        int i = 0;
        //判断是否需要学院的筛选
        if (selecte.getAcademy() != null) {
            AcademyCategory academy_category = academy_categoryMapper.selectByName(selecte.getAcademy());
            if (academy_category != null) {
                for (PurchasingItems item : purchasing_items
                ) {
                    if (academy_category.getCid() == item.getCid()) {
                        purchasing_items1.add(item);
                    }
                }
                //清空内容
                purchasing_items.clear();
                for (PurchasingItems item : purchasing_items1
                ) {
                    purchasing_items.add(item);
                }
                purchasing_items1.clear();

            }
        }

        if (selecte.getCategories() != null) {
            ProductCategory product_category = product_categoryMapper.selectByName(selecte.getCategories());

            if (product_category != null) {
                for (PurchasingItems item : purchasing_items
                ) {
                    if (product_category.getPid() == item.getPid()) {
                        purchasing_items1.add(item);
                    }
                }
                //清空内容
                purchasing_items.clear();
                for (PurchasingItems item : purchasing_items1
                ) {
                    purchasing_items.add(item);
                }
                purchasing_items1.clear();

            }
        }
        //list list1 null
        if (selecte != null && selecte.getValue1() != null) {
            List<Date> value1 = selecte.getValue1();
            if (!value1.isEmpty()) {
                Date date1 = value1.get(0);
                Date date2 = value1.get(1);
                if (date1 != null && date2 != null) {
                    int m = date2.compareTo(date1);
                    if (m != 0) {
                        for (PurchasingItems item : purchasing_items) {
                            if (date1 == null) {
                                int res2 = item.getPublish_time().compareTo(date1);
                                if (res2 >= 0) {
                                    purchasing_items1.add(item);
                                }
                            } else if (date2 == null) {
                                int res = item.getPublish_time().compareTo(date2);
                                if (res <= 0) {
                                    purchasing_items1.add(item);
                                }
                            } else {
                                int res = item.getPublish_time().compareTo(date2);
                                int res2 = item.getPublish_time().compareTo(date1);
                                if (res <= 0 && res2 >= 0) {
                                    purchasing_items1.add(item);
                                }
                            }
                        }

                        purchasing_items.clear();
                        for (PurchasingItems item : purchasing_items1) {
                            purchasing_items.add(item);
                        }
                        purchasing_items1.clear();
                    }
                }
            }
        }
        for (PurchasingItems p : purchasing_items) {
            if (p.getAcceess() == 11 && p.getPurchase_name() != null && p.getPurchase_name().length() > 0) {
                purchasing_items1.add(p);
            }
        }
        purchasing_items.clear();
        for (PurchasingItems item : purchasing_items1
        ) {
            purchasing_items.add(item);
        }
        purchasing_items1.clear();


        message = new Message();
        message.setSuccess(true);
        message.setMsg("查询到" + purchasing_items.size() + "条记录");
        message.setDate(purchasing_items);
        message.setStatus(1);
        return message;
    }

    @Override
    public Message addPurBoard(PurchasingItems purchasing_items) {
        
        purchasing_items.setPublish_time(new Date());
        int i = purchasingItemsMapper.updateByPrimaryKey(purchasing_items);
        if (i == 1) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("插入成功");
            //purchasing_items = purchasingItemsMapper.selectLastInsert();

            List<PurchasingItems> list = purchasingItemsMapper.selectByCid(purchasing_items.getCid());
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("插入失败");
        }
        return message;
    }

    @Override
    public Message getBoardListByCid(int cid) {
        
        List<PurchasingItems> list = new ArrayList<>();
        List<PurchasingItems> purchasing_items = null;
        if (cid == 0) {
            purchasing_items = purchasingItemsMapper.selectAll();
        } else {
            purchasing_items = purchasingItemsMapper.selectByCid(cid);
        }

        for (PurchasingItems p : purchasing_items) {
            if (p.getAcceess() >= 1) {
                list.add(p);
            }
        }
        message = new Message();
        message.setSuccess(true);
        message.setMsg("查询到" + list.size() + "条数据");
        message.setDate(list);
        message.setStatus(1);

        return message;
    }

    @Override
    public Message editPurBoard(PurchasingItems purchasing_items) {
        int i = purchasingItemsMapper.updateByPrimaryKey(purchasing_items);
        if (i == 1) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("修改成功");
            //purchasing_items = purchasingItemsMapper.selectLastInsert();
            List<PurchasingItems> list = purchasingItemsMapper.selectByCid(purchasing_items.getCid());
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("修改失败");
        }
        return message;
    }

    @Override
    public Message deletePurBoard(PurchasingItems purchasing_items) {
        int i = purchasingItemsMapper.deleteByPrimaryKey(purchasing_items.getId());
        if (i == 1) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("删除成功");
            //purchasing_items = purchasingItemsMapper.selectLastInsert();

            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("删除失败");
        }
        return message;
    }

    //获取用户名称用于文件名
    @Override
    public String getSupplyFileName(int id) {
        Supplier supplier = supplierMapper.selectByPrimaryKey(id);

        return supplier.getCompany_name() + "申请表" + ".docx";
        //return "采购审批表";
    }

    @Override
    public String getPurchaseFileName(int id) {
        PurchasingItems purchasing_items = purchasingItemsMapper.selectByPrimaryKey(id);
        return purchasing_items.getProduct_name() + "申请表" + ".docx";
    }

    @Override
    public Message getApplyCount(int purid) {

        int count = 0;
        count = bidding_applicationMapper.getApplyCount(purid);

        message = new Message();
        message.setSuccess(true);
        message.setMsg("查询成功");
        message.setStatus(count);


        return message;
    }

    @Override
    public Message getMinimumPrice(int purid) {
        List<BiddingApplication> list = bidding_applicationMapper.selectByPurchaingItemId(purid);
        int minimum = 1000000;
        for (BiddingApplication b : list) {
            if (b.getRealy_price() < minimum) {
                minimum = b.getRealy_price();
            }
        }
        if (minimum == 1000000) {
            minimum = 0;
        }
        message = new Message();
        message.setSuccess(true);
        message.setMsg("查询成功");
        message.setStatus(minimum);
        return message;
    }

    @Override
    public Message addResultBoard(int id) {
        Result result = resultMapper.selectByPIId(id);
        if (result != null) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("对不起，你已经发布结果公告");
            return message;
        }
        PurchasingItems purchasing_items = purchasingItemsMapper.selectByPrimaryKey(id);
        int val1 = purchasing_items.getEnd_time().compareTo(new Date());
        if (val1 >= 0) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("对不起，还未截至，不能发布公告");
            return message;
        }

        purchasing_items.setIs_result(1);
        int i = purchasingItemsMapper.updateByPrimaryKey(purchasing_items);
        List<BiddingApplication> list = bidding_applicationMapper.selectByPurchaingItemId(id);
        if (list.size() == 0) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("对不起,没人参与竞标，竞标失败");
            return message;
        }
        int minimun = 1000000;
        if (list.size() != 0) {
            minimun = list.get(0).getRealy_price();
        }
        int thisId = 0;
        for (BiddingApplication b : list) {
            if (b.getRealy_price() < minimun) {
                minimun = b.getRealy_price();
                thisId = b.getId();
            }
        }

        File file = new File("D:\\R_normal_soft\\idea\\study\\ssm\\boardFile\\resultFile\\" + id + ".pdf");
        
        if (!file.exists()) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("请先上传文件");
            return message;
        }


        Result result1 = new Result();
        result1.setPid(purchasing_items.getId());
        for (BiddingApplication bd : list) {
            if (bd.getId() == thisId) {
                result1.setSid(bd.getSid());//设置供应商id
            }
        }
        //result1.setSid(list.get(thisId).getSid());//设置供应商id
        result1.setScis(1);
        result1.setPurchase_id(purchaserMapper.selectByCid(purchasing_items.getCid()).getId());
        result1.setActual_product_name(purchasing_items.getProduct_name());
        result1.setReality_price(minimun);
        result1.setDate(new Date());
        int j = resultMapper.insert(result1);


        if (i == 1 && j == 1) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("插入成功");
            return message;
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("插入失败");
            return message;
        }


    }

    @Override
    public Message getAllBoardListByCid(int cid) {
        List<PurchasingItems> purchasing_items = purchasingItemsMapper.selectByCid(cid);
        List<PurchasingItems> list = new ArrayList<>();
        for (PurchasingItems p : purchasing_items) {
            if (p.getAcceess() == 11 && p.getPurchase_name() != null && p.getPurchase_name().length() > 0) {
                list.add(p);
            }
        }

        message = new Message();
        message.setSuccess(true);
        message.setMsg("查询到" + list.size() + "条数据");
        message.setDate(list);
        message.setStatus(1);

        return message;
    }

    @Override
    public Message boardScreen1(Selecte selecte) {
        
        //selecte.getAcademy()
        List<PurchasingItems> purchasing_items = purchasingItemsMapper.selectAll();
        List<PurchasingItems> purchasing_items1 = new ArrayList<>();
        int i = 0;
        //判断是否需要学院的筛选
        if (selecte.getAcademy() != null) {
            AcademyCategory academy_category = academy_categoryMapper.selectByName(selecte.getAcademy());
            if (academy_category != null) {
                for (PurchasingItems item : purchasing_items
                ) {
                    if (academy_category.getCid() == item.getCid()) {
                        purchasing_items1.add(item);
                    }
                }
                //清空内容
                purchasing_items.clear();
                for (PurchasingItems item : purchasing_items1
                ) {
                    purchasing_items.add(item);
                }
                purchasing_items1.clear();

            }
        }

        if (selecte.getCategories() != null) {
            ProductCategory product_category = product_categoryMapper.selectByName(selecte.getCategories());

            if (product_category != null) {
                for (PurchasingItems item : purchasing_items
                ) {
                    if (product_category.getPid() == item.getPid()) {
                        purchasing_items1.add(item);
                    }
                }
                //清空内容
                purchasing_items.clear();
                for (PurchasingItems item : purchasing_items1
                ) {
                    purchasing_items.add(item);
                }
                purchasing_items1.clear();

            }
        }
        //list list1 null
        if (selecte.getValue1().get(0) != null || selecte.getValue1().get(1) != null) {
            int m = selecte.getValue1().get(1).compareTo(selecte.getValue1().get(0));

            if (m != 0) {
                if (selecte.getValue1().get(0) == null) {
                    for (PurchasingItems item : purchasing_items
                    ) {
                        int res2 = item.getPublish_time().compareTo(selecte.getValue1().get(0));
                        if (res2 >= 0) {
                            purchasing_items1.add(item);
                        }
                    }
                } else if (selecte.getValue1().get(0) == null) {
                    for (PurchasingItems item : purchasing_items
                    ) {
                        int res = item.getPublish_time().compareTo(selecte.getValue1().get(1));
                        if (res <= 0) {
                            purchasing_items1.add(item);
                        }
                    }

                } else {
                    for (PurchasingItems item : purchasing_items
                    ) {
                        int res = item.getPublish_time().compareTo(selecte.getValue1().get(1));
                        int res2 = item.getPublish_time().compareTo(selecte.getValue1().get(0));
                        if (res <= 0 && res2 >= 0) {
                            purchasing_items1.add(item);
                        }
                    }

                }
                purchasing_items.clear();
                for (PurchasingItems item : purchasing_items1
                ) {
                    purchasing_items.add(item);
                }
                purchasing_items1.clear();
            }
        }
        if (selecte.getType() != null) {
            // item.getErrol_type()==2
            if (selecte.getType() == "结果公告") {
                for (PurchasingItems item : purchasing_items
                ) {
                    if (item.getErrol_type() == 2) {
                        purchasing_items1.add(item);
                    }
                }
            } else {
                for (PurchasingItems item : purchasing_items
                ) {
                    if (item.getErrol_type() == 1) {
                        purchasing_items1.add(item);
                    }
                }
            }
            purchasing_items.clear();
            for (PurchasingItems item : purchasing_items1
            ) {
                purchasing_items.add(item);
            }
            purchasing_items1.clear();
        }
        message = new Message();
        message.setSuccess(true);
        message.setMsg("查询到" + purchasing_items.size() + "条记录");
        message.setDate(purchasing_items);
        message.setStatus(1);
        return message;
    }

    @Override
    public Message getChartList(int id) {
        
        

        message = new Message();
        try {

            List<Result> results = resultMapper.selectBySupplierId(id);
            message.setSuccess(true);
            message.setMsg("查询到" + results.size() + "条数据");
            message.setDate(results);
            message.setStatus(1);
        } catch (Exception e) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
        }
        return message;
    }

    @Override
    public Message getChartAllList() {

        message = new Message();
        try {

            List<Result> results = resultMapper.selectAll();
            message.setSuccess(true);
            message.setMsg("查询到" + results.size() + "条数据");
            message.setDate(results);
            message.setStatus(1);
        } catch (Exception e) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
        }
        return message;
    }

    @Override
    public Message getChartSupplier() {
        message = new Message();
        try {
            List<Result> results = resultMapper.selectAll();
            message.setSuccess(true);
            message.setMsg("查询到" + results.size() + "条数据");
            message.setDate(results);
            message.setStatus(1);
        } catch (Exception e) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
        }
        return message;
    }

    @Override
    public Message getSupplierChart(int id) {
        President president = new President();
        message = new Message();

        president.setSid(0);
        president.setId(0);
        List<Result> results = resultMapper.selectBySupplierId(id);
        for (Result re : results
        ) {
            president.setSid(president.getSid() + re.getReality_price());
            president.setId(president.getId() + 1);
            president.setLast_time(re.getDate());
        }
        Supplier supplier = supplierMapper.selectByPrimaryKey(id);
        president.setName(supplier.getCompany_name());
        message.setSuccess(true);
        List<President> presidents = new ArrayList<>();
        presidents.add(president);
        message.setDate(presidents);
        message.setStatus(1);
        return message;
    }

    //为采购产品的id
    @Override
    public Message getApplyList(int id) {
        message = new Message();
        try {
            List<BiddingApplication> list = bidding_applicationMapper.selectByPurchaingItemId(id);
            for (BiddingApplication ba : list
            ) {
                Supplier supplier = supplierMapper.selectByPrimaryKey(ba.getSid());
                ba.setTag(supplier.getCompany_name());

            }


            message.setSuccess(true);
            message.setMsg("查询到" + list.size() + "条数据");
            message.setDate(list);
            message.setStatus(1);
        } catch (Exception e) {

            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
        }

        return message;
    }

    @Override
    public Message addResBoard(BiddingApplication ba) {

        Result result = resultMapper.selectByPIId(ba.getPurid());
        if (result != null) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("对不起，你已经发布结果公告");
            return message;
        }

        PurchasingItems purchasing_items = purchasingItemsMapper.selectByPrimaryKey(ba.getPurid());
        int val1 = purchasing_items.getEnd_time().compareTo(new Date());
        if (val1 >= 0) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("对不起，还未截至，不能发布公告");
            return message;
        }

        purchasing_items.setIs_result(1);
        int i = purchasingItemsMapper.updateByPrimaryKey(purchasing_items);

        File file = new File("D:\\R_normal_soft\\idea\\study\\ssm\\boardFile\\resultFile\\" + ba.getPurid() + ".pdf");
        
        if (!file.exists()) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("请先上传文件");
            return message;
        }


        Result result1 = new Result();
        result1.setPid(ba.getPurid());
        result1.setSid(ba.getSid());//设置供应商id
        result1.setScis(1);
        result1.setPurchase_id(purchaserMapper.selectByCid(purchasing_items.getCid()).getId());
        result1.setActual_product_name(ba.getProduct_name());
        result1.setReality_price(ba.getRealy_price());
        result1.setDate(new Date());
        int j = resultMapper.insert(result1);


        if (i == 1 && j == 1) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("插入成功");
            return message;
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("插入失败");
            return message;
        }

    }
}
