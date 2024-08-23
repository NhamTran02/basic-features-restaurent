package com.restapi.osahaneat.service;

import com.restapi.osahaneat.dto.UserDTO;
import com.restapi.osahaneat.entity.Roles;
import com.restapi.osahaneat.entity.Users;
import com.restapi.osahaneat.payload.request.SignUpRequest;
import com.restapi.osahaneat.responsitory.UserRespository;
import com.restapi.osahaneat.service.impl.LoginServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LoginService implements LoginServiceimpl {
    @Autowired
    UserRespository userResponsitory;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUser(){
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

    @Override
    public boolean checkLogin(String userName, String password) {
        Users users =userResponsitory.findByUsername(userName);

        return passwordEncoder.matches(password,users.getPassword());
    }

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {

        Roles roles=new Roles();
        roles.setId(signUpRequest.getRoleId());

        Users user = new Users();
        user.setFullname(signUpRequest.getFullname());
        user.setUsername(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setRole(roles);

        try {
            userResponsitory.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
