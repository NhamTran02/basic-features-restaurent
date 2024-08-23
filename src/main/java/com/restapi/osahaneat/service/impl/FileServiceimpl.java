package com.restapi.osahaneat.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceimpl {
    boolean saveFile(MultipartFile file);
    Resource loadFile(String filename);
}
