package com.sky.mapper;


import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    List<ShoppingCart> add(ShoppingCart shoppingCart);


    @Update("update shopping_cart set number= #{number} where id = #{id}")
    void updateNumber(ShoppingCart shoppingCart);

    void insert(ShoppingCart shoppingCart);


    @Select("select * from shopping_cart where user_id = #{userid}")
    List<ShoppingCart> showShoppingCart(Long userid);

}
