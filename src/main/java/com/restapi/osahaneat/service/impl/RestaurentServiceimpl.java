package com.restapi.osahaneat.service.impl;

import com.restapi.osahaneat.dto.RestaurentDTO;
import com.restapi.osahaneat.entity.Restaurent;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface RestaurentServiceimpl {
    boolean insertRestaurent(MultipartFile file,
                             String title,
                             String subtitle,
                             String description,
                             boolean is_freeship,
                             String address,
                             String open_date);

    List<RestaurentDTO> getHomePageRestaurants();
    RestaurentDTO getRestaurentDetail(int id);
}
