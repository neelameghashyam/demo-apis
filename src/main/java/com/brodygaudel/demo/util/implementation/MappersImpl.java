package com.brodygaudel.demo.util.implementation;

import com.brodygaudel.demo.dto.*;
import com.brodygaudel.demo.dto.settings.*;
import com.brodygaudel.demo.entity.*;
import com.brodygaudel.demo.entity.settings.*;
import com.brodygaudel.demo.util.Mappers;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mappersimpl implements Mappers {

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

    @Override
    public Invoice fromInvoiceDTO(@NotNull InvoiceDTO invoiceDTO) {
        return Invoice.builder()
                .id(invoiceDTO.id())
                .companyName(invoiceDTO.companyName())
                .customerEmail(invoiceDTO.customerEmail())
                .createdAt(invoiceDTO.createdAt())
                .invoiceNo(invoiceDTO.invoiceNo())
                .price(invoiceDTO.price())
                .currency(invoiceDTO.currency())
                .status(Invoice.Status.valueOf(invoiceDTO.status()))
                .deletedAt(invoiceDTO.deletedAt())
                .build();
    }

    @Override
    public InvoiceDTO fromInvoice(@NotNull Invoice invoice) {
        return new InvoiceDTO(
                invoice.getId(),
                invoice.getCompanyName(),
                invoice.getCustomerEmail(),
                invoice.getCreatedAt(),
                invoice.getInvoiceNo(),
                invoice.getPrice(),
                invoice.getCurrency(),
                invoice.getStatus().name(),
                invoice.getDeletedAt()
        );
    }

    @Override
    public List<InvoiceDTO> fromListOfInvoices(@NotNull List<Invoice> invoices) {
        return invoices.stream().map(this::fromInvoice).toList();
    }

    @Override
    public Payment fromPaymentDTO(@NotNull PaymentDTO paymentDTO) {
        return Payment.builder()
                .id(paymentDTO.id())
                .createdAt(paymentDTO.createdAt())
                .updatedAt(paymentDTO.updatedAt())
                .amount(paymentDTO.amount())
                .currency(paymentDTO.currency())
                .status(Payment.Status.valueOf(paymentDTO.status()))
                .cancellationTime(paymentDTO.cancellationTime())
                .cancellationReason(paymentDTO.cancellationReason())
                .build();
    }

    @Override
    public PaymentDTO fromPayment(@NotNull Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getCreatedAt(),
                payment.getUpdatedAt(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus().name(),
                payment.getCancellationTime(),
                payment.getCancellationReason()
        );
    }

    @Override
    public List<PaymentDTO> fromListOfPayments(@NotNull List<Payment> payments) {
        return payments.stream().map(this::fromPayment).toList();
    }

    @Override
    public PricePlan fromPricePlanDTO(@NotNull PricePlanDTO pricePlanDTO) {
        return PricePlan.builder()
                .id(pricePlanDTO.id())
                .planName(pricePlanDTO.planName())
                .planDescription(pricePlanDTO.planDescription())
                .status(pricePlanDTO.status())
                .defaultPlan(pricePlanDTO.defaultPlan())
                .freePlan(pricePlanDTO.freePlan())
                .type(pricePlanDTO.type())
                .dateAdded(pricePlanDTO.dateAdded())
                .priceMonthly(pricePlanDTO.priceMonthly())
                .priceAnnually(pricePlanDTO.priceAnnually())
                .unlimitedChat(pricePlanDTO.unlimitedChat())
                .numberOfChats(pricePlanDTO.numberOfChats())
                .extraChatAmount(pricePlanDTO.extraChatAmount())
                .unlimitedChatHistoryStorage(pricePlanDTO.unlimitedChatHistoryStorage())
                .chatHistoryDurationDays(pricePlanDTO.chatHistoryDurationDays())
                .costPerExtraDayOfStorage(pricePlanDTO.costPerExtraDayOfStorage())
                .unlimitedUsers(pricePlanDTO.unlimitedUsers())
                .numberOfUsers(pricePlanDTO.numberOfUsers())
                .extraUserCost(pricePlanDTO.extraUserCost())
                .numberOfWebsites(pricePlanDTO.numberOfWebsites())
                .extraWebsiteCost(pricePlanDTO.extraWebsiteCost())
                .chatTakeover(pricePlanDTO.chatTakeover())
                .chatTagging(pricePlanDTO.chatTagging())
                .chatTranscript(pricePlanDTO.chatTranscript())
                .chatbotOpenaiIncluded(pricePlanDTO.chatbotOpenaiIncluded())
                .managedAccount(pricePlanDTO.managedAccount())
                .customPlan(pricePlanDTO.customPlan())
                .createdAt(pricePlanDTO.createdAt())
                .updatedAt(pricePlanDTO.updatedAt())
                .build();
    }

    @Override
    public PricePlanDTO fromPricePlan(@NotNull PricePlan pricePlan) {
        return new PricePlanDTO(
                pricePlan.getId(),
                pricePlan.getPlanName(),
                pricePlan.getPlanDescription(),
                pricePlan.getStatus(),
                pricePlan.getDefaultPlan(),
                pricePlan.getFreePlan(),
                pricePlan.getType(),
                pricePlan.getDateAdded(),
                pricePlan.getPriceMonthly(),
                pricePlan.getPriceAnnually(),
                pricePlan.getUnlimitedChat(),
                pricePlan.getNumberOfChats(),
                pricePlan.getExtraChatAmount(),
                pricePlan.getUnlimitedChatHistoryStorage(),
                pricePlan.getChatHistoryDurationDays(),
                pricePlan.getCostPerExtraDayOfStorage(),
                pricePlan.getUnlimitedUsers(),
                pricePlan.getNumberOfUsers(),
                pricePlan.getExtraUserCost(),
                pricePlan.getNumberOfWebsites(),
                pricePlan.getExtraWebsiteCost(),
                pricePlan.getChatTakeover(),
                pricePlan.getChatTagging(),
                pricePlan.getChatTranscript(),
                pricePlan.getChatbotOpenaiIncluded(),
                pricePlan.getManagedAccount(),
                pricePlan.getCustomPlan(),
                pricePlan.getCreatedAt(),
                pricePlan.getUpdatedAt()
        );
    }

    @Override
    public List<PricePlanDTO> fromListOfPricePlans(@NotNull List<PricePlan> pricePlans) {
        return pricePlans.stream().map(this::fromPricePlan).toList();
    }

    @Override
    public Announcement fromAnnouncementDTO(@NotNull AnnouncementDTO announcementDTO) {
        return Announcement.builder()
                .id(announcementDTO.id())
                .pageType(Announcement.PageType.valueOf(announcementDTO.pageType()))
                .title(announcementDTO.title())
                .text(announcementDTO.text())
                .imageUrl(announcementDTO.imageUrl())
                .createdAt(announcementDTO.createdAt())
                .updatedAt(announcementDTO.updatedAt())
                .build();
    }

    @Override
    public AnnouncementDTO fromAnnouncement(@NotNull Announcement announcement) {
        return new AnnouncementDTO(
                announcement.getId(),
                announcement.getUser() != null ? announcement.getUser().getId() : null,
                announcement.getPageType().name(),
                announcement.getTitle(),
                announcement.getText(),
                announcement.getImageUrl(),
                announcement.getCreatedAt(),
                announcement.getUpdatedAt()
        );
    }

    @Override
    public List<AnnouncementDTO> fromListOfAnnouncements(@NotNull List<Announcement> announcements) {
        return announcements.stream().map(this::fromAnnouncement).toList();
    }

    @Override
    public DefaultAvatar fromDefaultAvatarDTO(@NotNull DefaultAvatarDTO defaultAvatarDTO) {
        return DefaultAvatar.builder()
                .id(defaultAvatarDTO.id())
                .name(defaultAvatarDTO.name())
                .jobTitle(defaultAvatarDTO.jobTitle())
                .avatarImageUrl(defaultAvatarDTO.avatarImageUrl())
                .createdAt(defaultAvatarDTO.createdAt())
                .updatedAt(defaultAvatarDTO.updatedAt())
                .build();
    }

    @Override
    public DefaultAvatarDTO fromDefaultAvatar(@NotNull DefaultAvatar defaultAvatar) {
        return new DefaultAvatarDTO(
                defaultAvatar.getId(),
                defaultAvatar.getUser() != null ? defaultAvatar.getUser().getId() : null,
                defaultAvatar.getName(),
                defaultAvatar.getJobTitle(),
                defaultAvatar.getAvatarImageUrl(),
                defaultAvatar.getCreatedAt(),
                defaultAvatar.getUpdatedAt()
        );
    }

    @Override
    public List<DefaultAvatarDTO> fromListOfDefaultAvatars(@NotNull List<DefaultAvatar> defaultAvatars) {
        return defaultAvatars.stream().map(this::fromDefaultAvatar).toList();
    }

    @Override
    public AvatarTemplate fromAvatarTemplateDTO(@NotNull AvatarTemplateDTO avatarTemplateDTO) {
        return AvatarTemplate.builder()
                .id(avatarTemplateDTO.id())
                .elementName(avatarTemplateDTO.elementName())
                .gender(AvatarTemplate.Gender.valueOf(avatarTemplateDTO.gender()))
                .imageUrl(avatarTemplateDTO.imageUrl())
                .createdAt(avatarTemplateDTO.createdAt())
                .updatedAt(avatarTemplateDTO.updatedAt())
                .build();
    }

    @Override
    public AvatarTemplateDTO fromAvatarTemplate(@NotNull AvatarTemplate avatarTemplate) {
        return new AvatarTemplateDTO(
                avatarTemplate.getId(),
                avatarTemplate.getElementName(),
                avatarTemplate.getGender().name(),
                avatarTemplate.getImageUrl(),
                avatarTemplate.getCreatedAt(),
                avatarTemplate.getUpdatedAt()
        );
    }

    @Override
    public List<AvatarTemplateDTO> fromListOfAvatarTemplates(@NotNull List<AvatarTemplate> avatarTemplates) {
        return avatarTemplates.stream().map(this::fromAvatarTemplate).toList();
    }

    @Override
    public Greeting fromGreetingDTO(@NotNull GreetingDTO greetingDTO) {
        return Greeting.builder()
                .id(greetingDTO.id())
                .title(greetingDTO.title())
                .greeting(greetingDTO.greeting())
                .type(Greeting.Type.valueOf(greetingDTO.type()))
                .visible(greetingDTO.visible())
                .createdAt(greetingDTO.createdAt())
                .updatedAt(greetingDTO.updatedAt())
                .build();
    }

    @Override
    public GreetingDTO fromGreeting(@NotNull Greeting greeting) {
        return new GreetingDTO(
                greeting.getId(),
                greeting.getUser() != null ? greeting.getUser().getId() : null,
                greeting.getTitle(),
                greeting.getGreeting(),
                greeting.getType().name(),
                greeting.getVisible(),
                greeting.getCreatedAt(),
                greeting.getUpdatedAt()
        );
    }

    @Override
    public List<GreetingDTO> fromListOfGreetings(@NotNull List<Greeting> greetings) {
        return greetings.stream().map(this::fromGreeting).toList();
    }

    @Override
    public Translation fromTranslationDTO(@NotNull TranslationDTO translationDTO) {
        return Translation.builder()
                .id(translationDTO.id())
                .language(translationDTO.language())
                .greetingText(translationDTO.greetingText())
                .createdAt(translationDTO.createdAt())
                .updatedAt(translationDTO.updatedAt())
                .build();
    }

    @Override
    public TranslationDTO fromTranslation(@NotNull Translation translation) {
        return new TranslationDTO(
                translation.getId(),
                translation.getGreeting() != null ? translation.getGreeting().getId() : null,
                translation.getLanguage(),
                translation.getGreetingText(),
                translation.getCreatedAt(),
                translation.getUpdatedAt()
        );
    }

    @Override
    public List<TranslationDTO> fromListOfTranslations(@NotNull List<Translation> translations) {
        return translations.stream().map(this::fromTranslation).toList();
    }

    @Override
    public EyeCatcher fromEyeCatcherDTO(@NotNull EyeCatcherDTO eyeCatcherDTO) {
        return EyeCatcher.builder()
                .id(eyeCatcherDTO.id())
                .title(eyeCatcherDTO.title())
                .text(eyeCatcherDTO.text())
                .backgroundColor(eyeCatcherDTO.backgroundColor())
                .textColor(eyeCatcherDTO.textColor())
                .imageUrl(eyeCatcherDTO.imageUrl())
                .createdBy(eyeCatcherDTO.createdBy())
                .company(eyeCatcherDTO.company())
                .createdAt(eyeCatcherDTO.createdAt())
                .updatedAt(eyeCatcherDTO.updatedAt())
                .build();
    }

    @Override
    public EyeCatcherDTO fromEyeCatcher(@NotNull EyeCatcher eyeCatcher) {
        return new EyeCatcherDTO(
                eyeCatcher.getId(),
                eyeCatcher.getUser() != null ? eyeCatcher.getUser().getId() : null,
                eyeCatcher.getTitle(),
                eyeCatcher.getText(),
                eyeCatcher.getBackgroundColor(),
                eyeCatcher.getTextColor(),
                eyeCatcher.getImageUrl(),
                eyeCatcher.getCreatedBy(),
                eyeCatcher.getCompany(),
                eyeCatcher.getCreatedAt(),
                eyeCatcher.getUpdatedAt()
        );
    }

    @Override
    public List<EyeCatcherDTO> fromListOfEyeCatchers(@NotNull List<EyeCatcher> eyeCatchers) {
        return eyeCatchers.stream().map(this::fromEyeCatcher).toList();
    }

    @Override
    public InactivityTimeout fromInactivityTimeoutDTO(@NotNull InactivityTimeoutDTO inactivityTimeoutDTO) {
        return InactivityTimeout.builder()
                .id(inactivityTimeoutDTO.id())
                .agentNotRespondingEnabled(inactivityTimeoutDTO.agentNotRespondingEnabled())
                .agentNotRespondingMinutes(inactivityTimeoutDTO.agentNotRespondingMinutes())
                .agentNotRespondingSeconds(inactivityTimeoutDTO.agentNotRespondingSeconds())
                .archiveChatEnabled(inactivityTimeoutDTO.archiveChatEnabled())
                .archiveChatMinutes(inactivityTimeoutDTO.archiveChatMinutes())
                .archiveChatSeconds(inactivityTimeoutDTO.archiveChatSeconds())
                .visitorInactivityMinutes(inactivityTimeoutDTO.visitorInactivityMinutes())
                .visitorInactivitySeconds(inactivityTimeoutDTO.visitorInactivitySeconds())
                .timeoutMessage(inactivityTimeoutDTO.timeoutMessage())
                .createdAt(inactivityTimeoutDTO.createdAt())
                .updatedAt(inactivityTimeoutDTO.updatedAt())
                .build();
    }

    @Override
    public InactivityTimeoutDTO fromInactivityTimeout(@NotNull InactivityTimeout inactivityTimeout) {
        return new InactivityTimeoutDTO(
                inactivityTimeout.getId(),
                inactivityTimeout.getUser() != null ? inactivityTimeout.getUser().getId() : null,
                inactivityTimeout.getAgentNotRespondingEnabled(),
                inactivityTimeout.getAgentNotRespondingMinutes(),
                inactivityTimeout.getAgentNotRespondingSeconds(),
                inactivityTimeout.getArchiveChatEnabled(),
                inactivityTimeout.getArchiveChatMinutes(),
                inactivityTimeout.getArchiveChatSeconds(),
                inactivityTimeout.getVisitorInactivityMinutes(),
                inactivityTimeout.getVisitorInactivitySeconds(),
                inactivityTimeout.getTimeoutMessage(),
                inactivityTimeout.getCreatedAt(),
                inactivityTimeout.getUpdatedAt()
        );
    }

    @Override
    public List<InactivityTimeoutDTO> fromListOfInactivityTimeouts(@NotNull List<InactivityTimeout> inactivityTimeouts) {
        return inactivityTimeouts.stream().map(this::fromInactivityTimeout).toList();
    }

    @Override
    public QueuedMessage fromQueuedMessageDTO(@NotNull QueuedMessageDTO queuedMessageDTO) {
        return QueuedMessage.builder()
                .id(queuedMessageDTO.id())
                .message(queuedMessageDTO.message())
                .backgroundColor(queuedMessageDTO.backgroundColor())
                .textColor(queuedMessageDTO.textColor())
                .imageUrl(queuedMessageDTO.imageUrl())
                .createdBy(queuedMessageDTO.createdBy())
                .company(queuedMessageDTO.company())
                .createdAt(queuedMessageDTO.createdAt())
                .updatedAt(queuedMessageDTO.updatedAt())
                .build();
    }

    @Override
    public QueuedMessageDTO fromQueuedMessage(@NotNull QueuedMessage queuedMessage) {
        return new QueuedMessageDTO(
                queuedMessage.getId(),
                queuedMessage.getUser() != null ? queuedMessage.getUser().getId() : null,
                queuedMessage.getMessage(),
                queuedMessage.getBackgroundColor(),
                queuedMessage.getTextColor(),
                queuedMessage.getImageUrl(),
                queuedMessage.getCreatedBy(),
                queuedMessage.getCompany(),
                queuedMessage.getCreatedAt(),
                queuedMessage.getUpdatedAt()
        );
    }

    @Override
    public List<QueuedMessageDTO> fromListOfQueuedMessages(@NotNull List<QueuedMessage> queuedMessages) {
        return queuedMessages.stream().map(this::fromQueuedMessage).toList();
    }

    @Override
    public SmartResponse fromSmartResponseDTO(@NotNull SmartResponseDTO smartResponseDTO) {
        return SmartResponse.builder()
                .id(smartResponseDTO.id())
                .response(smartResponseDTO.response())
                .createdBy(smartResponseDTO.createdBy())
                .company(smartResponseDTO.company())
                .createdAt(smartResponseDTO.createdAt())
                .updatedAt(smartResponseDTO.updatedAt())
                .build();
    }

    @Override
    public SmartResponseDTO fromSmartResponse(@NotNull SmartResponse smartResponse) {
        List<String> shortcuts = smartResponse.getShortcuts() != null
                ? smartResponse.getShortcuts().stream().map(SmartResponseShortcut::getShortcut).collect(Collectors.toList())
                : null;
        List<String> websites = smartResponse.getWebsites() != null
                ? smartResponse.getWebsites().stream().map(SmartResponseWebsite::getWebsite).collect(Collectors.toList())
                : null;
        return new SmartResponseDTO(
                smartResponse.getId(),
                smartResponse.getUser() != null ? smartResponse.getUser().getId() : null,
                smartResponse.getResponse(),
                smartResponse.getCreatedBy(),
                smartResponse.getCompany(),
                smartResponse.getCreatedAt(),
                smartResponse.getUpdatedAt(),
                shortcuts,
                websites
        );
    }

    @Override
    public List<SmartResponseDTO> fromListOfSmartResponses(@NotNull List<SmartResponse> smartResponses) {
        return smartResponses.stream().map(this::fromSmartResponse).toList();
    }

    
    @Override
    public Tag fromTagDTO(@NotNull TagDTO tagDTO) {
        return Tag.builder()
                .id(tagDTO.id())
                .tag(tagDTO.tag())
                .isDefault(tagDTO.isDefault())
                .createdBy(tagDTO.createdBy())
                .company(tagDTO.company())
                .createdAt(tagDTO.createdAt())
                .updatedAt(tagDTO.updatedAt())
                .build();
    }

    @Override
    public TagDTO fromTag(@NotNull Tag tag) {
        return new TagDTO(
                tag.getId(),
                tag.getUser() != null ? tag.getUser().getId() : null,
                tag.getTag(),
                tag.getIsDefault(),
                tag.getCreatedBy(),
                tag.getCompany(),
                tag.getCreatedAt(),
                tag.getUpdatedAt()
        );
    }

    @Override
    public List<TagDTO> fromListOfTags(@NotNull List<Tag> tags) {
        return tags.stream().map(this::fromTag).toList();
    }

   

    @Override
    public Webhook fromWebhookDTO(@NotNull WebhookDTO webhookDTO) {
        return Webhook.builder()
                .id(webhookDTO.id())
                .event(Webhook.Event.valueOf(webhookDTO.event()))
                .targetUrl(webhookDTO.targetUrl())
                .createdBy(webhookDTO.createdBy())
                .company(webhookDTO.company())
                .createdAt(webhookDTO.createdAt())
                .updatedAt(webhookDTO.updatedAt())
                .dataTypes(webhookDTO.dataTypes())
                .build();
    }

    @Override
    public WebhookDTO fromWebhook(@NotNull Webhook webhook) {
        return new WebhookDTO(
                webhook.getId(),
                webhook.getUser() != null ? webhook.getUser().getId() : null,
                webhook.getEvent().name(),
                webhook.getTargetUrl(),
                webhook.getCreatedBy(),
                webhook.getCompany(),
                webhook.getCreatedAt(),
                webhook.getUpdatedAt(),
                webhook.getDataTypes()
        );
    }

    @Override
    public List<WebhookDTO> fromListOfWebhooks(@NotNull List<Webhook> webhooks) {
        return webhooks.stream().map(this::fromWebhook).toList();
    }


    @Override
    public GlobalWebhook fromGlobalWebhookDTO(@NotNull GlobalWebhookDTO globalWebhookDTO) {
        return GlobalWebhook.builder()
                .id(globalWebhookDTO.id())
                .event(GlobalWebhook.Event.valueOf(globalWebhookDTO.event()))
                .dataTypeEnabled(globalWebhookDTO.dataTypeEnabled())
                .destination(GlobalWebhook.Destination.valueOf(globalWebhookDTO.destination()))
                .email(globalWebhookDTO.email())
                .targetUrl(globalWebhookDTO.targetUrl())
                .createdBy(globalWebhookDTO.createdBy())
                .company(globalWebhookDTO.company())
                .createdAt(globalWebhookDTO.createdAt())
                .updatedAt(globalWebhookDTO.updatedAt())
                .build();
    }

    @Override
    public GlobalWebhookDTO fromGlobalWebhook(@NotNull GlobalWebhook globalWebhook) {
        return new GlobalWebhookDTO(
                globalWebhook.getId(),
                globalWebhook.getUser() != null ? globalWebhook.getUser().getId() : null,
                globalWebhook.getEvent().name(),
                globalWebhook.getDataTypeEnabled(),
                globalWebhook.getDestination().name(),
                globalWebhook.getEmail(),
                globalWebhook.getTargetUrl(),
                globalWebhook.getCreatedBy(),
                globalWebhook.getCompany(),
                globalWebhook.getCreatedAt(),
                globalWebhook.getUpdatedAt()
        );
    }

    @Override
    public List<GlobalWebhookDTO> fromListOfGlobalWebhooks(@NotNull List<GlobalWebhook> globalWebhooks) {
        return globalWebhooks.stream().map(this::fromGlobalWebhook).toList();
    }

    @Override
    public Integration fromIntegrationDTO(@NotNull IntegrationDTO integrationDTO) {
        return Integration.builder()
                .id(integrationDTO.id())
                .service(Integration.Service.valueOf(integrationDTO.service()))
                .website(integrationDTO.website())
                .apiKey(integrationDTO.apiKey())
                .isConfigured(integrationDTO.isConfigured())
                .createdAt(integrationDTO.createdAt())
                .updatedAt(integrationDTO.updatedAt())
                .build();
    }

    @Override
    public IntegrationDTO fromIntegration(@NotNull Integration integration) {
        return new IntegrationDTO(
                integration.getId(),
                integration.getUser() != null ? integration.getUser().getId() : null,
                integration.getService().name(),
                integration.getWebsite(),
                integration.getApiKey(),
                integration.getIsConfigured(),
                integration.getCreatedAt(),
                integration.getUpdatedAt()
        );
    }

    @Override
    public List<IntegrationDTO> fromListOfIntegrations(@NotNull List<Integration> integrations) {
        return integrations.stream().map(this::fromIntegration).toList();
    }
}