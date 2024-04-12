package mapper;

import org.apache.ibatis.annotations.Select;
import pojo.Purchasing_items;

import java.util.List;

public interface Purchasing_itemsMapper {
    List<Purchasing_items> selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Purchasing_items record);

    int insertSelective(Purchasing_items record);

    Purchasing_items selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Purchasing_items record);

    int updateByPrimaryKey(Purchasing_items record);
}