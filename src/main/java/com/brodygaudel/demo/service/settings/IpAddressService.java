package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.IpAddressDTO;
import com.brodygaudel.demo.exception.settings.IpAddressNotFoundException;

import java.util.List;

public interface IpAddressService {
    IpAddressDTO saveIpAddress(IpAddressDTO ipAddressDTO);
    IpAddressDTO updateIpAddress(IpAddressDTO ipAddressDTO) throws IpAddressNotFoundException;
    IpAddressDTO findIpAddressById(Long id) throws IpAddressNotFoundException;
    List<IpAddressDTO> findAllIpAddresses();
    List<IpAddressDTO> searchIpAddresses(String keyword, int page, int size);
    void deleteIpAddressById(Long id);
    void deleteAllIpAddresses();
}