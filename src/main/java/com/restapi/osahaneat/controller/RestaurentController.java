package com.restapi.osahaneat.controller;

import com.restapi.osahaneat.payload.ResponseData;
import com.restapi.osahaneat.service.impl.FileServiceimpl;
import com.restapi.osahaneat.service.impl.RestaurentServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/restaurent")
public class RestaurentController {
    @Autowired
    FileServiceimpl fileService;

    @Autowired
    RestaurentServiceimpl restaurentServiceimpl;

    @PostMapping()
    public ResponseEntity<?> createRestaurent(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam String subtitle,
            @RequestParam String description,
            @RequestParam boolean is_freeship,
            @RequestParam String address,
            @RequestParam String open_date){
        ResponseData responseData = new ResponseData();
//        boolean isSuccess = fileService.saveFile(file); đã chuyển sang class RestaurentService
        boolean isSuccess=restaurentServiceimpl.insertRestaurent(file,title,subtitle,description,is_freeship,address,open_date);
        responseData.setData(isSuccess);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getHomePageRestaurent(){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurentServiceimpl.getHomePageRestaurants());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



    @GetMapping("/file/{filename:.*}")
    public ResponseEntity<?> getFileRestaurent(@PathVariable String filename){
        Resource resource =fileService.loadFile(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+resource.getFilename()+"\"").body(resource);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getRestaurentDetail(@PathVariable int id){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurentServiceimpl.getRestaurentDetail(id));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
