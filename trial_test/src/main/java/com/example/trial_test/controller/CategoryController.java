//package com.example.trial_test.controller;
//
//
//import com.example.trial_test.entity.Categories;
//import com.example.trial_test.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/categories")
//public class CategoryController {
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @GetMapping
//    public ResponseEntity<List<Categories>> getCategories(){
//        List<Categories> cats=categoryService.getCat();
//        return new ResponseEntity<>(cats, HttpStatus.OK);
//
//    }
//
//    @PostMapping
//    public ResponseEntity<?> addCategories(@RequestBody Categories newCat){
//            boolean a=categoryService.saveCat(newCat);
//            return new ResponseEntity<>(true,HttpStatus.OK);
//    }
//
//    @DeleteMapping("id/{catId}")
//    public ResponseEntity<?> delCategories(@PathVariable int catId){
//        boolean b=categoryService.delCat(catId);
//        return b
//                ? new ResponseEntity<>(true, HttpStatus.OK)
//                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
//    }
//    @PutMapping("id/{catId}")
//    public ResponseEntity<Categories> editCategories(@RequestBody Categories updateCat, @PathVariable int catId){
//        boolean c=categoryService.updateCat(updateCat,catId);
//        return c
//                ? new ResponseEntity<>(updateCat, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//}
