package com.zengfanfan.crm.dao;

import com.zengfanfan.crm.bean.Department;
import com.zengfanfan.crm.bean.Goodscategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectall(@Param("name") String name);
}