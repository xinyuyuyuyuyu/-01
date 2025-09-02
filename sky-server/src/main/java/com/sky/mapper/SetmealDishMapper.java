package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SetmealDishMapper {
    /**
     * 根据套餐id查询套餐菜品关系
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
}
