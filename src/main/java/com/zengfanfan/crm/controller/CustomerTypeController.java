package com.zengfanfan.crm.controller;

import com.zengfanfan.crm.bean.Customertype;
import com.zengfanfan.crm.bean.ResultBean;
import com.zengfanfan.crm.dao.CustomertypeMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customertype")
@Api(value = "客户类型",tags = "customertype")
public class CustomerTypeController {
    @Autowired
    private CustomertypeMapper mapper;

    @PostMapping("save")
    @ApiOperation(value = "客户类型, 保存",tags = "customerType")
    public Object save(@RequestBody Customertype customerType){
        int rows=0;
        if(customerType.getId()!=null)
        {
            rows=mapper.updateByPrimaryKeySelective(customerType);
        }
        else
        {
            rows=mapper.insertSelective(customerType);
        }

        ResultBean bean=null;
        if(rows>=1)
        {
            bean=new ResultBean();
            bean.setObject(customerType);
            return bean;
        }
        else {
            bean=new ResultBean(ResultBean.ResultType.FAIL);
            return bean;
        }

    }

    @GetMapping("delete")
    @ApiOperation(value = "客户类型, 删除",tags = "customerType")
    public Object delete(Integer id){
        int rows=mapper.deleteByPrimaryKey(id);
        ResultBean bean=null;

        if(rows>=1)
        {
            bean=new ResultBean();
            return bean;
        }
        else {
            bean=new ResultBean(ResultBean.ResultType.FAIL);
            return bean;
        }

    }

    @GetMapping("select")
    @ApiOperation(value = "客户类型, 查询",tags = "customerType")
    public Object select(String name){
        ResultBean bean=null;
        try{
            List<Customertype> list = mapper.selectall(name);
            bean=new ResultBean();
            bean.setObject(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            bean=new ResultBean(ResultBean.ResultType.FAIL);
            bean.setMessage("查询客户类型异常，请联系管理局");
        }

        return bean;
    }
}
