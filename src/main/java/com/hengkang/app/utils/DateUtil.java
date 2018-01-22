package com.hengkang.app.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015-03-10.
 */
public class DateUtil {
    public static String currentDateTimeString() {
        Date date = new Date();
        return dateTimeString(date);
    }

    public static String dateTimeString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String currentDateString() {
        Date date = new Date();
        return dateString(date);
    }

    public static String dateString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String currentPayDateTimeString() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    public static boolean dateCheck() throws IOException {
        File file = new File("/opt/conf", "date.txt");
        if (!file.exists()) {
            return true;
        }
        String time = IOUtils.toString(new FileInputStream(file));
        String[] times = time.split(",");
        long startTime = getTime(times[0]).getTimeInMillis();
        long endTime = getTime(times[1]).getTimeInMillis();
        long now = Calendar.getInstance().getTimeInMillis();

        if (endTime < startTime) {
            endTime += 24 * 60 * 60 * 1000L;
        }

        if (now > startTime && now < endTime) {
            return false;
        }
        return true;
    }

    public static String getAge(String birth) {
        String[] birthYear = birth.split("-");
        String[] nowYear=currentDateString().split("-");
        int age=Integer.parseInt(nowYear[0])-Integer.parseInt(birthYear[0]);
        return String.valueOf(age);
    }


    private static Calendar getTime(String time) {
        time = time.replace("\n", "");
        Calendar calendar = Calendar.getInstance();
        String[] array = time.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(array[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(array[1]));
        calendar.set(Calendar.SECOND, Integer.parseInt(array[2]));
        return calendar;
    }

    private static Calendar getTimeHuor(String time) {
        time = time.replace("\n", "");
        Calendar calendar = Calendar.getInstance();
        String[] array = time.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(array[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(array[1]));
        calendar.set(Calendar.SECOND, Integer.parseInt(array[2]));
        return calendar;
    }

    private static Calendar getTimeDay(String time) {
        time = time.replace("\n", "");
        Calendar calendar = Calendar.getInstance();
        String[] array = time.split("-");
        calendar.set(Calendar.YEAR, Integer.parseInt(array[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(array[1]) - 1);
        calendar.set(Calendar.DATE, Integer.parseInt(array[2]));
        return calendar;
    }

    // 活动开始和结束日期
    public static String activityDayCheck() throws IOException {
        File file = new File("/opt/conf", "activity.txt");
        if (!file.exists()) {
            return "no";
        }
        String time = IOUtils.toString(new FileInputStream(file));
        String[] times = time.split(",");
        long startTime = getTimeDay(times[0]).getTimeInMillis();
        long endTime = getTimeDay(times[1]).getTimeInMillis();
        long now = Calendar.getInstance().getTimeInMillis();
        if (now >= startTime && now <= endTime) {
            return "ok";
        } else if (now < startTime) {
            return "begin";
        } else if (now > endTime) {
            return "end";
        }
        return "no";
    }

    //当天活动开始和结束时间
    public static String activityTimeCheck() throws IOException {
        File file = new File("/opt/conf", "time.txt");
        if (!file.exists()) {
            return "no";
        }
        String time = IOUtils.toString(new FileInputStream(file));
        String[] times = time.split(",");
        long startTime = getTimeHuor(times[0]).getTimeInMillis();
        long endTime = getTimeHuor(times[1]).getTimeInMillis();
        long now = Calendar.getInstance().getTimeInMillis();
        if (now > startTime && now < endTime) {
            return "ok";
        } else if (now < startTime) {
            return "begin";
        } else if (now > endTime) {
            return "end";
        }
        return "no";
    }

    public static String gift() throws IOException {
        long min = 0, max = 0, preY = 0, aftY = 0, nowYM = 0, MaxY = 0, MinY = 0;
        File file = new File("/opt/conf", "count.txt");
        if (!file.exists()) {
            return "0,0\n";//没有找到文件
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        if (line != null) {
            String[] arr = line.split(",");
            String b = arr[0].split(" ")[0].toString();
            MinY = getTimeY(b).getTimeInMillis();
        }
        Map<String, Object> maps = new LinkedHashMap<String, Object>();
        while ((line = reader.readLine()) != null) {
            String[] arr = line.split(",");
            maps.put(arr[0] + "," + arr[1], arr[2] + "," + arr[3]);
        }
        if (reader.readLine() != null) {
            MinY = getTimeY(reader.readLine().split(",")[0]).getTimeInMillis();
        }
        for (Map.Entry<String, Object> entry : maps.entrySet()) {
            String b = entry.getKey().split(",")[0];
            String c = entry.getKey().split(",")[1];
            String d = b.split(" ")[0];
            String f = c.split(" ")[0];
            preY = getTimeY(d).getTimeInMillis();
            aftY = getTimeY(f).getTimeInMillis();
            long pre = getTimeH(b.split(" ")[1]).getTimeInMillis();
            long aft = getTimeH(c.split(" ")[1]).getTimeInMillis();

            Calendar calendar = Calendar.getInstance();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowY = formatter.format(calendar.getTime()).split(" ")[0];
            nowYM = getTimeY(nowY).getTimeInMillis();
            String nowH = formatter.format(calendar.getTime()).split(" ")[1].substring(0, 5);
            long nowHM = getTimeH(nowH).getTimeInMillis();

            if (nowYM >= preY && nowYM <= aftY) {
                if (nowHM >= pre && nowHM <= aft) {
                    System.out.println(entry.getValue().toString());
                    return entry.getValue().toString();
                }
            }
            min = Math.min(MinY, preY);
            max = Math.max(MaxY, aftY);
        }
        if (nowYM <= min) {
            return "00,00";
        }
        if (nowYM >= max) {
            return "62,2";
        }
        return "0,0";//没有找到文件
    }

    private static Calendar getTimeH(String time) {
        Calendar calendar = Calendar.getInstance();
        String[] array = time.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(array[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(array[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    private static Calendar getTimeY(String time) {
        Calendar calendar = Calendar.getInstance();
        String[] array = time.split("-");
        calendar.set(Calendar.YEAR, Integer.parseInt(array[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(array[1]) - 1);
        calendar.set(Calendar.DATE, Integer.parseInt(array[2]));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

}
