package com.subhas.ElectronicStore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subhas.ElectronicStore.dto.CategoryDto;
import com.subhas.ElectronicStore.payload.ApiResponseMessage;
import com.subhas.ElectronicStore.payload.PageableResponse;
import com.subhas.ElectronicStore.service.CategoryService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    // create
    @PostMapping ()
    public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.create(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
    // update
    @PutMapping ("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @RequestParam String categoryId){
        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
    // delete
    @DeleteMapping ("/delete/{categoryId}")
    public ResponseEntity<ApiResponseMessage> deleteCategory(@PathVariable String categoryId){
        categoryService.delete(categoryId);
        return new ResponseEntity<>(ApiResponseMessage.builder()
        .message("Category deleted successfully !!")
        .success(true)
        .status(HttpStatus.OK)
        .build(), HttpStatus.OK);

    }
    // get all
    @GetMapping ("/getAllCategory")
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
        @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber, 
        @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "name",required = false) String sortBy, 
        @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
    ){
        return new ResponseEntity<>(categoryService.getAllCategory(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);

    }
    
    // get single
    @GetMapping ("/getBy/{categoryId}")
    public ResponseEntity<CategoryDto> singleCategory(@RequestParam String categoryId){
        CategoryDto category = categoryService.singlecategory(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
