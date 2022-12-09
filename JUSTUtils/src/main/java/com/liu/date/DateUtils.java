package com.liu.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期工具类
 */
public class DateUtils {

    public String getTodayTime(){
        Date date = new Date();
        String format = new SimpleDateFormat("yyyy/MM/dd").format(date);
        return format;
    }
}
