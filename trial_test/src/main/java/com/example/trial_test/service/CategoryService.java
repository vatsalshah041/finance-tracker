package com.example.trial_test.service;


import com.example.trial_test.entity.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.trial_test.repository.categoryRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryService {

    @Autowired
    private categoryRepository categoryRepository;
    public List<Categories> getCat(String userId){
        return categoryRepository.findByUserId(userId);

    }

    public boolean saveCat(Categories newCat){
        try {
            categoryRepository.save(newCat);
            return true;
        } catch (Exception e) {
//            logger.error("Error saving category", e);
            return false;
        }
    }
    public boolean delCat(int catId){
        Optional<Categories> catDetail=categoryRepository.findById(String.valueOf(catId));
        if(catDetail.isPresent()){
            categoryRepository.deleteById(String.valueOf(catId));
            return true;
        }
        else {
            return false;
        }

    }
    public boolean updateCat(Categories updateCat,int catId){
        Optional<Categories> catDetail=categoryRepository.findById(String.valueOf(catId));
        if(catDetail.isPresent()){
            categoryRepository.save(updateCat);
            return true;
        }
        else {
            return false;
        }
    }


}
