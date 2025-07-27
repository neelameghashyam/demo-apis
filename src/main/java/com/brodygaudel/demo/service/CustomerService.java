package com.brodygaudel.demo.service;

import com.brodygaudel.demo.dto.CustomerDTO;
import com.brodygaudel.demo.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException;
    CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException;
    List<CustomerDTO> findAllCustomers();
    List<CustomerDTO> searchCustomers(String keyword, int page, int size);
    void deleteCustomerById(Long id);
    void deleteAllCustomers();
}