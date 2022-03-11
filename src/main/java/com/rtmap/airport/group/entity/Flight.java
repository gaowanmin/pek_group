package com.rtmap.airport.group.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author shan
 * @since 2022-02-28
 */
@Getter
@Setter
@TableName("flight")
@ApiModel(value = "Flight对象", description = "")
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("本站机场 ")
    @TableField("source_id")
    private String sourceId;

    @ApiModelProperty("航班唯一标识 ")
    @TableField("gufi")
    private String gufi;

    @ApiModelProperty("【源系统】航班唯一标识")
    @TableField("unit_ufi")
    private String unitUfi;

    @ApiModelProperty("航空公司二字码")
    @TableField("airline_iata")
    private String airlineIata;

    @ApiModelProperty("航班号")
    @TableField("flight_number_iata")
    private String flightNumberIata;

    @ApiModelProperty("航班执行日期")
    @TableField("flight_date")
    private LocalDate flightDate;

    @ApiModelProperty("进出港标识 A/D")
    @TableField("dep_or_arr")
    private String depOrArr;

    @ApiModelProperty("航班任务")
    @TableField("mission_property")
    private String missionProperty;

    @ApiModelProperty("航班属性(D/I/A)")
    @TableField("flight_segment_dori")
    private String flightSegmentDori;

    @ApiModelProperty("共享航班号")
    @TableField("code_share_flight_list")
    private String codeShareFlightList;

    @ApiModelProperty("机号")
    @TableField("reg_number")
    private String regNumber;

    @ApiModelProperty("机型")
    @TableField("aircraft_type")
    private String aircraftType;

    @ApiModelProperty("机场三字码")
    @TableField("dep_ap")
    private String depAp;

    @ApiModelProperty("出发站计划时间")
    @TableField("schedule_time_take_off")
    private LocalDateTime scheduleTimeTakeOff;

    @ApiModelProperty("出发站预计时间")
    @TableField("estimate_take_off_time")
    private LocalDateTime estimateTakeOffTime;

    @ApiModelProperty("出发站实际时间")
    @TableField("atot")
    private LocalDateTime atot;

    @ApiModelProperty("出发站航站楼")
    @TableField("dep_terminal")
    private String depTerminal;

    @ApiModelProperty("到达站机场三字码")
    @TableField("arr_ap")
    private String arrAp;

    @ApiModelProperty("到达站计划到达时间")
    @TableField("schedule_time_landing")
    private LocalDateTime scheduleTimeLanding;

    @ApiModelProperty("到达站预计到达时间")
    @TableField("eldt")
    private LocalDateTime eldt;

    @ApiModelProperty("到达站实际到达时间")
    @TableField("aldt")
    private LocalDateTime aldt;

    @ApiModelProperty("到达站航站楼")
    @TableField("arr_terminal")
    private String arrTerminal;

    @ApiModelProperty("到达站机位 ")
    @TableField("arr_stand")
    private String arrStand;

    @ApiModelProperty("航班状态")
    @TableField("cah_flight_status")
    private String cahFlightStatus;

    @ApiModelProperty("异常状态原因 ")
    @TableField("cnl_reason_chn")
    private String cnlReasonChn;

    @ApiModelProperty("航线")
    @TableField("mission_flyinfo")
    private String missionFlyinfo;

    @ApiModelProperty("值机柜台")
    @TableField("check_in_counter")
    private String checkInCounter;

    @ApiModelProperty("登机口")
    @TableField("dep_gate")
    private String depGate;

    @ApiModelProperty("行李提取口")
    @TableField("expected_arrival_luggage_turntable")
    private String expectedArrivalLuggageTurntable;

    @ApiModelProperty("开始登机时间")
    @TableField("asbt")
    private LocalDateTime asbt;

    @ApiModelProperty("结束登机时间")
    @TableField("aebt")
    private LocalDateTime aebt;

    @ApiModelProperty("催促登机时间 ")
    @TableField("final_call_time")
    private LocalDateTime finalCallTime;

    @ApiModelProperty("首件行李上架时间")
    @TableField("baggage_arr_time")
    private LocalDateTime baggageArrTime;

    @ApiModelProperty("末件行李上架时间")
    @TableField("arr_last_luggage_time")
    private LocalDateTime arrLastLuggageTime;

    @ApiModelProperty("前序航班计划标识符")
    @TableField("former_unit_ufi")
    private String formerUnitUfi;

    @ApiModelProperty("后序航班计划标识符")
    @TableField("next_unit_ufi")
    private String nextUnitUfi;

    @ApiModelProperty("进港上一站实际起飞时间")
    @TableField("pre_atot")
    private LocalDateTime preAtot;

    @ApiModelProperty("出港下一站实际落地时间")
    @TableField("next_aldt")
    private LocalDateTime nextAldt;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("当前记录最新更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标识（0删除）")
    @TableField("del_status")
    private String delStatus;


}
