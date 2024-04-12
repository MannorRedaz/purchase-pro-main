package cn.mono.purchase.mapper;


import cn.mono.purchase.pojo.ApplyBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Apply_boardMapper {


    List<ApplyBoard> selectAll();
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyBoard record);

    int insertSelective(ApplyBoard record);

    ApplyBoard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyBoard record);

    int updateByPrimaryKey(ApplyBoard record);
}