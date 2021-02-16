package com.example.retailer.service.impl;

import com.example.retailer.dto.CartDetailDto;
import com.example.retailer.dto.ItemDto;
import com.example.retailer.entity.Item;
import com.example.retailer.entity.Offer;
import com.example.retailer.entity.User;
import com.example.retailer.entity.UserCart;
import com.example.retailer.repository.ItemRepository;
import com.example.retailer.repository.OfferRepository;
import com.example.retailer.repository.UserRepository;
import com.example.retailer.service.ProductService;
import com.example.retailer.util.PromoTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceimpl implements ProductService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OfferRepository offerRepository;


    @Override
    public void additem(ItemDto itemDto, Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Item> item = itemRepository.findById(itemDto.getItemId());
        UserCart userCart = new UserCart();
        userCart.setPurchasedQty(itemDto.getAddedQty());
        userCart.setItem(item.get());
        userCart.setUser(user.get());
    }

    @Override
    public CartDetailDto getCartDetails(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        CartDetailDto cartDetailDto = new CartDetailDto();
        if(user.isPresent()) {
            Set<UserCart> carts = user.get().getCarts();
            List<Offer> offers = (List<Offer>) offerRepository.findAll();
            float totalAmount = 0, discountedAmt = 0;
            List<Offer> appliedOffers = new ArrayList<>();
            for(UserCart cart: carts) {
                totalAmount += cart.getItem().getRate() * cart.getPurchasedQty();
                Optional<Offer> offer = offers.stream().filter(o -> o.getItem().getId().equals(cart.getItem().getId())).findAny();
                if(offer.isPresent()) {
                    Offer applicableOffer = offer.get();
                    // buy 5 mango - get 2 extra at cost of 5 mango
                    if(PromoTypeEnum.FREE_ITEM.equals(applicableOffer.getPromo().getType()) && applicableOffer.getOnQuantity() <= cart.getPurchasedQty()) {
                        cart.setPurchasedQty(cart.getPurchasedQty() + applicableOffer.getDiscount().intValue());
                        appliedOffers.add(applicableOffer);
                    }
                    // buy 2 apple and get 10% discount
                    else if(PromoTypeEnum.PRICE_DISCOUNT.equals(applicableOffer.getPromo().getType()) && applicableOffer.getOnQuantity() <= cart.getPurchasedQty()) {
                        discountedAmt += (((cart.getItem().getRate() * cart.getPurchasedQty()) * applicableOffer.getDiscount())/100);
                        appliedOffers.add(applicableOffer);
                    }
                }
            }
            cartDetailDto.setTotalAmount(totalAmount);
            cartDetailDto.setDiscountedAmount(discountedAmt);
            cartDetailDto.setPayableAmount(totalAmount - discountedAmt);
            cartDetailDto.setUserCartList(carts);
            cartDetailDto.setAppliedOffers(appliedOffers);
        }
        return cartDetailDto;
    }
}
