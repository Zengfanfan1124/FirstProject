package com.zengfanfan.crm.controller;

import com.zengfanfan.crm.bean.Department;
import com.zengfanfan.crm.bean.Linkman;
import com.zengfanfan.crm.bean.ResultBean;
import com.zengfanfan.crm.dao.DepartmentMapper;
import com.zengfanfan.crm.dao.LinkmanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/linkman")
@Api(value = "linkman表", tags = "linkman")
public class LinkmanController {
    @Autowired
    private LinkmanMapper mapper;

    @PostMapping("save")
    @ApiOperation(value = "linkman表，保存", tags = "department")
    public Object save(@RequestBody Linkman linkman) {
        int rows = 0;
        if (linkman.getId() != null) {
            rows = mapper.updateByPrimaryKeySelective(linkman);
        } else {
            rows = mapper.insertSelective(linkman);
        }
        ResultBean bean = null;
        if (rows >= 1) {
            bean = new ResultBean(ResultBean.ResultType.SUCCESS);
            bean.setObject(linkman);
            return bean;
        } else {
            bean = new ResultBean(ResultBean.ResultType.FAIL);
            return bean;
        }
    }

    @GetMapping("delete")
    @ApiOperation(value = "linkman表，删除",tags = "linkman")
    public Object delete(Integer id){
        int rows = mapper.deleteByPrimaryKey(id);
        ResultBean bean = null;
        if(rows>=1){
            bean = new ResultBean(ResultBean.ResultType.SUCCESS);
            return bean;
        }
        else{
            bean = new ResultBean(ResultBean.ResultType.FAIL);
            return bean;
        }
    }

    @GetMapping("select")
    @ApiOperation(value = "linkman表，查询",tags = "linkman")
    public Object select(String name){
        ResultBean bean = null;
        try{
            List<Linkman> list = mapper.selectall(name);
            bean = new ResultBean();
            bean.setObject(list);
        }
        catch (Exception e) {
            e.printStackTrace();
            bean = new ResultBean(ResultBean.ResultType.FAIL);
            bean.setMessage("查询部门异常，请联系管理员");
        }
        return bean;
    }
}
