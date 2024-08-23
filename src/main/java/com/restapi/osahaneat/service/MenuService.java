package com.restapi.osahaneat.service;

import com.restapi.osahaneat.entity.Category;
import com.restapi.osahaneat.entity.Food;
import com.restapi.osahaneat.entity.Restaurent;
import com.restapi.osahaneat.responsitory.FoodRepository;
import com.restapi.osahaneat.service.impl.MenuServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MenuService implements MenuServiceimpl {
    @Autowired
    FileService fileService;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public boolean createMenu(MultipartFile file, String title, String timeShip, boolean isFreeship, double price, String cate_id) {
        boolean isInsertSeccess=false;
        try {
            boolean isSaveFileSuccess = fileService.saveFile(file);
            if (isSaveFileSuccess) {
                Food food=new Food();
                food.setTitle(title);
                food.setImage(file.getOriginalFilename());
                food.setTimeShip(timeShip);
                food.setFreeship(isFreeship);
                food.setPrice(price);

                Category category=new Category();
                category.setId(Integer.parseInt(cate_id));

                food.setCategory(category);

                foodRepository.save(food);
                isInsertSeccess=true;
            }
        }catch (Exception e){
            System.out.println("Lỗi insert restaurent"+e.getMessage());
        }
        return isInsertSeccess;
    }
}
