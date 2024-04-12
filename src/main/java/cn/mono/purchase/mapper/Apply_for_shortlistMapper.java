package cn.mono.purchase.mapper;


import cn.mono.purchase.pojo.ApplyForShortlist;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface Apply_for_shortlistMapper {
    List<ApplyForShortlist> selectAll();

    List<ApplyForShortlist> getApplyShortList(int sid);
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyForShortlist record);

    int insertSelective(ApplyForShortlist record);

    ApplyForShortlist selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyForShortlist record);

    int updateByPrimaryKey(ApplyForShortlist record);

    ApplyForShortlist getApplyForList(int sid);

    int deleteBySid(int id);

    ApplyForShortlist selectBySupplierId(Integer sid);
}