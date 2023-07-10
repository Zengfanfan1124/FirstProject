package com.zengfanfan.crm.dao;

import com.zengfanfan.crm.bean.Department;
import com.zengfanfan.crm.bean.Linkman;
import io.swagger.v3.oas.models.links.Link;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinkmanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Linkman record);

    int insertSelective(Linkman record);

    Linkman selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Linkman record);

    int updateByPrimaryKey(Linkman record);

    List<Linkman> selectall(@Param("name") String name);

}