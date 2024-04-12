package cn.mono.purchase.mapper;


import cn.mono.purchase.pojo.AcademyCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Academy_categoryMapper {
    List<AcademyCategory> selectAcademyListByName(String name) ;
    List<AcademyCategory> selectAll();

    int deleteByPrimaryKey(Integer cid);

    int insert(AcademyCategory record);

    int insertSelective(AcademyCategory record);

    AcademyCategory selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(AcademyCategory record);

    int updateByPrimaryKey(AcademyCategory record);

    AcademyCategory selectByName(String name);
}