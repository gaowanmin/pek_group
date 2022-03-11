package com.rtmap.airport.group.mapper;

import com.rtmap.airport.group.entity.SubScribeSimple;
import com.rtmap.airport.group.entity.SubscribeContract;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * 订阅契约表 Mapper 接口
 * </p>
 *
 * @author shan
 * @since 2022-03-03
 */
@Mapper
public interface SubscribeContractMapper extends BaseMapper<SubscribeContract> {

    @Select("select ffid, contract_id as contractId from subscribe_contract where opendid = #{openid} and subscribe_status = 'Y' and invalid_time > now() order by create_time desc")
    @ResultType(SubScribeSimple.class)
    List<SubScribeSimple> selectByOpenid(String openid);

    @Select("select contract_id as contractId from subscribe_contract where opendid = #{openid} and ffid = #{unitUfi} and subscribe_status = 'Y' and invalid_time > now() order by create_time desc")
    @ResultType(String.class)
    List<Integer> selectByParams(@Param("openid") String openid, @Param("unitUfi") String unitUfi);
}
