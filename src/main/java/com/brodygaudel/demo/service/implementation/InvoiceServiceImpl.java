package com.brodygaudel.demo.service.implementation;

import com.brodygaudel.demo.dto.InvoiceDTO;
import com.brodygaudel.demo.entity.Invoice;
import com.brodygaudel.demo.exception.InvoiceNotFoundException;
import com.brodygaudel.demo.repository.InvoiceRepository;
import com.brodygaudel.demo.service.InvoiceService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final Mappers mappers;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, Mappers mappers) {
        this.invoiceRepository = invoiceRepository;
        this.mappers = mappers;
    }

    @Override
    public InvoiceDTO saveInvoice(InvoiceDTO invoiceDTO) {
        log.info("In saveInvoice()");
        Invoice invoice = mappers.fromInvoiceDTO(invoiceDTO);
        Invoice invoiceSaved = invoiceRepository.save(invoice);
        log.info("Invoice saved with id: {}", invoiceSaved.getId());
        return mappers.fromInvoice(invoiceSaved);
    }

    @Override
    public InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO) throws InvoiceNotFoundException {
        log.info("In updateInvoice()");
        Invoice invoice = invoiceRepository.findById(invoiceDTO.id())
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
        invoice.setCompanyName(invoiceDTO.companyName());
        invoice.setCustomerEmail(invoiceDTO.customerEmail());
        invoice.setInvoiceNo(invoiceDTO.invoiceNo());
        invoice.setPrice(invoiceDTO.price());
        invoice.setCurrency(invoiceDTO.currency());
        invoice.setStatus(Invoice.Status.valueOf(invoiceDTO.status()));
        invoice.setDeletedAt(invoiceDTO.deletedAt());
        Invoice invoiceUpdated = invoiceRepository.save(invoice);
        log.info("Invoice updated");
        return mappers.fromInvoice(invoiceUpdated);
    }

    @Override
    public InvoiceDTO findInvoiceById(Long id) throws InvoiceNotFoundException {
        log.info("In findInvoiceById()");
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
        log.info("Invoice found with id: {}", invoice.getId());
        return mappers.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceDTO> findAllInvoices() {
        log.info("In findAllInvoices()");
        List<Invoice> invoices = invoiceRepository.findAll();
        log.info("All invoices found");
        return mappers.fromListOfInvoices(invoices);
    }

    @Override
    public List<InvoiceDTO> searchInvoices(String keyword, int page, int size) {
        log.info("In searchInvoices()");
        Page<Invoice> invoicePage = invoiceRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} invoices found", invoicePage.getContent().size());
        return mappers.fromListOfInvoices(invoicePage.getContent());
    }

    @Override
    public void deleteInvoiceById(Long id) {
        log.info("In deleteInvoiceById()");
        invoiceRepository.deleteById(id);
        log.info("Invoice deleted");
    }

    @Override
    public void deleteAllInvoices() {
        log.info("In deleteAllInvoices()");
        invoiceRepository.deleteAll();
        log.info("All invoices deleted");
    }
}