package com.restapi.osahaneat.service;

import com.restapi.osahaneat.dto.UserDTO;
import com.restapi.osahaneat.entity.Users;
import com.restapi.osahaneat.responsitory.UserRespository;
import com.restapi.osahaneat.service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceimpl {

    @Autowired
    UserRespository userResponsitory;

    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> listUser =userResponsitory.findAll();
        List<UserDTO> listUserDTO =new ArrayList<>();
        for (Users user : listUser) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setFullname(user.getFullname());
            userDTO.setCreateDate(user.getCreateDate());
            listUserDTO.add(userDTO);
        }
        return listUserDTO;
    }
}
