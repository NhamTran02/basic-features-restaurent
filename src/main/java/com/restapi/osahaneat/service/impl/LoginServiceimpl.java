package com.restapi.osahaneat.service.impl;

import com.restapi.osahaneat.dto.UserDTO;
import com.restapi.osahaneat.payload.request.SignUpRequest;

import java.util.List;

public interface LoginServiceimpl {
    List<UserDTO> getAllUser();
    boolean checkLogin(String userName, String password);
    boolean addUser(SignUpRequest signUpRequest);
}
