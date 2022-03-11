package com.rtmap.airport.group.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: gaowm
 * @date: 2019/4/10
 * @desc:
 */
public enum FltStatusEnum {

    CNCL("取消", "取消", "CANCELLED"),
    TAKEOFF("TAKEOFF", "已起飞", "DEPARTED"),
    GATE_CLOSE("GATE_CLOSE", "结束登机", "GATE CLOSED"),
    LAST_CALL("LAST_CALL", "催促登机", "LAST CALL"),
    BOARDING("BOARDING", "开始登机", "BOARDING"),
//    SCHEDULED("SCHEDULED", "预计", "SCHEDULED"),
    DELAY("延误", "延误", "DELAYED"),
    PLAN("PLAN", "计划", "PLAN"),

    ARRIVE("ARRIVE", "已到达", "LANDED"),
    RETRN("返航", "返航", "RETRUN"),
    DIVAL("备降", "备降", "DIVERTED"),
    EXPECTED("EXPECTED", "前方起飞", "ARRIVING");


    private String code;

    private String desc_cn;

    private String desc_en;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc_cn() {
        return desc_cn;
    }

    public void setDesc_cn(String desc_cn) {
        this.desc_cn = desc_cn;
    }

    public String getDesc_en() {
        return desc_en;
    }

    public void setDesc_en(String desc_en) {
        this.desc_en = desc_en;
    }

    FltStatusEnum(String code, String desc_cn, String desc_en) {
        this.code = code;
        this.desc_cn = desc_cn;
        this.desc_en = desc_en;
    }

    FltStatusEnum() {
    }


    public static FltStatusEnum getDescribe(String code) {

        if (StringUtils.isEmpty(code))
            return null;
        for (FltStatusEnum fltStatusEnum : FltStatusEnum.values()) {
            if (code.equals(fltStatusEnum.getCode())) {
                return fltStatusEnum;
            }
        }
        return null;
    }
}
