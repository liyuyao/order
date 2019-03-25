package com.own.order.infrastructure.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 获取起始日期，等价于去掉 date 的时分秒
     */
    public static Date getStartDate(Date date) {
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    /**
     * 获取终止日期，等价于将时分秒修改为 23:59:59
     */
    public static Date getEndDate(Date date) {
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        return c.getTime();
    }

    /**
     * 增加 days 天
     */
    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, days);

        return c.getTime();
    }

    /**
     * 计算年龄，精确到月和天
     */
    public static String getAge(Date birthdayDate) {
        Calendar birthday = Calendar.getInstance();
        birthday.setTime(birthdayDate);

        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
        int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

        //按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。  
        if (day < 0) {
            month -= 1;
            now.add(Calendar.MONTH, -1);//得到上一个月，用来得到上个月的天数。  
            day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if (month < 0) {
            month = (month + 12) % 12;
            year--;
        }

        if (year > 0) {
            return year + "岁";
        } else if (month > 0) {
            return month + "个月";
        } else if (day > 0) {
            return day + "天";
        } else {
            return "";
        }
    }
}