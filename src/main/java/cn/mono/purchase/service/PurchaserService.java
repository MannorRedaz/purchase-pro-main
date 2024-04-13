package cn.mono.purchase.service;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.dto.TimeSelectDto;
import cn.mono.purchase.pojo.Purchaser;
import cn.mono.purchase.pojo.PurchasingItems;


/**
 * @author nihao
 * @time 2021/4/16
 */
public interface PurchaserService {


    Message purchaseApply(PurchasingItems pi);

    Message purchaseApplyUpdate(PurchasingItems pi);

    Message purchaseApplySelect(int id);

    Message purchaseApplyDelete(int id);

    Message purchaseApplyAllSelect(int id);

    Message purchaseApplyAllSelectFilter(TimeSelectDto tsd);

    Message purchaseApplyResultFilter(TimeSelectDto tsd);

    Message purchaseApplyResult(int id);

    Message InfoSelect(int id);

    Message InfoUpdate(Purchaser purchaser);

    Message purchaseScreen(Selecte selecte);

    Message purchaseScreen1(Selecte selecte);

    Message getPurchaseById(PurchasingItems pi);
}
