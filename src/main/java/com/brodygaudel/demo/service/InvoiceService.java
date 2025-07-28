package com.brodygaudel.demo.service;

import com.brodygaudel.demo.dto.InvoiceDTO;
import com.brodygaudel.demo.exception.InvoiceNotFoundException;

import java.util.List;

public interface InvoiceService {
    InvoiceDTO saveInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO) throws InvoiceNotFoundException;
    InvoiceDTO findInvoiceById(Long id) throws InvoiceNotFoundException;
    List<InvoiceDTO> findAllInvoices();
    List<InvoiceDTO> searchInvoices(String keyword, int page, int size);
    void deleteInvoiceById(Long id);
    void deleteAllInvoices();
}