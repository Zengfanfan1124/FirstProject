package com.zengfanfan.crm.controller;

import com.zengfanfan.crm.bean.Employ;
import com.zengfanfan.crm.bean.ResultBean;
import com.zengfanfan.crm.dao.EmployMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employ")
@Api(value = "员工", tags = "Employ")
public class EmployController {
    @Autowired
    private EmployMapper employMapper;

    @PostMapping("save")
    @ApiOperation(value = "员工, 保存", tags = "Employ")
    public Object save(@RequestBody Employ employ) {
        int rows = 0;
        if (employ.getId() != null) {
            rows = employMapper.updateByPrimaryKeySelective(employ);
        } else {
            rows = employMapper.insertSelective(employ);
        }

        ResultBean bean = null;
        if (rows >= 1) {
            bean = new ResultBean();
            bean.setObject(employ);
            return bean;
        } else {
            bean = new ResultBean(ResultBean.ResultType.FAIL);
            return bean;
        }
    }

    @GetMapping("delete")
    @ApiOperation(value = "员工, 删除", tags = "Employ")
    public Object delete(Integer id) {
        int rows = employMapper.deleteByPrimaryKey(id);
        ResultBean bean = null;

        if (rows >= 1) {
            bean = new ResultBean();
            return bean;
        } else {
            bean = new ResultBean(ResultBean.ResultType.FAIL);
            return bean;
        }
    }

    @GetMapping("select")
    @ApiOperation(value = "员工, 查询", tags = "Employ")
    public Object select(String name) {
        ResultBean bean = null;
        try {
            List<Employ> list = employMapper.selectByName(name);
            bean = new ResultBean();
            bean.setObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            bean = new ResultBean(ResultBean.ResultType.FAIL);
            bean.setMessage("查询员工异常，请联系管理员");
        }

        return bean;
    }
}
