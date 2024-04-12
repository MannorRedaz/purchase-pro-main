package cn.mono.purchase.mapper;



import cn.mono.purchase.dto.TimeSelectDto;
import cn.mono.purchase.pojo.PurchasingItems;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Purchasing_itemsMapper {

    List<PurchasingItems> selectByCid(Integer cid);

    List<PurchasingItems> selectByTimeAndPidFilter(TimeSelectDto tsd);
    List<PurchasingItems> selectAll();
    PurchasingItems selectLastInsert();
    int deleteByPrimaryKey(Integer id);

    int insert(PurchasingItems record);

    int insertSelective(PurchasingItems record);

    PurchasingItems selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchasingItems record);

    int updateByPrimaryKey(PurchasingItems record);
}