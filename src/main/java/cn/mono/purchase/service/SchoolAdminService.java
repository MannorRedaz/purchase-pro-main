package cn.mono.purchase.service;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.PreAdmin;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.pojo.ApplyBoard;
import cn.mono.purchase.pojo.ApplyForShortlist;
import cn.mono.purchase.pojo.BiddingApplication;
import cn.mono.purchase.pojo.SchoolAdministrator;


/**
 * @author nihao
 * @time 2021/4/16
 */
public interface SchoolAdminService {

    Message selectSchoolAdminByName(String name);

    Message supplierApply(BiddingApplication ba);


    Message supApplys(int id);

    Message applyUpdate(BiddingApplication ba);


    Message applySelect(String t1, String t2, int sid);

    Message applyDelete(int id);


    Message supResult(int sid);


    Message infoUpdate(SchoolAdministrator sa);


    Message applyShortList(ApplyForShortlist afs);

    Message applyShortListUpdate(ApplyForShortlist afs);

    Message applyShortListSelect(String t1, String t2, int sid);

    Message applyShortListDelete(int id);

    Message selectSearchByAcademyName(String name);

    Message SearchByAcademyName(String name);

    Message addPresidentAdmin(PreAdmin preAdmin);

    Message resetPresidentPwd(int id);

    Message deletePresident(int id);

    Message searchPurchaserByAcademyName(String name);

    Message addPurchaserAdmin(PreAdmin preAdmin);

    Message deletePurchaser(int id);

    Message resetPurchaserPwd(int id);

    Message getSupApplyList(String name);

    Message getSupplierList();

    Message supplyApprove(int id);

    Message getPurchaseList();

    Message purchaseApprove(int id);

    Message selectDetail(Selecte selecte);

    Message getPurchaseList1();

    Message deleteApplyBoard(int id);

    Message addApplyBoard(ApplyBoard ab);
}
