package cn.mono.purchase.service;


import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.pojo.BiddingApplication;
import cn.mono.purchase.pojo.PurchasingItems;

/**
 * @author nihao
 * @time 2021/4/14
 */
public interface BaseAcademyService {
    /**
     * 获取产品种类
     * @return
     */
    Message GetProductCategory();

    /**
     * 获取学院种类
     * @return
     */
    Message  GetAcademyCategory();
    /**
     * 获取结果集合
     * @return
     * @return
     */
    Message  GetResultCategory();
    /**
     * 获取采购列表
     * @return
     */
    Message  GetPurchasingCategory();

    /**
     * 获取采购列表 新
     * @return
     */
    Message  GetPurchasingCategory1();

    /**
     * 获取公告列表 新
     * @return
     */
    Message GetBoardList();

    /**
     * 获取某条公告具体内容 新
     * @return
     */
    Message GetBoard(int id);

    Message updateBoard(PurchasingItems purchasing_items);

    Message getResult(int id);

    Message getSupplier(int id);

    String getFileName(int id);

    Message boardScreen(Selecte selecte);

    Message addPurBoard(PurchasingItems purchasing_items);

    Message getBoardListByCid(int cid);

    Message editPurBoard(PurchasingItems purchasing_items);

    Message deletePurBoard(PurchasingItems purchasing_items);

   String getSupplyFileName(int id);
    String getPurchaseFileName(int id);
    Message getApplyCount(int id);

    Message getMinimumPrice(int id);

    Message addResultBoard(int id);

    Message getAllBoardListByCid(int cid);

    Message boardScreen1(Selecte selecte);

    Message getChartList(int id);

    Message getChartAllList();

    Message getChartSupplier();

    Message getSupplierChart(int id);

    Message getApplyList(int id);

    Message addResBoard(BiddingApplication apply_for_shortlistMapper);
}
