package com.rocklct.demo.controller;

import com.rocklct.demo.service.CustomerService;
import com.rocklct.framework.annotation.Action;
import com.rocklct.framework.annotation.Controller;
import com.rocklct.framework.annotation.Inject;
import com.rocklct.framework.bean.Param;
import com.rocklct.framework.bean.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Rocklct on 2017/9/20.
 */

@Controller
public class HelloWorldController {

    private static Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);


    @Inject
    private CustomerService customerService;

    @Action("get:/helloworld")
    public View hello(Param param){
        LOGGER.error("execute hello method");
        return new View("helloworld.jsp").addModel("name","Rock")
                .addModel("content",customerService.getCustomerInfo());
    }
}
