package com.restapi.osahaneat.service;

import com.restapi.osahaneat.entity.Users;
import com.restapi.osahaneat.responsitory.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRespository userResponsitory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=userResponsitory.findByUsername(username);
        if(users==null){
            throw new UsernameNotFoundException("user kh tồn tại");
        }
        return new User(username,users.getPassword(), new ArrayList<>());
    }
}
