package com.restapi.osahaneat.service;

import com.restapi.osahaneat.dto.CategoryDTO;
import com.restapi.osahaneat.dto.MenuDTO;
import com.restapi.osahaneat.dto.RestaurentDTO;
import com.restapi.osahaneat.entity.*;
import com.restapi.osahaneat.responsitory.RestaurentRepository;
import com.restapi.osahaneat.service.impl.RestaurentServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurentService implements RestaurentServiceimpl {
    @Autowired
    RestaurentRepository restaurentReponsitory;

    @Autowired
    FileService fileService;


    @Override
    public boolean insertRestaurent(MultipartFile file, String title, String subtitle, String description,boolean is_freeship, String address, String open_date) {
        boolean isInsertSeccess=false;
        try {
            boolean isSaveFileSuccess = fileService.saveFile(file);
            if (isSaveFileSuccess) {
                Restaurent restaurent= new Restaurent();
                restaurent.setTitle(title);
                restaurent.setSubtitle(subtitle);
                restaurent.setDesc(description);
                restaurent.setImage(file.getOriginalFilename());
                restaurent.setFressship(is_freeship);
                restaurent.setAddress(address);

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date opendate = simpleDateFormat.parse(open_date);
                restaurent.setOpenDate(opendate);

                restaurentReponsitory.save(restaurent);
                isInsertSeccess=true;
            }
        }catch (Exception e){
            System.out.println("Lỗi insert restaurent"+e.getMessage());
        }
        return isInsertSeccess;
    }

    @Override
    public List<RestaurentDTO> getHomePageRestaurants() {
        List<RestaurentDTO> restaurentDTOList=new ArrayList<>();
        PageRequest pageRequest=PageRequest.of(0, 6);
        Page<Restaurent> listData= restaurentReponsitory.findAll(pageRequest);

        for(Restaurent data:listData){
            RestaurentDTO restaurentDTO=new RestaurentDTO();
            restaurentDTO.setId(data.getId());
            restaurentDTO.setImage(data.getImage());
            restaurentDTO.setTitle(data.getTitle());
            restaurentDTO.setSubtile(data.getSubtitle());
            restaurentDTO.setFreeship(data.isFressship());
            restaurentDTO.setRating(calculatorRating(data.getRatingRestaurents()));

            restaurentDTOList.add(restaurentDTO);
        }

        return restaurentDTOList;
    }

    @Override
    public RestaurentDTO getRestaurentDetail(int id) {
        Optional<Restaurent> restaurent = restaurentReponsitory.findById(id);
        RestaurentDTO restaurentDTO=new RestaurentDTO();
        if(restaurent.isPresent()){
            List<CategoryDTO> categoryDTOList=new ArrayList<>();
            Restaurent data=restaurent.get();

            restaurentDTO.setTitle(data.getTitle());
            restaurentDTO.setImage(data.getImage());
            restaurentDTO.setSubtile(data.getSubtitle());
            restaurentDTO.setFreeship(data.isFressship());
            restaurentDTO.setDescription(data.getDesc());
            restaurentDTO.setRating(calculatorRating(data.getRatingRestaurents()));
            restaurentDTO.setOpenDate(data.getOpenDate());
            System.out.println(restaurent);

            //category
            for(MenuRestaurent menuRestaurent : data.getMenuRestaurents()){
                CategoryDTO categoryDTO=new CategoryDTO();
                List<MenuDTO> menuDTOList=new ArrayList<>();

                categoryDTO.setName(menuRestaurent.getCategory().getNameCate());

                //menu
                for (Food food:menuRestaurent.getCategory().getFoods()){

                    MenuDTO menuDTO=new MenuDTO();
                    menuDTO.setId(food.getId());
                    menuDTO.setImage(food.getImage());
                    menuDTO.setTitle(food.getTitle());
                    menuDTO.setFreeShip(food.isFreeship());
                    menuDTO.setDescription(food.getDescription());
                    menuDTO.setPrice(food.getPrice());

                    menuDTOList.add(menuDTO);
                }
                categoryDTO.setMenus(menuDTOList);
                categoryDTOList.add(categoryDTO);
            }
            restaurentDTO.setCategories(categoryDTOList);
        }
        else {
            System.out.println("kh có restaurent có id: " + id);
        }
        return restaurentDTO;
    }

    private double calculatorRating(Set<RatingRestaurent> listRating){
        double totalPoint=0;
        for(RatingRestaurent data:listRating){
            totalPoint+= data.getRate_poin();
        }

        return totalPoint/listRating.size();
    }

}
