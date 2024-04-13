package cn.mono.purchase.service;


import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.pojo.ApplyForShortlist;
import cn.mono.purchase.pojo.BiddingApplication;
import cn.mono.purchase.pojo.President;

/**
 * @author nihao
 * @time 2021/4/16
 */
public interface PresidentService {
    //查询预算
    Message getBudget(int cid);

    // 查询已经使用的钱
    Message getUsedMoney(int cid);

    Message supplierApply(BiddingApplication ba);


    Message supApplys(int id);

    Message applyUpdate(BiddingApplication ba);


    Message applySelect(String t1, String t2, int sid);

    Message applyDelete(int id);


    Message supResult(int sid);


    Message infoUpdate(President president);


    Message applyShortList(ApplyForShortlist afs);

    Message applyShortListUpdate(ApplyForShortlist afs);

    Message applyShortListSelect(String t1, String t2, int sid);

    Message applyShortListDelete(int id);

    Message getPurchaseList(int cid);

    Message selectDetail(Selecte selecte);

    Message getPurchaseList1(int cid);

    Message purchaseApprove(int id);

    Message purchaseScreen(Selecte selecte);
}
