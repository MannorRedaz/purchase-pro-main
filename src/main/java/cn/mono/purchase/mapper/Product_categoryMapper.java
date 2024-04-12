package cn.mono.purchase.mapper;


import cn.mono.purchase.pojo.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface Product_categoryMapper {
    List<ProductCategory> selectAll();

    int deleteByPrimaryKey(Integer pid);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);

    ProductCategory selectByName(String name);
}