package com.ftzp;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        Date start = c.getTime();
        Date end = start;
        c.add(Calendar.HOUR,-1);
        end = c.getTime();
        System.out.println(start);
        System.out.println(end);
    }

    public static Date nowTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        return c.getTime();
    }
}
