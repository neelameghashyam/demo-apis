package com.brodygaudel.demo.controller;

import com.brodygaudel.demo.dto.InvoiceDTO;
import com.brodygaudel.demo.exception.InvoiceNotFoundException;
import com.brodygaudel.demo.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceRestController {

    private final InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/save")
    public InvoiceDTO saveInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.saveInvoice(invoiceDTO);
    }

    @PutMapping("/update")
    public InvoiceDTO updateInvoice(@RequestBody InvoiceDTO invoiceDTO) throws InvoiceNotFoundException {
        return invoiceService.updateInvoice(invoiceDTO);
    }

    @GetMapping("/get/{id}")
    public InvoiceDTO findInvoiceById(@PathVariable Long id) throws InvoiceNotFoundException {
        return invoiceService.findInvoiceById(id);
    }

    @GetMapping("/list")
    public List<InvoiceDTO> findAllInvoices() {
        return invoiceService.findAllInvoices();
    }

    @GetMapping("/search")
    public List<InvoiceDTO> searchInvoices(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return invoiceService.searchInvoices(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInvoiceById(@PathVariable Long id) {
        invoiceService.deleteInvoiceById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllInvoices() {
        invoiceService.deleteAllInvoices();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}