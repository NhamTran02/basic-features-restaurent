package com.restapi.osahaneat.service.impl;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

public interface MenuServiceimpl {
    boolean createMenu(MultipartFile file, String title, String timeShip, boolean isFreeship, double price, String cate_id);
}
