package com.zengfanfan.crm.dao;

import com.zengfanfan.crm.bean.Goodscategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodscategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goodscategory record);

    int insertSelective(Goodscategory record);

    Goodscategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goodscategory record);

    int updateByPrimaryKey(Goodscategory record);

    List<Goodscategory> selectall(@Param("name") String name);
}