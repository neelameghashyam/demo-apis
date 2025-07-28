package com.brodygaudel.demo.util;

import com.brodygaudel.demo.dto.*;
import com.brodygaudel.demo.entity.*;
import java.util.List;

public interface Mappers {
    ProductDTO fromProduct(Product product);
    List<ProductDTO> fromListOfProducts(List<Product> products);
    Product fromProductDTO(ProductDTO productDTO);
    Category fromCategoryDTO(CategoryDTO categoryDTO);
    CategoryDTO fromCategory(Category category);
    List<CategoryDTO> fromListOfCategories(List<Category> categories);
    Customer fromCustomerDTO(CustomerDTO customerDTO);
    CustomerDTO fromCustomer(Customer customer);
    List<CustomerDTO> fromListOfCustomers(List<Customer> customers);
    Website fromWebsiteDTO(WebsiteDTO websiteDTO);
    WebsiteDTO fromWebsite(Website website);
    List<WebsiteDTO> fromListOfWebsites(List<Website> websites);
    User fromUserDTO(UserDTO userDTO);
    UserDTO fromUser(User user);
    List<UserDTO> fromListOfUsers(List<User> users);
    Invoice fromInvoiceDTO(InvoiceDTO invoiceDTO);
    InvoiceDTO fromInvoice(Invoice invoice);
    List<InvoiceDTO> fromListOfInvoices(List<Invoice> invoices);
    Payment fromPaymentDTO(PaymentDTO paymentDTO);
    PaymentDTO fromPayment(Payment payment);
    List<PaymentDTO> fromListOfPayments(List<Payment> payments);
    PricePlan fromPricePlanDTO(PricePlanDTO pricePlanDTO);
    PricePlanDTO fromPricePlan(PricePlan pricePlan);
    List<PricePlanDTO> fromListOfPricePlans(List<PricePlan> pricePlans);
}