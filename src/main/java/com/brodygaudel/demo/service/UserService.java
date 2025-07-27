package com.brodygaudel.demo.service;

import com.brodygaudel.demo.dto.UserDTO;
import com.brodygaudel.demo.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException;
    UserDTO findUserById(Long id) throws UserNotFoundException;
    List<UserDTO> findAllUsers();
    List<UserDTO> searchUsers(String keyword, int page, int size);
    void deleteUserById(Long id);
    void deleteAllUsers();
}