package mapper;

import pojo.Purchasing_items;
import pojo.Result;

import java.util.List;

public interface ResultMapper {
    List<Result> selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Result record);

    int insertSelective(Result record);

    Result selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);
}