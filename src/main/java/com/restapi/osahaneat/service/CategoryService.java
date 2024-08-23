package com.restapi.osahaneat.service;

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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceimpl {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("id"));
        Page<Category> listCategory=categoryRepository.findAll(pageRequest);
        List<CategoryDTO> categoryDTOList=new ArrayList<>();
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


        return categoryDTOList;
    }
}
