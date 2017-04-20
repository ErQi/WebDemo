package com.erqi.test;

import com.erqi.domain.Customer;
import com.erqi.service.CustomerService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.21.
 * 备 注: 测试注入是否成功
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test {
    @Resource(name="customerService")
    private CustomerService customerService;

    @org.junit.Test
    public void delete(){
        try {
            customerService.delete(16L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void list(){
        try {
            List<Customer> list = customerService.queryList();
            for (Customer customer : list) {
                System.err.println(customer.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
