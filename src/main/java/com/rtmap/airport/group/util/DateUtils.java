package com.rtmap.airport.group.util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DateUtils {

    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    //获取这一时刻当天最小与最大时间
    public  static Map getStartAndEndTime(){
        long current=System.currentTimeMillis();    //当前时间毫秒数
        long zeroT=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();  //今天零点零分零秒的毫秒数
        String zero = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(zeroT);
        long endT=zeroT+24*60*60*1000-1;  //今天23点59分59秒的毫秒数
        String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endT);
        HashMap<String,Object> timemap=new HashMap<String,Object>(2);
        timemap.put("zero",zero);
        timemap.put("end",end);
        return timemap;
    }


    // 获得某天最小时间 2017-10-15 00:00:00
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String formatDateYMDHMS(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String formatDateYMDHM(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return format.format(date);
    }

    public static String formatLocalDate(LocalDate date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(df);
    }

    public static String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(df);
    }

    public static int amOrPm() {
        GregorianCalendar ca = new GregorianCalendar();
        //结果为“0”是上午 结果为“1”是下午
        return ca.get(GregorianCalendar.AM_PM);
    }

    public static void main(String args) {

    }

    //在线时间戳
    public static String getTimeStamp() {
        return String.valueOf(new Date().getTime());
    }
}