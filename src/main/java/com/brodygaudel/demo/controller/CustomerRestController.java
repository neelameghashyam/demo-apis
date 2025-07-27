package com.brodygaudel.demo.controller;

import com.brodygaudel.demo.dto.CustomerDTO;
import com.brodygaudel.demo.exception.CustomerNotFoundException;
import com.brodygaudel.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @PutMapping("/update")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException {
        return customerService.updateCustomer(customerDTO);
    }

    @GetMapping("/get/{id}")
    public CustomerDTO findCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/list")
    public List<CustomerDTO> findAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/search")
    public List<CustomerDTO> searchCustomers(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return customerService.searchCustomers(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllCustomers() {
        customerService.deleteAllCustomers();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}