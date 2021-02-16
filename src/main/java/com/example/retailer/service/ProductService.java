package com.example.retailer.service;

import com.example.retailer.dto.CartDetailDto;
import com.example.retailer.dto.ItemDto;

public interface ProductService {

    void additem(ItemDto itemDto, Integer userId);

    CartDetailDto getCartDetails(Integer userId);
}
