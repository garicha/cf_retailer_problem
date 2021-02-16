package com.example.retailer.controller;

import com.example.retailer.dto.CartDetailDto;
import com.example.retailer.dto.ItemDto;
import com.example.retailer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/addProduct")
    public ResponseEntity<?> addItemsToCart(@RequestBody ItemDto itemDto, @RequestParam Integer userId) {
        productService.additem(itemDto, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/getDetails")
    public CartDetailDto getCartDetails(@RequestParam Integer userId) {
        return productService.getCartDetails(userId);
    }

}
