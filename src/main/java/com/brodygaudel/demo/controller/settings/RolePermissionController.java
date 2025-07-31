package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.RolePermissionDTO;
import com.brodygaudel.demo.exception.settings.RolePermissionNotFoundException;
import com.brodygaudel.demo.service.settings.RolePermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/role-permissions")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public RolePermissionDTO saveRolePermission(@RequestBody RolePermissionDTO rolePermissionDTO) {
        return rolePermissionService.saveRolePermission(rolePermissionDTO);
    }

    @PutMapping("/update")
    public RolePermissionDTO updateRolePermission(@RequestBody RolePermissionDTO rolePermissionDTO) throws RolePermissionNotFoundException {
        return rolePermissionService.updateRolePermission(rolePermissionDTO);
    }

    @GetMapping("/find/{id}")
    public RolePermissionDTO findRolePermissionById(@PathVariable Long id) throws RolePermissionNotFoundException {
        return rolePermissionService.findRolePermissionById(id);
    }

    @GetMapping("/all")
    public List<RolePermissionDTO> findAllRolePermissions() {
        return rolePermissionService.findAllRolePermissions();
    }

    @GetMapping("/search")
    public List<RolePermissionDTO> searchRolePermissions(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return rolePermissionService.searchRolePermissions(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRolePermissionById(@PathVariable Long id) {
        rolePermissionService.deleteRolePermissionById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllRolePermissions() {
        rolePermissionService.deleteAllRolePermissions();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}