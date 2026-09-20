package com.subhas.ElectronicStore.service;

import com.subhas.ElectronicStore.dto.CategoryDto;
import com.subhas.ElectronicStore.payload.PageableResponse;

public interface CategoryService {
    // create
    CategoryDto create(CategoryDto categoryDto);
    // update
    CategoryDto update(CategoryDto categoryDto, String categoryId);

    // delete
    void delete(String categoryId);
    // get all
    PageableResponse<CategoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir);

    // get single category
    CategoryDto singlecategory(String categoryId);
}
