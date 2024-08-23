package com.restapi.osahaneat.controller;

import com.restapi.osahaneat.payload.ResponseData;
import com.restapi.osahaneat.payload.request.SignUpRequest;
import com.restapi.osahaneat.service.impl.LoginServiceimpl;
import com.restapi.osahaneat.utils.JwtUtilsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceimpl loginServiceimpl;

    @Autowired
    JwtUtilsHelper jwtUtilHelper;

    @PostMapping("/signin")
    //? là kiểu dữ liệu động kh xác dịnh , do mình trả về kiểu j
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData=new ResponseData();

//        SecretKey secretKey= Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String encrypted= Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println(encrypted);

        if(loginServiceimpl.checkLogin(username, password)){
            String token=jwtUtilHelper.gernerateToken(username);
            responseData.setData(token);
        }
        else {
            responseData.setData("");
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData=new ResponseData();
        responseData.setData(loginServiceimpl.addUser(signUpRequest));

        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }
}
