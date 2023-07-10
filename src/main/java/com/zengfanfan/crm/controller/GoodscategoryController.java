package com.zengfanfan.crm.controller;

import com.zengfanfan.crm.bean.Goodscategory;
import com.zengfanfan.crm.bean.ResultBean;
import com.zengfanfan.crm.dao.GoodscategoryMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goodscategory")
@Api(value = "货物种类",tags = "goodscategory")
public class GoodscategoryController {
    @Autowired
    private GoodscategoryMapper mapper;


    @PostMapping("save")
    @ApiOperation(value = "货物种类, 保存", tags = "goodscategory")
    public Object save(@RequestBody Goodscategory goodscategory) {
        int rows = 0;
        if (goodscategory.getId()!=null) {
            rows =mapper.updateByPrimaryKeySelective(goodscategory);
        } else {
            rows =mapper.insertSelective(goodscategory);
        }

        ResultBean bean = null;
        if (rows >= 1) {
            bean = new ResultBean();
            bean.setObject(goodscategory);
            return bean;
        } else {
            bean = new ResultBean(ResultBean.ResultType.FAIL);

            return bean;
        }

    }

    @GetMapping("delete")
    @ApiOperation(value = "货物种类, 删除", tags = "category")
    public Object save(Integer id) {
        int rows = mapper.deleteByPrimaryKey(id);
        ResultBean bean = null;
        if (rows >= 1) {
            bean = new ResultBean();
            return bean;
        } else {
            bean = new ResultBean(ResultBean.ResultType.FAIL);

            return bean;
        }

    }

    @GetMapping ("select")
    @ApiOperation(value = "货物种类, 查询", tags = "category")
    public Object select(String name) {
        ResultBean bean = null;
        try {
            List<Goodscategory> list = mapper.selectall(name);
            bean = new ResultBean();
            bean.setObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            bean = new ResultBean(ResultBean.ResultType.FAIL);
            bean.setMessage("查询货物种类异常，请联系管理员");
        }

        return bean;

    }
}