package cn.mono.purchase.service.impl;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.mapper.*;
import cn.mono.purchase.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nihao
 * @time 2021/5/15
 */
@Service
public class NotifyServiceImpl  implements NotifyService {
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
}
