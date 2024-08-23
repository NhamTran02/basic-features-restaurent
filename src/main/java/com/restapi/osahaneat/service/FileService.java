package com.restapi.osahaneat.service;

import com.restapi.osahaneat.service.impl.FileServiceimpl;
import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService implements FileServiceimpl {
    @Value("${fileUpload.path}")
    private String fileUploadPath;
    private Path root;

    public void init(){
        root = Paths.get(fileUploadPath);
        if(Files.notExists(root)){
            try {
                Files.createDirectories(root);
            } catch (Exception e) {
                System.out.println("Lỗi tạo thư mục root:  "+e.getMessage());
            }
        }
    }

    @Override
    public boolean saveFile(MultipartFile file) {
        try {
            init();
            Files.copy(file.getInputStream(),root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return true;
        }
        catch (Exception e){
            System.out.println("Lỗi save file:  "+e.getMessage());
            return false;
        }
    }

    @Override
    public Resource loadFile(String filename) {
        try {
            init();
            Path file = root.resolve(filename);
            //Resoucrce giúp ktra file có tồn tại kh, có quyền đọc ghi...
            Resource resource=new UrlResource(file.toUri());
            if(resource.exists()||resource.isReadable()){
                return resource;
            }
        }catch (Exception e){
            System.out.println("Lỗi loadfile:  "+e.getMessage());
        }
        return null;
    }
}
