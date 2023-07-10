package com.zengfanfan.crm.controller;


import com.zengfanfan.crm.bean.Goods;
import com.zengfanfan.crm.bean.ResultBean;
import com.zengfanfan.crm.dao.GoodsMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("Goods")
@Api(value = "货物",tags = "goods")
public class GoodsController {
    @Autowired
    private GoodsMapper mapper;


    @PostMapping("save")
    @ApiOperation(value = "货物, 保存",tags = "goods")
    public Object save(@RequestBody Goods goods){
        int rows=0;
        if(goods.getId()!=null)
        {
            rows=mapper.updateByPrimaryKeySelective(goods);
        }
        else {
            rows= mapper.insertSelective(goods);
        }

        ResultBean bean=null;
        if(rows>=1)
        {
            bean=new ResultBean();
            bean.setObject(goods);
            return bean;
        }
        else {
            bean=new ResultBean(ResultBean.ResultType.FAIL);

            return bean;
        }

    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
    }
    @GetMapping("delete")
    @ApiOperation(value = "货物, 删除",tags = "goods")
    public Object save(Integer id){
        int rows=mapper.deleteByPrimaryKey(id);
        ResultBean bean=null;
        if (rows>=1)
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
    @ApiOperation(value = "货物, 查询",tags = "goods")
    public Object select(String name){
        ResultBean bean=null;
        try{
            List<Goods> list=mapper.selectall(name);
            bean=new ResultBean();
            bean.setObject(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            bean=new ResultBean(ResultBean.ResultType.FAIL);
            bean.setMessage("查询货物异常，请联系管理员");
        }

        return bean;


    }
}
