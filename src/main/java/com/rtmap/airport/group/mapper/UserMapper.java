package com.rtmap.airport.group.mapper;import com.baomidou.mybatisplus.core.mapper.BaseMapper;import com.baomidou.mybatisplus.core.metadata.IPage;import com.baomidou.mybatisplus.extension.plugins.pagination.Page;import com.rtmap.airport.group.entity.User;import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Select;/** * @Auther: shan * @Date:2022/2/16 * @Description: */@Mapperpublic interface UserMapper extends BaseMapper<User> {    @Select("SELECT * FROM user WHERE sex = #{sex}")    IPage<User> selectPageBySex(Page<?> page, String sex);}