package com.rtmap.airport.group.result;


import org.springframework.util.StringUtils;

public enum ResponseStatusEnum {

    SUCCESS("200", "success"),
    FAILED("-1", "操作失败"),
    FAIL("500", "系统异常，请稍后再试！"),
    VERIFY_CODE_FAIL("40001", "验证码验证失败"),
    DATABASE_ERROR("40002", "数据库错误"),
    USER_NOT_EXIST("40003", "用户不存在"),
    PASSWORD_ERROR("40004", "用户名或密码错误"),
    MOBILE_ALREADY_REGIST("40005", "手机号已被注册"),
    SMS_CODE_SIGIN_FAIL("40006", "验证码发送签名错误"),
    SMS_CODE_TYPE_FAIL("40007", "验证码发送类型错误"),
    PARAMS_ERROR("40008", "参数错误"),
    USER_LOCK("40009", "用户被锁定，请联系管理员！"),
    WX_PAY_FAIL("40010", "微信支付错误，请稍后重试！"),
    ALIPAY_FAIL("40011", "支付宝支付失败，请联系客服！"),
    PAY_SUCCESS("40012", "付款成功！"),
    NO_PRIVILEGE("50001", "用户未登录"),
    SOURCE_FAIL("50002", "非法授权渠道"),
    USER_NOT_APPLY("50003", "用户状态未审核"),
    WITHDRAW_OVER_TIMES("50004", "今日提现次数已用完"),
    DATABASE_DONT_NO("50005", "数据库尚未配置"),


    //活动相关
    ACTIVITY_TAKE_ALREADY("60001", "已参与过该活动"),
    ACTIVITY_NOT_FOUND("60002", "活动不存在！"),
    ACTIVITY_FINISH("60003", "活动结束"),
    LOTTERY_INVITED_FRIEND_NO_SUFFICIENT_2("60004", "当天需邀请2个好友才可兑换"),
    LOTTERY_INVITED_FRIEND_NO_SUFFICIENT_5("60004", "当天需邀请5个好友才可兑换"),
    LOTTERY_INVITED_FRIEND_NO_SUFFICIENT_8("60004", "当天需邀请7个好友才可兑换"),

    //申请相关
    BAOSONG_FAIL("60005", "存在待提交报送，报送失败！"),
    JOB_NOT_EXIT("60006", "岗位信息不存在！"),
    JOB_NOT_MATCH("60004", "用户类型不符合岗位要求！"),
    JOB_DATE_NOT_MATCH("60003", "服务日期不符合岗位要求！"),
    JOB_APPLY_NOT_EXIT("60002", "报名信息不存在！"),
    JOB_APPLY_EXIT("60001", "您已报该时间段岗位！"),
    CHANGE_SELL_OUT("60006", "岗位已满！"),
    CHANGE_TAKE_TODAY("60007", "今日已领取"),
    REPORT_FAIL("60008", "已经提交过报告！"),
    CHANGE_ORDER_OUT("60009", "库存不足 || 包含下架商品"),
    MONTH_SERVICE_DURATION("60010", "本月服务时长已达到32小时"),

    //订单相关
    APPLY_INFO_ERROR("70001", "岗位报名信息不存在"),
    APPLY_LACK_24_HOUR("70002", "距离上岗不足24小时，请联系客服取消"),
    ORDER_NO_ERROR("70003", "订单号不存在"),
    STORE_ORDER_NO_ERROR("70004", "不是该店铺订单，订单操作失败"),
    USER_ORDER_NO_ERROR("70005", "不是该用户订单，订单操作失败"),
    ORDER_STATUS_ERROR("70006", "订单已变更，请勿重复操作"),
    ErrNoSTORE("70007", "店铺未开业，请先开业"),
    ERROR_GOODS_UP("70008", "订单商品已下架"),
    ERROR_NO_CATEGORYGOODS("70009", "暂无该类型商品的店铺"),
    ERROR_NO_STORE("70010", "暂无店铺营业"),
    ERROR_SCAN_NO_ORDER("70011", "请重新扫码并确认是否为本店铺订单"),


    //志愿者相关
    VOLUNTEER_INFO_EXIST("90001", "志愿者信息已存在"),
    VOLUNTEER_INFO_NOT_EXIST("90002", "志愿者信息不存在"),
    VOLUNTEER_INFO_UPDATE_ERROR("90003", "志愿者信息更新失败"),
    SMS_NOTICE_PARAM_ERROR("90004", "请完善【具体时间】和【具体地点】！"),
    //考勤相关
    CHECKIN_ERROR("10001", "打卡失败"),
    CARD_CHECKIN_ERROR("10002", "考勤更新失败"),


    //好友相关
    NOT_FRIEND("80001", "非好友关系"),

    //积分相关
    SCORE_OF_FRIEND_LEFT_NOT_ENOUGH("90001", "好友积分不足"),
    SCORE_STEAL_SUCCESS("200", "偷取积分成功"),

    //体温相关
    TEMPERATURE_OK("200", "您的体温正常！"),
    TEMPERATURE_EXCEPTION("-1", "您的体温异常！"),
    HEALTH_NOT_EXIST("-1", "未查询到您的测评数据!"),

    //Aibee
    PARAMS_NULL("-1", "条件为空"),
    ErrNoSuccess("0", "成功响应"),
    ErrNoAuthFail("400", "鉴权失败"),
    ErrNoMissingParam("401", "缺少请求参数非法"),
    ErrNoInvalidParam("402", "请求参数非法"),
    ErrNoLowQualityPhoto("403", "图片质量过低"),
    ErrNoImgDecodeFail("404", "图片解析失败"),
    ErrNoUnauthorized("405", "未授权操作"),
    ErrNoSystemErr("500", "系统错误"),
    ErrNoDatabaseErr("501", "数据库错误"),
    ErrNoFaceOpFail("502", "人脸服务操作失败"),
    ErrNoImgSaveFail("503", "图片存储失败"),

    //商家登录相关
    ErrNoPhoneFail("-1", "绑定手机号有误"),
    LOGINFAILED("-2", "验证码有误，登录失败"),

    //上传文件相关
    ErrNoPhoto("40001", "上传图片大小为0！"),
    ErrOutPhoto("40002", "上传图片大小不能超过5M！"),


    //航班相关
    CurrentAirportNotEmpty("1001","本站机场三字码不能为空！"),
    FltDateNotEmpty("1002","航班日期不能为空！"),
    FltNoNotEmpty("1002","航班号不能为空！"),
    AirportCodeNotEmpty("1003","机场三字码不能为空！"),
    AordNotEmpty("1004","进出港标识不能为空！"),
    FfidNotEmpty("1005","进出港标识不能为空！"),
    SubcribeIdNotEmpty("1006","openid不能为空！"),
    RepeatSubcribe("1007","不允许重复订阅！"),
    RepeatUnSubcribe("1008","不允许重复取消订阅！"),
    OpenidNotEmpty("1009","openid不能为空！"),

    //行李相关
    LoginFailed("2001","登录失败！");


    private String msg;
    private String code;

    ResponseStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static ResponseStatusEnum getDescribe(String code) {
        if (StringUtils.isEmpty(code))
            return null;
        for (ResponseStatusEnum responseStatusEnum : ResponseStatusEnum.values()) {
            if (code.equals(responseStatusEnum.getCode())) {
                return responseStatusEnum;
            }
        }
        return null;
    }
}
