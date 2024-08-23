package com.restapi.osahaneat.controller;

import com.restapi.osahaneat.payload.ResponseData;
import com.restapi.osahaneat.service.FileService;
import com.restapi.osahaneat.service.MenuService;
import com.restapi.osahaneat.service.impl.FileServiceimpl;
import com.restapi.osahaneat.service.impl.MenuServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuServiceimpl menuServiceimpl;

    @Autowired
    FileServiceimpl fileServiceimpl;

    @PostMapping()
    public ResponseEntity<?> createMenu(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam String timeShip,
            @RequestParam boolean isFreeship,
            @RequestParam double price,
            @RequestParam String cate_id){
        ResponseData responseData = new ResponseData();
        boolean isSuccess=menuServiceimpl.createMenu(file, title, timeShip, isFreeship, price, cate_id);
        responseData.setData(isSuccess);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/file/{filename:.*}")
    public ResponseEntity<?> getFileRestaurent(@PathVariable String filename){
        Resource resource =fileServiceimpl.loadFile(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+resource.getFilename()+"\"").body(resource);
    }

}
