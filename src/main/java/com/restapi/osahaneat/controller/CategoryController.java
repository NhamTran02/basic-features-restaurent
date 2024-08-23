package com.restapi.osahaneat.controller;

import com.restapi.osahaneat.payload.ResponseData;
import com.restapi.osahaneat.service.CategoryService;
import com.restapi.osahaneat.service.impl.CategoryServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceimpl categoryServiceimpl;

    @GetMapping()
    public ResponseEntity<?> getHomePageCategory(){
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceimpl.getCategoryHomePage());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
