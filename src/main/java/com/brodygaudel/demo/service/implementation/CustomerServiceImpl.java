package com.brodygaudel.demo.service.implementation;

import com.brodygaudel.demo.dto.CustomerDTO;
import com.brodygaudel.demo.entity.Customer;
import com.brodygaudel.demo.exception.CustomerNotFoundException;
import com.brodygaudel.demo.repository.CustomerRepository;
import com.brodygaudel.demo.service.CustomerService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Mappers mappers;

    public CustomerServiceImpl(CustomerRepository customerRepository, Mappers mappers) {
        this.customerRepository = customerRepository;
        this.mappers = mappers;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("In saveCustomer()");
        Customer customer = mappers.fromCustomerDTO(customerDTO);
        Customer customerSaved = customerRepository.save(customer);
        log.info("Customer saved with id: {}", customerSaved.getId());
        return mappers.fromCustomer(customerSaved);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException {
        log.info("In updateCustomer()");
        Customer customer = customerRepository.findById(customerDTO.id())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customer.setName(customerDTO.name());
        customer.setEmail(customerDTO.email());
        customer.setCountry(customerDTO.country());
        customer.setDateAdded(customerDTO.dateAdded());
        customer.setIntegrations(customerDTO.integrations());
        customer.setActivePlanName(Customer.ActivePlanName.valueOf(customerDTO.activePlanName()));
        customer.setStatus(Customer.Status.valueOf(customerDTO.status()));
        Customer customerUpdated = customerRepository.save(customer);
        log.info("Customer updated");
        return mappers.fromCustomer(customerUpdated);
    }

    @Override
    public CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException {
        log.info("In findCustomerById()");
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        log.info("Customer found with id: {}", customer.getId());
        return mappers.fromCustomer(customer);
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        log.info("In findAllCustomers()");
        List<Customer> customers = customerRepository.findAll();
        log.info("All customers found");
        return mappers.fromListOfCustomers(customers);
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword, int page, int size) {
        log.info("In searchCustomers()");
        Page<Customer> customerPage = customerRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} customers found", customerPage.getContent().size());
        return mappers.fromListOfCustomers(customerPage.getContent());
    }

    @Override
    public void deleteCustomerById(Long id) {
        log.info("In deleteCustomerById()");
        customerRepository.deleteById(id);
        log.info("Customer deleted");
    }

    @Override
    public void deleteAllCustomers() {
        log.info("In deleteAllCustomers()");
        customerRepository.deleteAll();
        log.info("All customers deleted");
    }
}