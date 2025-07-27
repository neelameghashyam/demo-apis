package com.brodygaudel.demo.service.implementation;

import com.brodygaudel.demo.dto.UserDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.exception.UserNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.service.UserService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mappers mappers;

    public UserServiceImpl(UserRepository userRepository, Mappers mappers) {
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        log.info("In saveUser()");
        User user = mappers.fromUserDTO(userDTO);
        User userSaved = userRepository.save(user);
        log.info("User saved with id: {}", userSaved.getId());
        return mappers.fromUser(userSaved);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException {
        log.info("In updateUser()");
        User user = userRepository.findById(userDTO.id())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setEmail(userDTO.email());
        user.setRole(User.Role.valueOf(userDTO.role()));
        user.setPasswordHash(userDTO.passwordHash());
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setJobTitle(userDTO.jobTitle());
        user.setDepartment(userDTO.department());
        user.setCompanyId(userDTO.companyId());
        user.setSimultaneousChatLimit(userDTO.simultaneousChatLimit());
        user.setDeletedAt(userDTO.deletedAt());
        User userUpdated = userRepository.save(user);
        log.info("User updated");
        return mappers.fromUser(userUpdated);
    }

    @Override
    public UserDTO findUserById(Long id) throws UserNotFoundException {
        log.info("In findUserById()");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        log.info("User found with id: {}", user.getId());
        return mappers.fromUser(user);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        log.info("In findAllUsers()");
        List<User> users = userRepository.findAll();
        log.info("All users found");
        return mappers.fromListOfUsers(users);
    }

    @Override
    public List<UserDTO> searchUsers(String keyword, int page, int size) {
        log.info("In searchUsers()");
        Page<User> userPage = userRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} users found", userPage.getContent().size());
        return mappers.fromListOfUsers(userPage.getContent());
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("In deleteUserById()");
        userRepository.deleteById(id);
        log.info("User deleted");
    }

    @Override
    public void deleteAllUsers() {
        log.info("In deleteAllUsers()");
        userRepository.deleteAll();
        log.info("All users deleted");
    }
}