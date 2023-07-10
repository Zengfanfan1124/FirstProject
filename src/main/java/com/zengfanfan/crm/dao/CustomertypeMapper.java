package com.zengfanfan.crm.dao;

import com.zengfanfan.crm.bean.Customertype;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomertypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customertype record);

    int insertSelective(Customertype record);

    Customertype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customertype record);

    int updateByPrimaryKey(Customertype record);

    List<Customertype> selectAllByTypeName(@Param("typeName") String typeName);

    List<Customertype> selectall(@Param("name") String name);
}