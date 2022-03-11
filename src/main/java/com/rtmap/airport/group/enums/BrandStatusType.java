package com.rtmap.airport.group.enums;


/**
 * @author cgk
 * @since 2022-02-21
 */
public enum BrandStatusType {

    OPEN(0, "开启"),
    CLOSE(1, "关闭");


    private int status;
    private String type;


    BrandStatusType(int status, String type) {
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
