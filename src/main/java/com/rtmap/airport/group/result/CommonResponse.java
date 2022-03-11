package com.rtmap.airport.group.result;


import java.util.Date;


public class CommonResponse<T> {

    public CommonResponse() {
        this.setCode(ResponseStatusEnum.SUCCESS.getCode());
        this.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
    }

    public CommonResponse(T data) {
        this(ResponseStatusEnum.SUCCESS, data);
    }

    public CommonResponse(ResponseStatusEnum responseStatusEnum, T data) {
        this.setCode(responseStatusEnum.getCode());
        this.setMsg(responseStatusEnum.getMsg());
        this.setData(data);
    }

    public CommonResponse(String code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

    public CommonResponse(ResponseStatusEnum responseStatusEnum) {
        this.setCode(responseStatusEnum.getCode());
        this.setMsg(responseStatusEnum.getMsg());
    }

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 错误码
     */
    private String code;

    /**
     * 系统当前时间
     */
    private Date sysCurrentTime = new Date();

    /**
     * 成功时存储的数据
     */
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getSysCurrentTime() {
        return sysCurrentTime;
    }

    public void setSysCurrentTime(Date sysCurrentTime) {
        this.sysCurrentTime = sysCurrentTime;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
