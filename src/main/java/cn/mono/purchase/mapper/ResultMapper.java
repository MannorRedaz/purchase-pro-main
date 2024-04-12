package cn.mono.purchase.mapper;


import cn.mono.purchase.pojo.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResultMapper {

    //查询每个学院的的结果集
    List<Result> selectByPurchaseId(int Purchase_id);

    //查询某个供应商的申请详情
    List<Result> getSupResultList(int sid);

    //查询所有结果
    List<Result> selectAll();

    List<Result> selectBySupplierId(int sid);
    //通过产品外键查询
    Result selectByPIId(Integer pid);

    int deleteByPrimaryKey(Integer id);

    int insert(Result record);

    int insertSelective(Result record);

    Result selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);
}