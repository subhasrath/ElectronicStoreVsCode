package com.subhas.ElectronicStore.helper;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;

import com.subhas.ElectronicStore.payload.PageableResponse;


public class Helper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static <U,V> PageableResponse<V> getPageableResponse(Page<U> page, Class<V> type){
        List<U> entity = page.getContent();
        List<V> dtoList = entity.stream().map(object -> modelMapper.map(object, type)).toList();

        PageableResponse<V> response = new PageableResponse<>();
        response.setContent(dtoList);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        return response;
    }
}
