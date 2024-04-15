package cn.mono.purchase.service.impl;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.PreAdmin;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.mapper.*;
import cn.mono.purchase.pojo.*;

import cn.mono.purchase.service.SchoolAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author nihao
 * @time 2021/4/16
 * school_administrator学校管理员
 */
@Service
public class SchoolAdminServiceImpl implements SchoolAdminService {
    @Autowired
    Bidding_applicationMapper bidding_applicationMapper;
    @Autowired
    Apply_for_shortlistMapper apply_for_shortlistMapper;
    @Autowired
    ResultMapper resultMapper;
    @Autowired
    SupplierMapper supplierMapper;
    @Autowired
    School_administratorMapper school_administratorMapper;
    @Autowired
    PresidentMapper presidentMapper;
    @Autowired
    PurchaserMapper purchaserMapper;
    @Autowired
    Academy_categoryMapper academy_categoryMapper;
    @Autowired
    Purchasing_itemsMapper purchasing_itemsMapper;
    @Autowired
    Product_categoryMapper product_categoryMapper;

    @Autowired
    Apply_boardMapper apply_boardMapper;
    Message msg;

    @Override
    public Message selectSchoolAdminByName(String name) {
        return null;
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
    public Message infoUpdate(SchoolAdministrator sa) {
        int i = 0;
        i = school_administratorMapper.updateByPrimaryKey(sa);
        List<Supplier> list = new ArrayList<>();
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("用户个人信息更新成功");
            list.add(supplierMapper.selectByPrimaryKey(sa.getId()));
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
    public Message selectSearchByAcademyName(String name) {
        int i = 0;
        List<President> list = presidentMapper.selectPresidentListByName(name);
        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到" + list.size() + "条数据");
        msg.setDate(list);
        msg.setStatus(1);
        return msg;
    }

    @Override
    public Message SearchByAcademyName(String name) {
        int i = 0;
        List<AcademyCategory> listAca = academy_categoryMapper.selectAcademyListByName(name);
        List<President> listRes = new ArrayList<>();
        for (AcademyCategory a : listAca) {
            List<President> list = presidentMapper.selectByCid(a.getCid());
            for (President president : list) {
                listRes.add(president);
            }
        }

        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到" + listRes.size() + "条数据");
        msg.setDate(listRes);
        msg.setStatus(1);
        return msg;
    }

    @Override
    public Message addPresidentAdmin(PreAdmin preAdmin) {
        int p = 0;

        AcademyCategory academy_category = new AcademyCategory();
        academy_category.setAcademy_name(preAdmin.getAcademy_name());


        academy_category.setSid(preAdmin.getSid());

        academy_category.setBudget(preAdmin.getBudget());
        academy_category.setMaximum_order(preAdmin.getMaximum_order());
        try {
            p = academy_categoryMapper.insertSelective(academy_category);
        } catch (Exception e) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("添加失败");
            msg.setStatus(0);
            return msg;
        }
        try {
            academy_category = academy_categoryMapper.selectByName(preAdmin.getAcademy_name());
        } catch (Exception e) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("学院已经存在");
            msg.setStatus(0);
            return msg;
        }

        President president = new President();
        president.setCid(academy_category.getCid());
        president.setContract_name(preAdmin.getContract_name());
        president.setCount(preAdmin.getCount());
        president.setName(preAdmin.getName());
        president.setPwd("123456");
        president.setSid(preAdmin.getSid());
        president.setTel(preAdmin.getTel());

        president.setLast_time(new Date());
        int i = 0;
        try {
            i = presidentMapper.insertSelective(president);
        } catch (Exception e) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("添加失败");
            msg.setStatus(0);
            return msg;
        }


        try {
            presidentMapper.selectByName(president.getName());
        } catch (Exception e) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("用户名重复");
            msg.setStatus(0);
            return msg;

        }

        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("添加成功");
            List<President> list = new ArrayList<>();
            list.add(presidentMapper.selectByName(president.getName()));
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("添加失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message resetPresidentPwd(int id) {
        President president = presidentMapper.selectByPrimaryKey(id);

        president.setPwd("111111");
        int i = presidentMapper.updateByPrimaryKey(president);
        if (i > 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("重置密码成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("重置密码失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message deletePresident(int id) {
        int i = 0;
        try {

            i = presidentMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("删除失败");
            msg.setStatus(0);
        }
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("删除账号成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("删除失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message searchPurchaserByAcademyName(String name) {

        int i = 0;
        List<AcademyCategory> listAca = academy_categoryMapper.selectAcademyListByName(name);
        List<Purchaser> listRes = new ArrayList<>();
        Purchaser purchaser;
        for (AcademyCategory a : listAca) {
            purchaser = purchaserMapper.selectByCid(a.getCid());
            if (purchaser != null) {
                listRes.add(purchaser);
            }
        }
        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到" + listRes.size() + "条数据");
        msg.setDate(listRes);
        msg.setStatus(1);
        return msg;
    }

    @Override
    public Message addPurchaserAdmin(PreAdmin preAdmin) {
        AcademyCategory academy_category = academy_categoryMapper.selectByName(preAdmin.getAcademy_name());

        if (purchaserMapper.selectByCid(academy_category.getCid()) != null) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("该学院已经有实验室管理员了");
            msg.setStatus(1);
            return msg;
        }


        Purchaser purchaser = new Purchaser();
        purchaser.setCid(academy_category.getCid());
        purchaser.setContract_name(preAdmin.getContract_name());
        purchaser.setCount(preAdmin.getCount());
        purchaser.setName(preAdmin.getName());
        purchaser.setSid(1);

        President president = presidentMapper.selectByCid(academy_category.getCid()).get(0);
        purchaser.setPrid(president.getId());
        purchaser.setPwd("123456");
        purchaser.setTel(preAdmin.getTel());

        int i = purchaserMapper.insertSelective(purchaser);
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("添加成功");
            List<President> list = new ArrayList<>();
            list.add(presidentMapper.selectByName(purchaser.getName()));
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("添加失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message deletePurchaser(int id) {
        int i = 0;
        i = purchaserMapper.deleteByPrimaryKey(id);
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("删除账号成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("删除失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message resetPurchaserPwd(int id) {
        Purchaser purchaser = purchaserMapper.selectByPrimaryKey(id);

        purchaser.setPwd("111111");
        int i = purchaserMapper.updateByPrimaryKey(purchaser);
        if (i > 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("重置密码成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("重置密码失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message getSupApplyList(String name) {
        List<ApplyForShortlist> list = apply_for_shortlistMapper.selectAll();

        msg = new Message();
        msg.setSuccess(true);
        msg.setDate(list);
        msg.setMsg("查询到" + list.size() + "条数据");
        msg.setStatus(1);

        return msg;
    }

    @Override
    public Message getSupplierList() {
        List<Supplier> list = supplierMapper.selectAll();

        msg = new Message();
        msg.setSuccess(true);
        msg.setDate(list);
        msg.setMsg("查询到" + list.size() + "条数据");
        msg.setStatus(1);

        return msg;
    }

    @Override
    public Message supplyApprove(int id) {

        ApplyForShortlist i = apply_for_shortlistMapper.selectByPrimaryKey(id);
        i.setSchool_administrator_access(1);
        i.setAcademy_access("1");
        Supplier supplier = supplierMapper.selectByPrimaryKey(i.getSid());
        supplier.setAccess(1);
        int m = supplierMapper.updateByPrimaryKey(supplier);
        int p = apply_for_shortlistMapper.updateByPrimaryKey(i);
        if (p == 1 && m == 1) {
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
    public Message getPurchaseList() {

        List<PurchasingItems> purchasing_items = purchasing_itemsMapper.selectAll();
        List<PurchasingItems> list = new ArrayList<>();

        for (PurchasingItems p :
                purchasing_items) {
            AcademyCategory academy_category = academy_categoryMapper.selectByPrimaryKey(
                    p.getCid()
            );

            if (p.getAcceess() == 1 || p.getAcceess() == 11) {
                //if(academy_category.getMaximum_order()>p.getHighest_price())
                list.add(p);
            }
        }
        if (purchasing_items.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("获取采购列表成功");
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
    public Message purchaseApprove(int id) {
        PurchasingItems purchasing_items = purchasing_itemsMapper.selectByPrimaryKey(id);
        AcademyCategory academy_category = academy_categoryMapper.selectByPrimaryKey(purchasing_items.getCid());

//        if(purchasing_items.getHighest_price()>academy_category.getMaximum_order()){
//            purchasing_items.setAcceess(1);
//        }else
//        {
        purchasing_items.setAcceess(11);

//        }
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
    public Message selectDetail(Selecte selecte) {
        //查询历史订单


        List<PurchasingItems> purchasing_items = purchasing_itemsMapper.selectAll();
        List<PurchasingItems> purchasing_items1 = new ArrayList<>();

//只要有结果的
        for (PurchasingItems item : purchasing_items) {
            try {
                if (item.getIs_result() == 1) {

                    purchasing_items1.add(item);
                }
            } catch (Exception e) {

            }
        }
        purchasing_items.clear();
        for (PurchasingItems item : purchasing_items1
        ) {
            purchasing_items.add(item);
        }
        purchasing_items1.clear();
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

        //list list1 null 数组越界get(0)

//这里有问题
        if (selecte.getValue1().size() > 0) {


            for (PurchasingItems item : purchasing_items
            ) {
                if (item == null) {
                    break;
                }
                int res2 = 0;
                try {
                    res2 = item.getPublish_time().compareTo(selecte.getValue1().get(0));
                } catch (Exception e) {
                    msg = new Message();
                    msg.setSuccess(true);
                    msg.setMsg("没有订单");
                    msg.setStatus(0);
                    msg.setDate(purchasing_items);
                    return msg;
                }
                if (res2 >= 0) {
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


        Result result = null;
        double count = 0;
        for (PurchasingItems item : purchasing_items
        ) {
            result = resultMapper.selectByPIId(item.getId());
            if (result != null) {
                count = count + result.getReality_price();//17900
            }
        }

        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到" + purchasing_items.size() + "条记录");
        msg.setStatus((int) count);
        msg.setDate(purchasing_items);


        return msg;
    }

    @Override
    public Message getPurchaseList1() {
        List<PurchasingItems> purchasing_items = purchasing_itemsMapper.selectAll();
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
    public Message deleteApplyBoard(int id) {
        try {
            apply_boardMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("删除失败");
            return msg;
        }
        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("删除成功");
        msg.setStatus(1);
        return msg;
    }

    @Override
    public Message addApplyBoard(ApplyBoard ab) {
        try {
            apply_boardMapper.insert(ab);
        } catch (Exception e) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("添加失败");
            return msg;
        }
        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("添加成功");
        msg.setStatus(1);
        return msg;
    }

}
