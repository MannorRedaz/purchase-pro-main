package cn.mono.purchase.service.impl;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.dto.TimeSelectDto;
import cn.mono.purchase.mapper.*;
import cn.mono.purchase.pojo.*;

import cn.mono.purchase.service.PurchaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nihao
 * @time 2021/4/16
 */
@Service
public class PurchaserServiceImpl implements PurchaserService {
    @Autowired
    Bidding_applicationMapper bidding_applicationMapper;
    @Autowired
    Academy_categoryMapper academy_categoryMapper;
    @Autowired
    Product_categoryMapper product_categoryMapper;
    @Autowired
    Apply_for_shortlistMapper apply_for_shortlistMapper;
    @Autowired
    ResultMapper resultMapper;
    @Autowired
    SupplierMapper supplierMapper;
    @Autowired
    Purchasing_itemsMapper purchasing_itemsMapper;
    @Autowired
    PurchaserMapper purchaserMapper;
    Message msg;

    @Override
    public Message purchaseApply(PurchasingItems pi) {
        int i = 0;
        pi.setAcceess(0);
        pi.setErrol_type(purchasing_itemsMapper.selectLastInsert().getId()+1);

        i = purchasing_itemsMapper.insert(pi);
        
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("申请采购成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setStatus(0);
            msg.setMsg("申请采购失败");
        }
        return msg;
    }

    @Override
    public Message purchaseApplyUpdate(PurchasingItems pi) {
        int i = 0;
        i = purchasing_itemsMapper.updateByPrimaryKey(pi);
        List<PurchasingItems> list = null;
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("采购申请更新成功");
            list.add(purchasing_itemsMapper.selectByPrimaryKey(pi.getId()));
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("采购申请更新失败");
        }
        return msg;
    }

    /**
     * 只显示 还在审核 和  未通过 和 还没有结果的申请（还没有供应商的中标）
     *
     * @param id
     * @return
     */
    @Override
    public Message purchaseApplySelect(int id) {
        //通过用户id查询采购的栏目
        Purchaser purchaser = purchaserMapper.selectByPrimaryKey(id);
        List<PurchasingItems> list1 = new ArrayList<>();
        //通过学院id查询所有的采购栏目
        List<PurchasingItems> list = purchasing_itemsMapper.selectByCid(purchaser.getCid());
        for (PurchasingItems pi :
                list) {
            //代表未通过和没有通过的
            if (pi.getAcceess() != 11 && pi.getAcceess() != 1) {
                list1.add(pi);
            } else {
                //代表还没有出现结果
                if (resultMapper.selectByPIId(pi.getId()) == null) {
                    list1.add(pi);
                }

            }
        }
        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到正在采购和驳回数据" + list1 + "条");
        msg.setDate(list1);
        msg.setStatus(1);
        return msg;
    }

    //采购申请的删除
    @Override
    public Message purchaseApplyDelete(int id) {
        int i = 0;
        PurchasingItems purchasing_items = purchasing_itemsMapper.selectByPrimaryKey(id);
        if (purchasing_items.getAcceess()>=1){
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("删除采购申请失败,已经在审核中");
            msg.setStatus(0);
            return  msg;
        }
        i = purchasing_itemsMapper.deleteByPrimaryKey(id);
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("删除采购申请成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("删除采购申请失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message purchaseApplyAllSelect(int id) {
        List<PurchasingItems> list = purchasing_itemsMapper.selectAll();
        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询成功，共" + list.size() + "条记录");
        msg.setDate(list);
        msg.setStatus(1);
        return msg;
    }

    //②时间选择器 和商品类别选择器
    @Override
    public Message purchaseApplyAllSelectFilter(TimeSelectDto tsd) {
        int time1 = Integer.parseInt(tsd.getT1());
        int time2 = Integer.parseInt(tsd.getT2());
        int time = 0;
        List<PurchasingItems> list = purchasing_itemsMapper.selectByTimeAndPidFilter(tsd);


        msg = new Message();
        msg.setSuccess(true);
        msg.setDate(list);
        msg.setMsg("查询成功，共" + list.size() + "条记录");

        msg.setStatus(1);
        return msg;


    }

    @Override
    public Message purchaseApplyResultFilter(TimeSelectDto tsd) {
        return null;
    }
//对他的采购的商品中标后通知他 信息的通知
    @Override
    public Message purchaseApplyResult(int id) {
        List<Result> list = resultMapper.selectByPurchaseId(id);
        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询成功，共" + list.size() + "条记录");
        msg.setDate(list);
        msg.setStatus(1);
        return msg;
    }

    @Override
    public Message InfoSelect(int id) {
        Purchaser purchaser = purchaserMapper.selectByPrimaryKey(id);
       List<Purchaser> list = new ArrayList<>();
       list.add(purchaser);

        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询成功，共" + list.size() + "条记录");
        msg.setDate(list);
        msg.setStatus(1);
        return msg;
    }

    @Override
    public Message InfoUpdate(Purchaser purchaser) {
        int i = 0;
        i = purchaserMapper.updateByPrimaryKeySelective(purchaser);
        List<Purchaser> list = new ArrayList<>();
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("采购者个人信息更新成功");
            list.add(purchaserMapper.selectByPrimaryKey(purchaser.getId()));
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("采购者个人信息更新失败");
        }
        return msg;
    }

    @Override
    public Message purchaseScreen(Selecte selecte) {

        
        List<PurchasingItems> purchasing_items = purchasing_itemsMapper.selectAll();
        List<PurchasingItems> purchasing_items1 = new ArrayList<>();
        int i = 0;
        //判断是否需要学院的筛选
        
        

        if (selecte.getAcademy()!= null) {
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
                int i1=0;
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
        for(PurchasingItems p:purchasing_items){
            if(p.getAcceess()==11&&p.getPurchase_name()!=null&&p.getPurchase_name().length()>0){
                purchasing_items1.add(p);
            }
        }
        purchasing_items.clear();
        for (PurchasingItems item : purchasing_items1
        ) {
            purchasing_items.add(item);
        }
        purchasing_items1.clear();
        
        

        
        if (selecte.getValue1().get(0) != null || selecte.getValue1().get(1) != null) {
            int m = selecte.getValue1().get(1).compareTo(selecte.getValue1().get(0));

            if(m!=0){
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

        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到" + purchasing_items.size() + "条记录");
        msg.setDate(purchasing_items);
        msg.setStatus(1);
        return msg;
    }

    @Override
    public Message purchaseScreen1(Selecte selecte) {

        
        List<PurchasingItems> purchasing_items = purchasing_itemsMapper.selectAll();
        List<PurchasingItems> purchasing_items1 = new ArrayList<>();
        int i = 0;
        //判断是否需要学院的筛选
        
        

        if (selecte.getAcademy()!= null) {
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
                int i1=0;
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


        purchasing_items1.clear();
        
        

        
        if (selecte.getValue1().get(0) != null || selecte.getValue1().get(1) != null) {
            int m = selecte.getValue1().get(1).compareTo(selecte.getValue1().get(0));

            if(m!=0){
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

        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到" + purchasing_items.size() + "条记录");
        msg.setDate(purchasing_items);
        msg.setStatus(1);
        return msg;
    }

    @Override
    public Message getPurchaseById(PurchasingItems pi) {
        List<Purchaser> purchasers = new ArrayList<>();
        Purchaser purchaser = purchaserMapper.selectByPrimaryKey(pi.getPid());
        purchasers.add(purchaser);
        msg.setSuccess(true);
        msg.setMsg("查询到" + purchasers.size() + "条记录");
        msg.setDate(purchasers);
        msg.setStatus(1); return msg;
    }
}
