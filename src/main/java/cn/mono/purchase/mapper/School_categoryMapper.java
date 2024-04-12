package cn.mono.purchase.mapper;



import cn.mono.purchase.pojo.SchoolAdministrator;
import cn.mono.purchase.pojo.SchoolCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface School_categoryMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(SchoolCategory record);

    int insertSelective(SchoolCategory record);

    SchoolCategory selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(SchoolCategory record);

    int updateByPrimaryKey(SchoolCategory record);

    List<SchoolAdministrator> selectAll();

    SchoolCategory selectByName(String schoolName);

    List<SchoolCategory> selectSchoolByName(String school_name);
}