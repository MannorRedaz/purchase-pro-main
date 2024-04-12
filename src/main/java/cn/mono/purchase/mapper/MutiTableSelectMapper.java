package cn.mono.purchase.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author nihao
 * @time 2021/3/24
 */
@Mapper
public interface MutiTableSelectMapper {
    String selectCheckByName(String name);
}
