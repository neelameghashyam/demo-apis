package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.RolePermissionDTO;
import com.brodygaudel.demo.exception.settings.RolePermissionNotFoundException;

import java.util.List;

public interface RolePermissionService {
    RolePermissionDTO saveRolePermission(RolePermissionDTO rolePermissionDTO);
    RolePermissionDTO updateRolePermission(RolePermissionDTO rolePermissionDTO) throws RolePermissionNotFoundException;
    RolePermissionDTO findRolePermissionById(Long id) throws RolePermissionNotFoundException;
    List<RolePermissionDTO> findAllRolePermissions();
    List<RolePermissionDTO> searchRolePermissions(String keyword, int page, int size);
    void deleteRolePermissionById(Long id);
    void deleteAllRolePermissions();
}