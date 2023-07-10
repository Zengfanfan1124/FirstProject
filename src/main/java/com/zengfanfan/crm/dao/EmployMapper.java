package com.zengfanfan.crm.dao;

import com.zengfanfan.crm.bean.Employ;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employ record);

    int insertSelective(Employ record);

    Employ selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employ record);

    int updateByPrimaryKey(Employ record);

    List<Employ> selectByName(@Param("name") String name);
}