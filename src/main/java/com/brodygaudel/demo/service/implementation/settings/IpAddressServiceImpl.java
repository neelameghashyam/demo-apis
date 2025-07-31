package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.IpAddressDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.IpAddress;
import com.brodygaudel.demo.exception.settings.IpAddressNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.IpAddressRepository;
import com.brodygaudel.demo.service.settings.IpAddressService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IpAddressServiceImpl implements IpAddressService {

    private final IpAddressRepository ipAddressRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public IpAddressServiceImpl(IpAddressRepository ipAddressRepository, UserRepository userRepository, Mappers mappers) {
        this.ipAddressRepository = ipAddressRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public IpAddressDTO saveIpAddress(IpAddressDTO ipAddressDTO) {
        log.info("In saveIpAddress()");
        IpAddress ipAddress = mappers.fromIpAddressDTO(ipAddressDTO);
        if (ipAddressDTO.userId() != null) {
            User user = userRepository.findById(ipAddressDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            ipAddress.setUser(user);
        }
        IpAddress saved = ipAddressRepository.save(ipAddress);
        log.info("IpAddress saved with id: {}", saved.getId());
        return mappers.fromIpAddress(saved);
    }

    @Override
    public IpAddressDTO updateIpAddress(IpAddressDTO ipAddressDTO) throws IpAddressNotFoundException {
        log.info("In updateIpAddress()");
        IpAddress ipAddress = ipAddressRepository.findById(ipAddressDTO.id())
                .orElseThrow(() -> new IpAddressNotFoundException("IpAddress not found"));
        ipAddress.setIpAddress(ipAddressDTO.ipAddress());
        ipAddress.setCreatedAt(ipAddressDTO.createdAt());
        ipAddress.setUpdatedAt(ipAddressDTO.updatedAt());
        if (ipAddressDTO.userId() != null) {
            User user = userRepository.findById(ipAddressDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            ipAddress.setUser(user);
        } else {
            ipAddress.setUser(null);
        }
        IpAddress updated = ipAddressRepository.save(ipAddress);
        log.info("IpAddress updated");
        return mappers.fromIpAddress(updated);
    }

    @Override
    public IpAddressDTO findIpAddressById(Long id) throws IpAddressNotFoundException {
        log.info("In findIpAddressById()");
        IpAddress ipAddress = ipAddressRepository.findById(id)
                .orElseThrow(() -> new IpAddressNotFoundException("IpAddress not found"));
        log.info("IpAddress found with id: {}", ipAddress.getId());
        return mappers.fromIpAddress(ipAddress);
    }

    @Override
    public List<IpAddressDTO> findAllIpAddresses() {
        log.info("In findAllIpAddresses()");
        List<IpAddress> ipAddresses = ipAddressRepository.findAll();
        log.info("All ip addresses found");
        return mappers.fromListOfIpAddresses(ipAddresses);
    }

    @Override
    public List<IpAddressDTO> searchIpAddresses(String keyword, int page, int size) {
        log.info("In searchIpAddresses()");
        Page<IpAddress> ipAddressPage = ipAddressRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} ip addresses found", ipAddressPage.getContent().size());
        return mappers.fromListOfIpAddresses(ipAddressPage.getContent());
    }

    @Override
    public void deleteIpAddressById(Long id) {
        log.info("In deleteIpAddressById()");
        ipAddressRepository.deleteById(id);
        log.info("IpAddress deleted");
    }

    @Override
    public void deleteAllIpAddresses() {
        log.info("In deleteAllIpAddresses()");
        ipAddressRepository.deleteAll();
        log.info("All ip addresses deleted");
    }
}