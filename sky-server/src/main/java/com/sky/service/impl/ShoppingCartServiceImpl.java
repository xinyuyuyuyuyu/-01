package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;



    public void add(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        Long userid = BaseContext.getCurrentId();
        shoppingCart.setUserId(userid);
        List<ShoppingCart> list = shoppingCartMapper.add(shoppingCart);

        if(list != null && list.size() >0){
            ShoppingCart shoppingCart1 =list.get(0);
            shoppingCart1.setNumber(shoppingCart1.getNumber()+1);
            shoppingCartMapper.updateNumber(shoppingCart1);
        }else{
            Long dishId = shoppingCart.getDishId();
            if(dishId != null){
                Dish dish = dishMapper.getById(dishId);
                shoppingCart.setName(dish.getName());

                log.info("dish:{}",dish);
                log.info("shoppingcart:{}",shoppingCart);
                shoppingCart.setAmount(dish.getPrice());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setNumber(1);
                shoppingCart.setCreateTime(LocalDateTime.now());
                shoppingCartMapper.insert(shoppingCart);
            }else{
                Long setmealId = shoppingCart.getSetmealId();
                Setmeal setmeal = setmealMapper.getById(setmealId);
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setAmount(setmeal.getPrice());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setNumber(1);
                shoppingCart.setCreateTime(LocalDateTime.now());
                shoppingCartMapper.insert(shoppingCart);
            }

        }



    }

    public List<ShoppingCart> showShoppingCart(){
        Long userid = BaseContext.getCurrentId();
        List<ShoppingCart> list = shoppingCartMapper.showShoppingCart(userid);
        return list;
    }
}
