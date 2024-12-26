package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAllCustomers();
    List<Customer>getCustomersWithHighMileThanAvg();//1-1
    List<Customer> getCustomersByMileageGrade(String grade);//1-2
}
