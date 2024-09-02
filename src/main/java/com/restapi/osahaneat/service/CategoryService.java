package com.restapi.osahaneat.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.restapi.osahaneat.dto.CategoryDTO;
import com.restapi.osahaneat.dto.MenuDTO;
import com.restapi.osahaneat.entity.Category;
import com.restapi.osahaneat.entity.Food;
import com.restapi.osahaneat.responsitory.CategoryRepository;
import com.restapi.osahaneat.service.impl.CategoryServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceimpl {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RedisTemplate redisTemplate;

    private Gson gson=new Gson();

//    @Cacheable("categoryHome")
    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        String dataRedis= (String) redisTemplate.opsForValue().get("category");
        List<CategoryDTO> categoryDTOList=new ArrayList<>();

        if (dataRedis==null){
            PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("id"));
            Page<Category> listCategory=categoryRepository.findAll(pageRequest);
            for (Category data : listCategory) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(data.getNameCate());

                List<MenuDTO> menuDTOList=new ArrayList<>();
                for(Food dataFood: data.getFoods()){
                    MenuDTO menuDTO=new MenuDTO();
                    menuDTO.setTitle(dataFood.getTitle());
                    menuDTO.setFreeShip(dataFood.isFreeship());
                    menuDTO.setImage(dataFood.getImage());

                    menuDTOList.add(menuDTO);
                }
                categoryDTO.setMenus(menuDTOList);

                categoryDTOList.add(categoryDTO);
            }

            String dataJson=gson.toJson(categoryDTOList);
            redisTemplate.opsForValue().set("category",dataJson);
        }else {
            Type listType=new TypeToken<List<CategoryDTO>>(){}.getType();
            categoryDTOList=gson.fromJson(dataRedis,listType);
        }

        return categoryDTOList;
    }
}
