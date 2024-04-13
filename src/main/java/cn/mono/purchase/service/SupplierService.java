package cn.mono.purchase.service;


import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.pojo.ApplyForShortlist;
import cn.mono.purchase.pojo.BiddingApplication;
import cn.mono.purchase.pojo.Supplier;

/**
 * @author nihao
 * @time 2021/4/16
 */
public interface SupplierService {

    Message supplierApply(BiddingApplication ba);


    Message supApplys(int id);

    Message applyUpdate(BiddingApplication ba);


    Message applySelect(String t1, String t2, int sid);

    Message applyDelete(int id);


    Message supResult(int sid);


    Message infoUpdate(Supplier sup);


    Message applyShortList(ApplyForShortlist afs);

    Message applyShortListUpdate(ApplyForShortlist afs);

    Message applyShortListSelect(String t1, String t2, int sid);

    Message applyShortListDelete(int id);

    Message getApplyList(int id);

    Message getPurchaseList();

    Message deleteApply(BiddingApplication ba);

    Message getResultListBySupplierId(int sid);

    Message getApplyForList(int sid);

    Message applyListScreen(Selecte selecte);

    Message getApplyResultList(int sid);

    Message getSearchBidingList(Selecte selecte);

    Message getApplyBoard();

    Message getPurchaseMaxID();

}
