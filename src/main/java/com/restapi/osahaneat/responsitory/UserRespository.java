package com.restapi.osahaneat.responsitory;

import com.restapi.osahaneat.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRespository extends JpaRepository<Users, Integer> {
    List<Users> findByUsernameAndPassword(String username,String password);
    Users findByUsername(String username);
}
