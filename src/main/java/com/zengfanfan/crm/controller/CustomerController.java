package com.zengfanfan.crm.controller;

import com.zengfanfan.crm.bean.Customer;
import com.zengfanfan.crm.bean.ResultBean;
import com.zengfanfan.crm.dao.CustomerMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@Api(value = "客户", tags = "Customer")
public class CustomerController {
    @Autowired
    private CustomerMapper mapper;

    @PostMapping("save")
    @ApiOperation(value = "客户, 保存", tags = "Customer")
    public Object save(@RequestBody Customer customer) {
        int rows = 0;
        if (customer.getId() != null) {
            rows = mapper.updateByPrimaryKeySelective(customer);
        } else {
            rows = mapper.insertSelective(customer);
        }

        ResultBean bean = null;
        if (rows >= 1) {
            bean = new ResultBean();
            bean.setObject(customer);
            return bean;
        } else {
            bean = new ResultBean(ResultBean.ResultType.FAIL);
            return bean;
        }
    }

    @GetMapping("delete")
    @ApiOperation(value = "客户, 删除", tags = "Customer")
    public Object delete(Integer id) {
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

    @GetMapping("select")
    @ApiOperation(value = "客户, 查询", tags = "Customer")
    public Object select(String name) {
        ResultBean bean = null;
        try {
            List<Customer> list = mapper.selectall(name);
            bean = new ResultBean();
            bean.setObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            bean = new ResultBean(ResultBean.ResultType.FAIL);
            bean.setMessage("查询客户异常，请联系管理员");
        }

        return bean;
    }
}