package cn.mono.purchase.mapper;


import cn.mono.purchase.pojo.Supper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SupperMapper {

    Supper selectByName(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(Supper record);

    int insertSelective(Supper record);

    Supper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Supper record);

    int updateByPrimaryKey(Supper record);
}