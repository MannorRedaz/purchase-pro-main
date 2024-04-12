package cn.mono.purchase.mapper;


import cn.mono.purchase.pojo.Supplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SupplierMapper {
    //通过名字查询
    Supplier selectByName(String name);
    //
    List<Supplier> selectAll();
    int deleteByPrimaryKey(Integer id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}