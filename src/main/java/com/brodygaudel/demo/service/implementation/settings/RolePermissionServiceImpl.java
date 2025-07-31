package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.RolePermissionDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.RolePermission;
import com.brodygaudel.demo.exception.settings.RolePermissionNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.RolePermissionRepository;
import com.brodygaudel.demo.service.settings.RolePermissionService;
import com.brodygaudel.demo.util.Mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository, UserRepository userRepository, Mappers mappers) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public RolePermissionDTO saveRolePermission(RolePermissionDTO rolePermissionDTO) {
        log.info("In saveRolePermission()");
        RolePermission rolePermission = mappers.fromRolePermissionDTO(rolePermissionDTO);
        if (rolePermissionDTO.userId() != null) {
            User user = userRepository.findById(rolePermissionDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            rolePermission.setUser(user);
        }
        RolePermission saved = rolePermissionRepository.save(rolePermission);
        log.info("RolePermission saved with id: {}", saved.getId());
        return mappers.fromRolePermission(saved);
    }

    @Override
    public RolePermissionDTO updateRolePermission(RolePermissionDTO rolePermissionDTO) throws RolePermissionNotFoundException {
        log.info("In updateRolePermission()");
        RolePermission rolePermission = rolePermissionRepository.findById(rolePermissionDTO.id())
                .orElseThrow(() -> new RolePermissionNotFoundException("RolePermission not found"));
        rolePermission.setUserRole(rolePermissionDTO.userRole());
        rolePermission.setCreatedAt(rolePermissionDTO.createdAt());
        rolePermission.setUpdatedAt(rolePermissionDTO.updatedAt());
        if (rolePermissionDTO.userId() != null) {
            User user = userRepository.findById(rolePermissionDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            rolePermission.setUser(user);
        } else {
            rolePermission.setUser(null);
        }
        RolePermission updated = rolePermissionRepository.save(rolePermission);
        log.info("RolePermission updated");
        return mappers.fromRolePermission(updated);
    }

    @Override
    public RolePermissionDTO findRolePermissionById(Long id) throws RolePermissionNotFoundException {
        log.info("In findRolePermissionById()");
        RolePermission rolePermission = rolePermissionRepository.findById(id)
                .orElseThrow(() -> new RolePermissionNotFoundException("RolePermission not found"));
        log.info("RolePermission found with id: {}", rolePermission.getId());
        return mappers.fromRolePermission(rolePermission);
    }

    @Override
    public List<RolePermissionDTO> findAllRolePermissions() {
        log.info("In findAllRolePermissions()");
        List<RolePermission> rolePermissions = rolePermissionRepository.findAll();
        log.info("All role permissions found");
        return mappers.fromListOfRolePermissions(rolePermissions);
    }

    @Override
    public List<RolePermissionDTO> searchRolePermissions(String keyword, int page, int size) {
        log.info("In searchRolePermissions()");
        Page<RolePermission> rolePermissionPage = rolePermissionRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} role permissions found", rolePermissionPage.getContent().size());
        return mappers.fromListOfRolePermissions(rolePermissionPage.getContent());
    }

    @Override
    public void deleteRolePermissionById(Long id) {
        log.info("In deleteRolePermissionById()");
        rolePermissionRepository.deleteById(id);
        log.info("RolePermission deleted");
    }

    @Override
    public void deleteAllRolePermissions() {
        log.info("In deleteAllRolePermissions()");
        rolePermissionRepository.deleteAll();
        log.info("All role permissions deleted");
    }
}