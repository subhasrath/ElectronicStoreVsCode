package com.subhas.ElectronicStore.service.impl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${product.image.path}")
    String imagePath;

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        product.setAddedDate(new Date());
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct, ProductDto.class);

    }
    
    @Override
    public ProductDto update(ProductDto productDto, String productId) {
       Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product Not Found with given Id"));
       product.setProductName(productDto.getProductName());
       product.setDescription(productDto.getDescription());
       product.setPrice(productDto.getPrice());
       product.setDiscountedPrice(productDto.getDiscountedPrice());
       product.setQuantity(productDto.getQuantity());
       product.setAddedDate(productDto.getAddedDate());
       product.setLive(productDto.isLive());
       product.setStock(productDto.isStock());
       product.setProductImage(productDto.getProductImage());
       Product savedProduct = productRepository.save(product);
       return mapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto getSingleProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found with Given Id"));
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? (Sort.by(sortBy).ascending()) : (Sort.by(sortBy).descending());
        PageRequest pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Product> page = productRepository.findAll(pageable);

        // PageableResponse<productDto> pageableResponse = Helper.getPageableResponse(page, productDto.class);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(page, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found with Given Id"));
       String fullPath = imagePath + product.getProductImage();
        try{
            Path path = Paths.get(fullPath);
            Files.delete(path);
        }
        catch(NoSuchFileException ex){
            logger.info("Product image not found");
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        productRepository.delete(product);
    }

    @Override
    public PageableResponse<ProductDto> searchProducts(int pageNumber, int pageSize, String sortDir, String sortBy,
            String subTitle) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? (Sort.by(sortBy).ascending()) : (Sort.by(sortBy).descending());
        PageRequest pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Product> page = productRepository.findByProductNameContaining(subTitle, pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(page, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public PageableResponse<ProductDto> showLiveProducts(int pageNumber, int pageSize, String sortDir, String sortBy) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? (Sort.by(sortBy).ascending()) : (Sort.by(sortBy).descending());
        PageRequest pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Product> page = productRepository.findByLive(true, pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(page, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public PageableResponse<ProductDto> showStockedProducts(int pageNumber, int pageSize, String sortDir,
            String sortBy) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).ascending()) : (Sort.by(sortBy).descending());
        PageRequest pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Product> page = productRepository.findByStock(true, pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(page, ProductDto.class);
        return pageableResponse;
    }
    

}
