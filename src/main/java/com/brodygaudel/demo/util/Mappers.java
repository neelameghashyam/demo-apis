package com.brodygaudel.demo.util;

import com.brodygaudel.demo.dto.*;
import com.brodygaudel.demo.dto.settings.*;
import com.brodygaudel.demo.entity.*;
import com.brodygaudel.demo.entity.settings.*;

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
    Announcement fromAnnouncementDTO(AnnouncementDTO announcementDTO);
    AnnouncementDTO fromAnnouncement(Announcement announcement);
    List<AnnouncementDTO> fromListOfAnnouncements(List<Announcement> announcements);
    DefaultAvatar fromDefaultAvatarDTO(DefaultAvatarDTO defaultAvatarDTO);
    DefaultAvatarDTO fromDefaultAvatar(DefaultAvatar defaultAvatar);
    List<DefaultAvatarDTO> fromListOfDefaultAvatars(List<DefaultAvatar> defaultAvatars);
    AvatarTemplate fromAvatarTemplateDTO(AvatarTemplateDTO avatarTemplateDTO);
    AvatarTemplateDTO fromAvatarTemplate(AvatarTemplate avatarTemplate);
    List<AvatarTemplateDTO> fromListOfAvatarTemplates(List<AvatarTemplate> avatarTemplates);
    Greeting fromGreetingDTO(GreetingDTO greetingDTO);
    GreetingDTO fromGreeting(Greeting greeting);
    List<GreetingDTO> fromListOfGreetings(List<Greeting> greetings);
    Translation fromTranslationDTO(TranslationDTO translationDTO);
    TranslationDTO fromTranslation(Translation translation);
    List<TranslationDTO> fromListOfTranslations(List<Translation> translations);
    EyeCatcher fromEyeCatcherDTO(EyeCatcherDTO eyeCatcherDTO);
    EyeCatcherDTO fromEyeCatcher(EyeCatcher eyeCatcher);
    List<EyeCatcherDTO> fromListOfEyeCatchers(List<EyeCatcher> eyeCatchers);
    InactivityTimeout fromInactivityTimeoutDTO(InactivityTimeoutDTO inactivityTimeoutDTO);
    InactivityTimeoutDTO fromInactivityTimeout(InactivityTimeout inactivityTimeout);
    List<InactivityTimeoutDTO> fromListOfInactivityTimeouts(List<InactivityTimeout> inactivityTimeouts);
    QueuedMessage fromQueuedMessageDTO(QueuedMessageDTO queuedMessageDTO);
    QueuedMessageDTO fromQueuedMessage(QueuedMessage queuedMessage);
    List<QueuedMessageDTO> fromListOfQueuedMessages(List<QueuedMessage> queuedMessages);
   
    Tag fromTagDTO(TagDTO tagDTO);
    TagDTO fromTag(Tag tag);
    List<TagDTO> fromListOfTags(List<Tag> tags);
    GlobalWebhook fromGlobalWebhookDTO(GlobalWebhookDTO globalWebhookDTO);
    GlobalWebhookDTO fromGlobalWebhook(GlobalWebhook globalWebhook);
    List<GlobalWebhookDTO> fromListOfGlobalWebhooks(List<GlobalWebhook> globalWebhooks);
    Integration fromIntegrationDTO(IntegrationDTO integrationDTO);
    IntegrationDTO fromIntegration(Integration integration);
    List<IntegrationDTO> fromListOfIntegrations(List<Integration> integrations);
   
    Template fromTemplateDTO(TemplateDTO templateDTO);
    TemplateDTO fromTemplate(Template template);
    List<TemplateDTO> fromListOfTemplates(List<Template> templates);
    GlobalNotification fromGlobalNotificationDTO(GlobalNotificationDTO globalNotificationDTO);
    GlobalNotificationDTO fromGlobalNotification(GlobalNotification globalNotification);
    List<GlobalNotificationDTO> fromListOfGlobalNotifications(List<GlobalNotification> globalNotifications);
    MailTemplate fromMailTemplateDTO(MailTemplateDTO mailTemplateDTO);
    MailTemplateDTO fromMailTemplate(MailTemplate mailTemplate);
    List<MailTemplateDTO> fromListOfMailTemplates(List<MailTemplate> mailTemplates);
    IpAddress fromIpAddressDTO(IpAddressDTO ipAddressDTO);
    IpAddressDTO fromIpAddress(IpAddress ipAddress);
    List<IpAddressDTO> fromListOfIpAddresses(List<IpAddress> ipAddresses);
    RolePermission fromRolePermissionDTO(RolePermissionDTO rolePermissionDTO);
    RolePermissionDTO fromRolePermission(RolePermission rolePermission);
    List<RolePermissionDTO> fromListOfRolePermissions(List<RolePermission> rolePermissions);
    
}