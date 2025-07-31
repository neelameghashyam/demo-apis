package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.IpAddressDTO;
import com.brodygaudel.demo.exception.settings.IpAddressNotFoundException;
import com.brodygaudel.demo.service.settings.IpAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/ip-addresses")
public class IpAddressController {

    private final IpAddressService ipAddressService;

    public IpAddressController(IpAddressService ipAddressService) {
        this.ipAddressService = ipAddressService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public IpAddressDTO saveIpAddress(@RequestBody IpAddressDTO ipAddressDTO) {
        return ipAddressService.saveIpAddress(ipAddressDTO);
    }

    @PutMapping("/update")
    public IpAddressDTO updateIpAddress(@RequestBody IpAddressDTO ipAddressDTO) throws IpAddressNotFoundException {
        return ipAddressService.updateIpAddress(ipAddressDTO);
    }

    @GetMapping("/find/{id}")
    public IpAddressDTO findIpAddressById(@PathVariable Long id) throws IpAddressNotFoundException {
        return ipAddressService.findIpAddressById(id);
    }

    @GetMapping("/all")
    public List<IpAddressDTO> findAllIpAddresses() {
        return ipAddressService.findAllIpAddresses();
    }

    @GetMapping("/search")
    public List<IpAddressDTO> searchIpAddresses(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ipAddressService.searchIpAddresses(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIpAddressById(@PathVariable Long id) {
        ipAddressService.deleteIpAddressById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllIpAddresses() {
        ipAddressService.deleteAllIpAddresses();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}