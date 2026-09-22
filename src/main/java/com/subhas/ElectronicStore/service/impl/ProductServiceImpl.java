package com.subhas.ElectronicStore.service.impl;

import org.modelmapper.ModelMapper;

import com.subhas.ElectronicStore.dto.ProductDto;
import com.subhas.ElectronicStore.entity.Product;
import com.subhas.ElectronicStore.payload.PageableResponse;
import com.subhas.ElectronicStore.repository.ProductRepository;
import com.subhas.ElectronicStore.service.ProductService;

public class ProductServiceImpl implements ProductService{

    private ModelMapper mapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ModelMapper mapper, ProductRepository productRepository){
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct, ProductDto.class);

    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        Product product = mapper.map(productDto, Product.class);
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setAddedDate(productDto.getAddedDate());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        
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
