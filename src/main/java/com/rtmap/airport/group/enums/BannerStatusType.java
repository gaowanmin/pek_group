package com.rtmap.airport.group.enums;

public enum BannerStatusType {

    UNPUBLISH(0, "锁住"),
    PUBLISH(1, "正常");


    private int status;
    private String type;


    BannerStatusType(int status, String type) {
        this.status = status;
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
