package com.subhas.ElectronicStore.controller;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.subhas.ElectronicStore.dto.CategoryDto;
import com.subhas.ElectronicStore.dto.ProductDto;
import com.subhas.ElectronicStore.payload.ApiResponseMessage;
import com.subhas.ElectronicStore.payload.ImageResponse;
import com.subhas.ElectronicStore.payload.PageableResponse;
import com.subhas.ElectronicStore.service.CategoryService;
import com.subhas.ElectronicStore.service.FileService;
import com.subhas.ElectronicStore.service.ProductService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final FileService fileService;
    private final ProductService productService;

    public CategoryController(CategoryService categoryService, FileService fileService, ProductService productService){
        this.categoryService = categoryService;
        this.fileService = fileService;
        this.productService = productService;
    }

    @Value("${category.image.path}")
    String imageUploadPath;

    Logger logger = LoggerFactory.getLogger(CategoryController.class);

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

     // upload user image
    @PostMapping("/image/{categoryId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("coverImage") MultipartFile image, @PathVariable String categoryId) throws IOException{
        
        String imageName = fileService.uploadImage(image, imageUploadPath);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        CategoryDto category = categoryService.singlecategory(categoryId);
        category.setCoverImage(imageName);
        categoryService.update(category, categoryId);
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
    }
    // serve User Image
    @GetMapping("/image/{categoryId}")
    public void serveUserImage(@PathVariable String categoryId, HttpServletResponse response)throws IOException{
        CategoryDto category = categoryService.singlecategory(categoryId);
        logger.info("cover image name: {}", category.getCoverImage());
        InputStream resource = fileService.getResource(imageUploadPath, category.getCoverImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

    @GetMapping ("/search/{keyword}")
    public ResponseEntity<PageableResponse<CategoryDto>> getCategoryByTitle(
        @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber, 
        @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "title",required = false) String sortBy, 
        @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir,
        @PathVariable String keyword
    ){
        return new ResponseEntity<>(categoryService.searchCategoryByName(keyword,pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);

    }
// create product with category
    @PostMapping("/{categoryId}/product")
    public ResponseEntity<ProductDto> createProductWithCategory(@RequestBody ProductDto productDto, 
        @PathVariable ("categoryId") String categoryId) 
        {
            return new ResponseEntity<>(productService.createWithCategory(productDto, categoryId), HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDto> updateCategoryOfProduct(@PathVariable String categoryId, @PathVariable String productId) {
        return new ResponseEntity<>(productService.updateCategory(productId, categoryId), HttpStatus.CREATED);
    }

    @GetMapping ("/search/{categoryId}")
    public ResponseEntity<PageableResponse<CategoryDto>> getCategoryById(
        @PathVariable String categoryId
    ){
       PageableResponse<ProductDto> response = productService.getAllOfCategory(categoryId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    
}
