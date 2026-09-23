package com.subhas.ElectronicStore.service.impl;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.subhas.ElectronicStore.dto.ProductDto;
import com.subhas.ElectronicStore.entity.Product;
import com.subhas.ElectronicStore.exception.ResourceNotFoundException;
import com.subhas.ElectronicStore.helper.Helper;
import com.subhas.ElectronicStore.payload.PageableResponse;
import com.subhas.ElectronicStore.repository.ProductRepository;
import com.subhas.ElectronicStore.service.ProductService;

@Service 
public class ProductServiceImpl implements ProductService{

    private ModelMapper mapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ModelMapper mapper, ProductRepository productRepository){
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        String productId = UUID.randomUUID().toString();
        Product product = mapper.map(productDto, Product.class);
        product.setProductId(productId);
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct, ProductDto.class);

    }
    
    @Override
    public ProductDto update(ProductDto productDto, String productId) {
       Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product Not Found with given Id"));
       product.setProductName(productDto.getProductName());
       product.setDescription(productDto.getDescription());
       product.setPrice(productDto.getPrice());
       product.setQuantity(productDto.getPrice());
       product.setAddedDate(productDto.getAddedDate());
       product.setLive(productDto.isLive());
       product.setStock(productDto.isStock());
       Product savedProduct = productRepository.save(product);
       return mapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto getSingleProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found with Given Id"));
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllProducts(int pageNumber, int pageSize, String sortDir, String sortBy) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).ascending()) : (Sort.by(sortBy).descending());
        PageRequest pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Product> page = productRepository.findAll(pageable);

        // PageableResponse<productDto> pageableResponse = Helper.getPageableResponse(page, productDto.class);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(page, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found with Given Id"));
        productRepository.delete(product);
    }

    @Override
    public PageableResponse<ProductDto> searchProducts(int pageNumber, int pageSize, String sortDir, String sortBy,
            String subTitle) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).ascending()) : (Sort.by(sortBy).descending());
        PageRequest pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Product> page = productRepository.findByProductNameContaining(subTitle, pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(page, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public PageableResponse<ProductDto> showLiveProducts(int pageNumber, int pageSize, String sortDir, String sortBy) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).ascending()) : (Sort.by(sortBy).descending());
        PageRequest pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Product> page = productRepository.findByLive(true, pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(page, ProductDto.class);
        return pageableResponse;
    }
    

}
