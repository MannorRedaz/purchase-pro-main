package cn.mono.purchase.service.impl;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.mapper.*;
import cn.mono.purchase.pojo.*;

import cn.mono.purchase.service.PresidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author nihao
 * @time 2021/4/16
 */
@Service
public class PresidentServiceImpl implements PresidentService {
    @Autowired
    Bidding_applicationMapper bidding_applicationMapper;
    @Autowired
    Apply_for_shortlistMapper apply_for_shortlistMapper;
    @Autowired
    ResultMapper resultMapper;
    @Autowired
    SupplierMapper supplierMapper;
    @Autowired
    Academy_categoryMapper academy_categoryMapper;
    @Autowired
    PurchaserMapper purchaserMapper;
    @Autowired
    Purchasing_itemsMapper purchasing_itemsMapper;
    @Autowired
    PresidentMapper presidentMapper;

    Message msg;

    @Override
    public Message getBudget(int cid) {
        AcademyCategory academy_categories = academy_categoryMapper.selectByPrimaryKey(cid);
        List<AcademyCategory> list = new ArrayList<>();
        list.add(academy_categories);
        if (list.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("获取结果列表成功");
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("获取结果列表失败");
        }
        return msg;
    }

    @Override
    public Message getUsedMoney(int cid) {
        //Purchaser purchaser = purchaserMapper.selectByCid(cid);
        AcademyCategory academy_category = academy_categoryMapper.selectByPrimaryKey(cid);
        PurchasingItems purchasing_items = null;
        //List<Result> list = resultMapper.selectByPurchaseId(purchaser.getId());
        List<Result> list = resultMapper.selectAll();
        int j = 0;
        for (Result i :
                list) {
            purchasing_items = purchasing_itemsMapper.selectByPrimaryKey(i.getPid());
            if (purchasing_items.getCid() == cid)
                j = j + i.getReality_price();
        }
        if (list.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("获取结果列表成功");
            msg.setStatus(j);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("获取结果列表失败");
        }
        return msg;
    }


    //申请竞标接口
    @Override
    public Message supplierApply(BiddingApplication ba) {
        int i = 0;
        i = bidding_applicationMapper.insert(ba);
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("申请成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setStatus(0);
            msg.setMsg("申请失败");
        }
        return msg;
    }

    //供应商申请列表接口
    @Override
    public Message supApplys(int sid) {
        List<BiddingApplication> bidList = bidding_applicationMapper.getBidList(sid);
        if (bidList.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("获取申请列表成功");
            msg.setDate(bidList);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("获取申请列表失败");
        }
        return msg;
    }

    //供应商申请列表更新
    @Override
    public Message applyUpdate(BiddingApplication ba) {
        int i = 0;
        i = bidding_applicationMapper.updateByPrimaryKey(ba);
        List<BiddingApplication> list = null;
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("更新成功");
            list.add(bidding_applicationMapper.selectByPrimaryKey(ba.getId()));
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("更新失败");
        }
        return msg;
    }

    //申请筛选
    @Override
    public Message applySelect(String t1, String t2, int sid) {
        int time1 = Integer.parseInt(t1);
        int time2 = Integer.parseInt(t2);
        int time = 0;
        List<BiddingApplication> list = bidding_applicationMapper.getBidList(sid);

        List<BiddingApplication> list2 = null;
//        for (Bidding_application i :
//               list ) {
//            time = Integer.parseInt(i.getData());
//            if (time<time2&&time>time1){
//                list2.add(i);
//            }
//        }
        if (list2.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("查询成功");
            msg.setDate(list2);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("未查询到相关信息");
            msg.setStatus(0);

        }
        return msg;
    }

    @Override
    public Message applyDelete(int id) {
        int i = 0;
        i = bidding_applicationMapper.deleteByPrimaryKey(id);

        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("删除申请成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("删除申请失败");
            msg.setStatus(0);
        }
        return msg;
    }

    //供应商申请结果接口
    @Override
    public Message supResult(int sid) {
        List<Result> results = resultMapper.getSupResultList(sid);
        if (results.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("获取供应商结果列表成功");
            msg.setDate(results);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("获取供应商结果列表失败");
        }
        return msg;

    }

    //供应商个人信息修改
    @Override
    public Message infoUpdate(President president) {
        int i = 0;
        i = presidentMapper.updateByPrimaryKey(president);
        List<President> list = null;
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("用户个人信息更新成功");
            list.add(presidentMapper.selectByPrimaryKey(president.getId()));
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("用户个人信息更新失败");
        }
        return msg;
    }

    //申请入围
    @Override
    public Message applyShortList(ApplyForShortlist afs) {
        int i = 0;
        i = apply_for_shortlistMapper.insert(afs);
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("申请成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setStatus(0);
            msg.setMsg("申请失败");
        }
        return msg;
    }

    @Override
    public Message applyShortListUpdate(ApplyForShortlist afs) {
        int i = 0;
        i = apply_for_shortlistMapper.updateByPrimaryKey(afs);
        List<BiddingApplication> list = null;
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("更新成功");
            list.add(bidding_applicationMapper.selectByPrimaryKey(afs.getId()));
            msg.setDate(new ArrayList<BiddingApplication>());
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("更新失败");
        }
        return msg;
    }

    @Override
    public Message applyShortListSelect(String t1, String t2, int sid) {
//        int time1 =Integer.parseInt(t1);
//        int time2 =Integer.parseInt(t2);
//        int time=0;
//        List<Apply_for_shortlist> list  = apply_for_shortlistMapper.getApplyShortList(sid);
//
//        List<Apply_for_shortlist> list2=null;
//        for (Apply_for_shortlist i :
//                list ) {
//            time = Integer.parseInt(i.getDate());
//            if (time<time2&&time>time1){
//                list2.add(i);
//            }
//        }
//        if(list2.size()!=0){
//            msg=new Message();
//            msg.setSuccess(true);
//            msg.setMsg("查询成功");
//            msg.setDate(list2);
//            msg.setStatus(1);
//        }else {
//            msg=new Message();
//            msg.setSuccess(false);
//            msg.setMsg("未查询到相关信息");
//            msg.setStatus(0);
//        }
//        return msg;
        return null;
    }

    @Override
    public Message applyShortListDelete(int id) {
        int i = 0;
        i = apply_for_shortlistMapper.deleteByPrimaryKey(id);

        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("删除申请成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("删除申请失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message getPurchaseList(int cid) {//获得list
        List<PurchasingItems> purchasing_items = purchasing_itemsMapper.selectByCid(cid);
        List<PurchasingItems> list = new ArrayList<>();

        for (PurchasingItems p :
                purchasing_items) {
            if (p.getIs_result() != null) {
                list.add(p);
            }
        }
        if (purchasing_items.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("查询到" + purchasing_items.size() + "条数据");
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("获取采购列表失败");
        }
        return msg;
    }

    @Override
    public Message selectDetail(Selecte selecte) {

        System.out.println(selecte.toString());
        List<PurchasingItems> purchasing_items = purchasing_itemsMapper.selectAll();
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

        //list list1 null
        if (selecte.getValue1().get(0) != null) {

            Date c = new Date();
            c.setYear(selecte.getValue1().get(0).getYear() + 1);
            c.setMonth(selecte.getValue1().get(0).getMonth());

            c.setDate(selecte.getValue1().get(0).getDate());
            //有的并不是公告，还没有发布时间
//过滤
            for (PurchasingItems p : purchasing_items
            ) {
                if (p.getAcceess() == 11 && p.getPurchase_name() != null && p.getPurchase_name().length() > 0) {
                    System.out.println("p.getPurchase_name()" + p.getPurchase_name());
                    purchasing_items1.add(p);
                }
            }

            purchasing_items.clear();
            for (PurchasingItems item : purchasing_items1
            ) {
                purchasing_items.add(item);
            }
            purchasing_items1.clear();

            for (PurchasingItems item : purchasing_items
            ) {
                int res2 = item.getPublish_time().compareTo(selecte.getValue1().get(0));
                int res1 = item.getPublish_time().compareTo(c);
                System.out.println(c);
                System.out.println(res1);
                System.out.println(res2);
                System.out.println(c); //2020 2021 res2 2020 0 res1 2021 1
                if (res2 >= 0 && res1 <= 0) {
                    purchasing_items1.add(item);
                }
            }

            purchasing_items.clear();
            for (PurchasingItems item : purchasing_items1
            ) {
                purchasing_items.add(item);
            }
            purchasing_items1.clear();

        }

        //只要有结果的
        for (PurchasingItems item : purchasing_items
        ) {
            if (item.getIs_result() == 1) {
                purchasing_items1.add(item);
            } else {

            }
        }
        purchasing_items.clear();
        for (PurchasingItems item : purchasing_items1
        ) {
            purchasing_items.add(item);
        }
        purchasing_items1.clear();

        Result result = null;
        double count = 0;
        for (PurchasingItems item : purchasing_items
        ) {
            result = resultMapper.selectByPIId(item.getId());
            if (result != null)
                count = count + result.getReality_price();//17900
        }
        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到" + purchasing_items.size() + "条记录");
        msg.setStatus((int) count);
        msg.setDate(purchasing_items);
        return msg;
    }

    @Override
    public Message getPurchaseList1(int cid) {
        List<PurchasingItems> purchasing_items = purchasing_itemsMapper.selectByCid(cid);
        List<PurchasingItems> list = new ArrayList<>();
        for (PurchasingItems p :
                purchasing_items) {
            list.add(p);
        }
        if (purchasing_items.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("获取采购列表成功");
            msg.setDate(list);
            msg.setStatus(1);
        }
        return msg;
    }

    @Override
    public Message purchaseApprove(int id) {
        PurchasingItems purchasing_items = purchasing_itemsMapper.selectByPrimaryKey(id);
        AcademyCategory academy_category = academy_categoryMapper.selectByPrimaryKey(
                purchasing_items.getCid()
        );

        if (purchasing_items.getHighest_price() < academy_category.getMaximum_order()) {
            purchasing_items.setAcceess(11);
        } else {
            purchasing_items.setAcceess(1);
        }
        int p = purchasing_itemsMapper.updateByPrimaryKey(purchasing_items);

        if (p == 1) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("已同意该申请");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("操作失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message purchaseScreen(Selecte selecte) {
        System.out.println(selecte.toString());
        List<PurchasingItems> purchasing_items = purchasing_itemsMapper.selectAll();
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


        //list list1 null
        System.out.println(selecte.getValue1());
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

        if (selecte.getValue1().get(0) != null || selecte.getValue1().get(1) != null) {
            int m = selecte.getValue1().get(1).compareTo(selecte.getValue1().get(0));

            if (m != 0) {
                if (selecte.getValue1().get(0) == null) {
                    for (PurchasingItems item : purchasing_items
                    ) {
                        int res2 = item.getApply_time().compareTo(selecte.getValue1().get(0));
                        if (res2 >= 0) {
                            purchasing_items1.add(item);
                        }
                    }
                } else if (selecte.getValue1().get(0) == null) {
                    for (PurchasingItems item : purchasing_items
                    ) {
                        int res = item.getApply_time().compareTo(selecte.getValue1().get(1));
                        if (res <= 0) {
                            purchasing_items1.add(item);
                        }
                    }

                } else {
                    for (PurchasingItems item : purchasing_items
                    ) {
                        int res = item.getApply_time().compareTo(selecte.getValue1().get(1));
                        int res2 = item.getApply_time().compareTo(selecte.getValue1().get(0));
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

}
