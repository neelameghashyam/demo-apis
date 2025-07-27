package com.brodygaudel.demo.util.implementation;

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
import com.brodygaudel.demo.util.Mappers;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappersImpl implements Mappers {

    @Override
    public ProductDTO fromProduct(@NotNull Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBarcode(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getId()
        );
    }

    @Override
    public List<ProductDTO> fromListOfProducts(@NotNull List<Product> products) {
        return products.stream().map(this::fromProduct).toList();
    }

    @Override
    public Product fromProductDTO(@NotNull ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.id())
                .barcode(productDTO.barcode())
                .name(productDTO.name())
                .description(productDTO.description())
                .price(productDTO.price())
                .quantity(productDTO.quantity())
                .build();
    }

    @Override
    public Category fromCategoryDTO(@NotNull CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.id())
                .name(categoryDTO.name())
                .description(categoryDTO.description())
                .build();
    }

    @Override
    public CategoryDTO fromCategory(@NotNull Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }

    @Override
    public List<CategoryDTO> fromListOfCategories(@NotNull List<Category> categories) {
        return categories.stream().map(this::fromCategory).toList();
    }

    @Override
    public Customer fromCustomerDTO(@NotNull CustomerDTO customerDTO) {
        return Customer.builder()
                .id(customerDTO.id())
                .name(customerDTO.name())
                .email(customerDTO.email())
                .country(customerDTO.country())
                .dateAdded(customerDTO.dateAdded())
                .integrations(customerDTO.integrations())
                .activePlanName(Customer.ActivePlanName.valueOf(customerDTO.activePlanName()))
                .status(Customer.Status.valueOf(customerDTO.status()))
                .createdAt(customerDTO.createdAt())
                .updatedAt(customerDTO.updatedAt())
                .build();
    }

    @Override
    public CustomerDTO fromCustomer(@NotNull Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCountry(),
                customer.getDateAdded(),
                customer.getIntegrations(),
                customer.getActivePlanName().name(),
                customer.getStatus().name(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    @Override
    public List<CustomerDTO> fromListOfCustomers(@NotNull List<Customer> customers) {
        return customers.stream().map(this::fromCustomer).toList();
    }

    @Override
    public Website fromWebsiteDTO(@NotNull WebsiteDTO websiteDTO) {
        return Website.builder()
                .id(websiteDTO.id())
                .protocol(Website.Protocol.valueOf(websiteDTO.protocol()))
                .domain(websiteDTO.domain())
                .companyId(websiteDTO.companyId())
                .businessCategory(websiteDTO.businessCategory())
                .dateAdded(websiteDTO.dateAdded())
                .isActive(websiteDTO.isActive())
                .isVerified(websiteDTO.isVerified())
                .createdAt(websiteDTO.createdAt())
                .updatedAt(websiteDTO.updatedAt())
                .build();
    }

    @Override
    public WebsiteDTO fromWebsite(@NotNull Website website) {
        return new WebsiteDTO(
                website.getId(),
                website.getProtocol().name(),
                website.getDomain(),
                website.getCompanyId(),
                website.getBusinessCategory(),
                website.getDateAdded(),
                website.getIsActive(),
                website.getIsVerified(),
                website.getCreatedAt(),
                website.getUpdatedAt()
        );
    }

    @Override
    public List<WebsiteDTO> fromListOfWebsites(@NotNull List<Website> websites) {
        return websites.stream().map(this::fromWebsite).toList();
    }

    @Override
    public User fromUserDTO(@NotNull UserDTO userDTO) {
        return User.builder()
                .id(userDTO.id())
                .email(userDTO.email())
                .role(User.Role.valueOf(userDTO.role()))
                .passwordHash(userDTO.passwordHash())
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .jobTitle(userDTO.jobTitle())
                .department(userDTO.department())
                .companyId(userDTO.companyId())
                .simultaneousChatLimit(userDTO.simultaneousChatLimit())
                .createdAt(userDTO.createdAt())
                .updatedAt(userDTO.updatedAt())
                .deletedAt(userDTO.deletedAt())
                .build();
    }

    @Override
    public UserDTO fromUser(@NotNull User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getRole().name(),
                user.getPasswordHash(),
                user.getFirstName(),
                user.getLastName(),
                user.getJobTitle(),
                user.getDepartment(),
                user.getCompanyId(),
                user.getSimultaneousChatLimit(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }

    @Override
    public List<UserDTO> fromListOfUsers(@NotNull List<User> users) {
        return users.stream().map(this::fromUser).toList();
    }
}