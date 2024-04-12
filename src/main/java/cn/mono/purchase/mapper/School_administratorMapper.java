package cn.mono.purchase.mapper;


import cn.mono.purchase.pojo.SchoolAdministrator;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface School_administratorMapper {


    List<SchoolAdministrator> selectSchoolAdminByName(String name);

    SchoolAdministrator selectByName(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(SchoolAdministrator record);

    int insertSelective(SchoolAdministrator record);

    SchoolAdministrator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SchoolAdministrator record);

    int updateByPrimaryKey(SchoolAdministrator record);
}
