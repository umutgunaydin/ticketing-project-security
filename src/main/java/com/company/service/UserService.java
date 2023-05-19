package com.company.service;


import com.company.dto.UserDTO;

import java.util.List;

public interface UserService  {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO user);
    //    void deleteByUserName(String username);
    UserDTO update(UserDTO user);
    void delete(String username);
    List<UserDTO> listAllByRole(String role);

}