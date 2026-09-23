package com.subhas.ElectronicStore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhas.ElectronicStore.dto.ProductDto;
import com.subhas.ElectronicStore.payload.PageableResponse;
import com.subhas.ElectronicStore.service.ProductService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@RequestParam String productId) {
        return new ResponseEntity<>(productService.getSingleProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String sortBy, @RequestParam String sortDir) {
        return new ResponseEntity<>(productService.getAllProducts(pageNumber,pageSize,sortBy,sortDir), HttpStatus.OK);
    }
    
    @GetMapping("path")
    public String getProductByTitle(@RequestParam String param) {
        return new String();
    }

    @GetMapping("path")
    public String IsProductLive(@RequestParam String param) {
        return new String();
    }
    
    
    
}
