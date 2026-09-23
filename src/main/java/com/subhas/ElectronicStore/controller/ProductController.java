package com.subhas.ElectronicStore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhas.ElectronicStore.dto.ProductDto;
import com.subhas.ElectronicStore.payload.ApiResponseMessage;
import com.subhas.ElectronicStore.payload.PageableResponse;
import com.subhas.ElectronicStore.service.ProductService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController 
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController (ProductService productService){
        this.productService = productService;
    }

    @PostMapping 
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.create(productDto), HttpStatus.CREATED);
    }

    @PutMapping("update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.update(productDto, productId), HttpStatus.OK);
    }

    @GetMapping("/getBy")
    public ResponseEntity<ProductDto> getProduct(@RequestParam String productId) {
        return new ResponseEntity<>(productService.getSingleProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct(
        @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber, 
        @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "productName",required = false) String sortBy, 
        @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir) {
        return new ResponseEntity<>(productService.getAllProducts(pageNumber,pageSize,sortBy,sortDir), HttpStatus.OK);
    }
    
    @GetMapping("/search/{subTitle}")
    public ResponseEntity<PageableResponse<ProductDto>> getProductByTitle(
        @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber, 
        @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "productName",required = false) String sortBy, 
        @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir,
        @PathVariable String subTitle
    ) {
        return new ResponseEntity<>(productService.searchProducts(pageNumber, pageSize, sortDir, sortBy, subTitle), HttpStatus.OK);
    }

    @GetMapping("/search/isLive")
    public ResponseEntity<PageableResponse<ProductDto>> IsProductLive(
        @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber, 
        @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "productName",required = false) String sortBy, 
        @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
    ) {
        return new ResponseEntity<>(productService.showLiveProducts(pageNumber, pageSize, sortDir, sortBy), HttpStatus.OK);
    }

    @GetMapping("/search/inStock")
    public ResponseEntity<PageableResponse<ProductDto>> IsProductInStock(
        @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber, 
        @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "productName",required = false) String sortBy, 
        @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
    ) {
        return new ResponseEntity<>(productService.showStockedProducts(pageNumber, pageSize, sortDir, sortBy), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponseMessage> delete(@PathVariable String productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(ApiResponseMessage.builder()
        .message("product deleted succesfully")
        .status(HttpStatus.OK).success(true)
        .build(), HttpStatus.OK); 
    }
    
    
    
}
