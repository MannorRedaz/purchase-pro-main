package cn.mono.purchase.mapper;


import cn.mono.purchase.pojo.Purchaser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaserMapper {

    //通过学院外键查询
    //Purchaser selectByForeignKey(int cid);
    Purchaser selectByCid(int cid);
    //通过名字查询
    Purchaser selectByName(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(Purchaser record);

    int insertSelective(Purchaser record);

    Purchaser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Purchaser record);

    int updateByPrimaryKey(Purchaser record);
}
