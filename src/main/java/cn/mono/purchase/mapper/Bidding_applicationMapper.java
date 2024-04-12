package cn.mono.purchase.mapper;


import cn.mono.purchase.pojo.BiddingApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface Bidding_applicationMapper {
    //查询某个供应商的申请详情
    List<BiddingApplication> getBidList(int sid);
    int deleteByPrimaryKey(Integer id);

    int insert(BiddingApplication record);

    int insertSelective(BiddingApplication record);

    BiddingApplication selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BiddingApplication record);

    int updateByPrimaryKey(BiddingApplication record);

    List<BiddingApplication> selectBySupplierId(Integer id);
    List<BiddingApplication> selectBySid(Integer id);
    List<BiddingApplication> selectByPurchaingItemId(Integer purid);

    int getApplyCount(int purid);
}