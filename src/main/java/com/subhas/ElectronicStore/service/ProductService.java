package com.subhas.ElectronicStore.service;

import com.subhas.ElectronicStore.dto.ProductDto;
import com.subhas.ElectronicStore.payload.PageableResponse;

public interface ProductService{
    ProductDto create(ProductDto productDto);
    ProductDto update(ProductDto productDto, String productId);
    ProductDto getSingleProduct(String productId);
    PageableResponse<ProductDto> getAllProducts(int pageNumber, int pageSize, String sortDir, String sortBy);
    void deleteProduct(String productId);
    PageableResponse<ProductDto> searchProducts(int pageNumber, int pageSize, String sortDir, String sortBy, String subTitle);
    PageableResponse<ProductDto> showLiveProducts(int pageNumber, int pageSize, String sortDir, String sortBy);
    PageableResponse<ProductDto> showStockedProducts(int pageNumber, int pageSize, String sortDir, String sortBy);
}
