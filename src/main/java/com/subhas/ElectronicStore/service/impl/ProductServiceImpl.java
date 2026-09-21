package com.subhas.ElectronicStore.service.impl;

import com.subhas.ElectronicStore.dto.ProductDto;
import com.subhas.ElectronicStore.payload.PageableResponse;
import com.subhas.ElectronicStore.service.ProductService;

public class ProductServiceImpl implements ProductService{

    @Override
    public ProductDto create(ProductDto productDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ProductDto getSingleProduct(String productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSingleProduct'");
    }

    @Override
    public PageableResponse<ProductDto> getAllProducts(int pageNumber, int pageSize, String sortDir, String sortBy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
    }

    @Override
    public void deleteProduct(String productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

    @Override
    public PageableResponse<ProductDto> searchProducts(int pageNumber, int pageSize, String sortDir, String sortBy,
            String subTitle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchProducts'");
    }

    @Override
    public PageableResponse<ProductDto> showLiveProducts(int pageNumber, int pageSize, String sortDir, String sortBy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showLiveProducts'");
    }
    

}
