package com.rtmap.airport.group.service.impl;

import com.rtmap.airport.group.entity.Category;
import com.rtmap.airport.group.mapper.CategoryMapper;
import com.rtmap.airport.group.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商贸餐饮二级分类表 服务实现类
 * </p>
 *
 * @author shan
 * @since 2022-03-08
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
