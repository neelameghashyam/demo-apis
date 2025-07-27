package com.brodygaudel.demo.util;

import com.brodygaudel.demo.dto.CategoryDTO;
import com.brodygaudel.demo.dto.CustomerDTO;
import com.brodygaudel.demo.dto.ProductDTO;
import com.brodygaudel.demo.dto.UserDTO;
import com.brodygaudel.demo.dto.WebsiteDTO;
import com.brodygaudel.demo.entity.Category;
import com.brodygaudel.demo.entity.Customer;
import com.brodygaudel.demo.entity.Product;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.Website;

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
}