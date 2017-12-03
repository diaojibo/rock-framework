package com.rocklct.demo.service;

import com.rocklct.demo.model.Customer;
import com.rocklct.framework.annotation.Service;
import com.rocklct.framework.annotation.Transaction;
import com.rocklct.framework.helper.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Rocklct on 2017/12/3.
 */
@Service
public class CustomerService {

    @Transaction
    public String getCustomerInfo() {
        ArrayList<Customer> clist = new ArrayList<Customer>();
        clist.addAll(DatabaseHelper.queryEntityList(Customer.class, "select * from customer"));
        String result = "";
        for (Customer c : clist) {
            result += c.getContact() + " email: " + c.getEmail() + "\n";
        }
        return result;
    }

}
