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

    public Message supplierApply(BiddingApplication ba);


    public Message supApplys(int id) ;

    public Message applyUpdate(BiddingApplication ba) ;


    public Message applySelect(String t1,String t2,int sid);

    public Message applyDelete (int id);


    public Message supResult(int sid) ;


    public Message infoUpdate(Supplier sup);


    public Message applyShortList(ApplyForShortlist afs);
    public Message applyShortListUpdate(ApplyForShortlist afs);

    public Message applyShortListSelect(String t1,String t2,int sid);

    public Message applyShortListDelete (int id);

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
