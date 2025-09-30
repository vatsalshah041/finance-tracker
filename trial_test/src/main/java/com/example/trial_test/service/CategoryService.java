package com.example.trial_test.service;


import com.example.trial_test.entity.Categories;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.trial_test.repository.categoryRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
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
    public boolean delCat(String userId,String catId){
        if (!ObjectId.isValid(catId)) return false;
        ObjectId objId = new ObjectId(catId);
        Optional<Categories> catDetail=categoryRepository.findById(objId);
        if(catDetail.isPresent()){
            categoryRepository.deleteById(objId);
            return true;
        }
        else {
            return false;
        }

    }
    public boolean updateCat(Categories updateCat){
        ObjectId objId = updateCat.getId();

        Optional<Categories> catDetail=categoryRepository.findById(objId);
        if(catDetail.isPresent()){
            Categories ogDet=catDetail.get();
            if (updateCat.getTitle() != null) ogDet.setTitle(updateCat.getTitle());
            if (updateCat.getType() != null) ogDet.setType(updateCat.getType());
            categoryRepository.save(ogDet);
            return true;
        }
        else {
            return false;
        }
    }


}
