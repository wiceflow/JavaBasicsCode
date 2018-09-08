package com.wiceflow.util;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Date 日期组件
 *
 * @author iceflow
 */
public class DateUtil {
    Logger logger = Logger.getLogger(DateUtil.class);

    static DecimalFormat df = new DecimalFormat("##.##");
    public static String PATTERN_yyyy_MM_dd = "yyyy-MM-dd";
    private static String PATTERN_yyyy_MM = "yyyy-MM";
    private static String PATTERN_yy_MM_dd_HHmmss = "yy-MM-dd HH:mm:ss";
    public final static String PATTERN_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    private static String PATIERN_yyyy_MM_dd_HHmm = "yyyy-MM-dd HH:mm";
    private static String PATIERN_HH_MM = "HH:mm";
    private static String PATTERN_yy_MM_dd_HHmmss2 = "yy/MM/dd HH:mm:ss";
    static DateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

    public static List<String> getDates(Integer year, Integer month) {
        List<String> dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        while (cal.get(Calendar.YEAR) == year &&
                cal.get(Calendar.MONTH) < month) {
            dates.add(yyyy_MM_dd.format(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static String getHourAndMin(){
        return parseDateToString(Calendar.getInstance().getTime(),
                PATIERN_HH_MM);
    }

    public static String timeInterval(String start, String end) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yy_MM_dd_HHmmss2);
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        try {
            calendar1.setTime(sdf.parse(start));
            calendar2.setTime(sdf.parse(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long result = calendar2.getTime().getTime() - calendar1.getTime().getTime();
        return df.format(result / 1000 / 60 / 60.0);
    }

    public static List<String> getSerialDays(String start, String end) {
        List<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyy_MM_dd);
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        try {
            calendar1.setTime(sdf.parse(start));
            calendar2.setTime(sdf.parse(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayNum = calendar2.get(Calendar.DAY_OF_YEAR) - calendar1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i <= dayNum; i++) {
            result.add(sdf.format(calendar1.getTime()));
            calendar1.add(Calendar.DATE, 1);
        }
        return result;
    }

    public static String getCurrent_yyyy_MM_dd_HHmmss() {
        return parseDateToString(Calendar.getInstance().getTime(),
                PATTERN_YYYY_MM_DD_HHMMSS);
    }

    public static String getCurrent_yyyy_MM_dd_HHmm() {
        return parseDateToString(Calendar.getInstance().getTime(), PATIERN_yyyy_MM_dd_HHmm);
    }

    public static String getCurrent_yyyy_MM_dd_HHmm(Date date) {
        return parseDateToString(date, PATIERN_yyyy_MM_dd_HHmm);
    }

    public static String getCurrent_yy_MM_dd_HHmmss() {
        return parseDateToString(Calendar.getInstance().getTime(),
                PATTERN_yy_MM_dd_HHmmss);
    }

    public static String getCurrentTimeyyyy_MM_dd() {
        return parseDateToString(Calendar.getInstance().getTime(),
                PATTERN_yyyy_MM_dd);
    }

    public static String parseDateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat _df = new SimpleDateFormat(pattern);
        return _df.format(date);
    }


    public static String getCurrentMonth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyy_MM);
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    public static String getLastMonth(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyy_MM);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(time));
        calendar.add(Calendar.MONTH, -1);
        return sdf.format(calendar.getTime());
    }

    public static String getNextMonth(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyy_MM);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(time));
        calendar.add(Calendar.MONTH, 1);
        return sdf.format(calendar.getTime());
    }

    public static String getLastYear(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyy_MM);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(time));
        calendar.add(Calendar.YEAR, -1);
        return sdf.format(calendar.getTime());
    }


    public static List<String> getLast7weeks(String wtime) {
        List<String> reuslt = new ArrayList<>();
        String[] time = wtime.split("_");
        String lastTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(time[0]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reuslt.add(wtime);
        for (int i = 0; i < 7; i++) {
            c1.add(Calendar.DATE, -1);
            lastTime = sdf.format(c1.getTime());
            c1.add(Calendar.DATE, -6);
            lastTime = sdf.format(c1.getTime()) + "_" + lastTime;
            reuslt.add(lastTime);
        }
        return reuslt;
    }

    /**
     * 获取当周日期 周日到周六
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static String getWeek(String time) throws ParseException {
        SimpleDateFormat YYYY_MM_dd = new SimpleDateFormat(PATTERN_yyyy_MM_dd);
        String Monday;
        String Sunday;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(YYYY_MM_dd.parse(time));
        int now = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -now + 1);
        Monday = YYYY_MM_dd.format(calendar.getTime());
        calendar.add(Calendar.DATE, 6);
        Sunday = YYYY_MM_dd.format(calendar.getTime());
        return Monday + "_" + Sunday;
    }

    public static String getWeek1to7(String time) throws ParseException {
        SimpleDateFormat YYYY_MM_dd = new SimpleDateFormat(PATTERN_yyyy_MM_dd);
        String Monday;
        String Sunday;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(YYYY_MM_dd.parse(time));
        int now = calendar.get(Calendar.DAY_OF_WEEK);
        if (now == 1) {
            calendar.add(Calendar.DATE, -6);
        } else {
            calendar.add(Calendar.DATE, -now + 2);
        }
        Monday = YYYY_MM_dd.format(calendar.getTime());
        calendar.add(Calendar.DATE, 6);
        Sunday = YYYY_MM_dd.format(calendar.getTime());
        return Monday + "_" + Sunday;
    }

    /**
     * 1.当前日期获取当周四-下周三日期,return list
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Stack<String> getDays(String time) throws ParseException {
        SimpleDateFormat YYYY_MM_dd = new SimpleDateFormat(PATTERN_yyyy_MM_dd);
        Stack<String> result = new Stack<>();
        String[] days = getWeek1to7(time).split("_");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(YYYY_MM_dd.parse(days[0]));
        for (int i = 0; i < 7; i++) {
            result.add(YYYY_MM_dd.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return result;
    }

    /**
     * 获取上一个月
     *
     * @param time
     * @return
     */
    public static String getLastMonthTime(String time) {
        String lastTime = null;
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08",
                "09", "10", "11", "12"};
        String month = time.split("-")[1];
        for (int i = 0; i < months.length; i++) {
            if (month.equals("01")) {
                lastTime = (Integer.valueOf(time.split("-")[0]) - 1) + "-"
                        + months[11];
            } else if (months[i].equals(month)) {
                lastTime = time.split("-")[0] + "-" + months[i - 1];
            }
        }
        return lastTime;
    }

    /**
     * 获取上一周
     * 2016-12-01_2016-12-07
     *
     * @param wtime
     * @return
     */
    public static String getLastWeek(String wtime) {
        String[] time = wtime.split("_");
        String lastTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(time[0]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c1.add(Calendar.DATE, -7);
        lastTime = sdf.format(c1.getTime());
//      c1.setEventTime(new SimpleDateFormat("yyyy-MM-dd").parse(time[1]));
        c1.add(Calendar.DATE, 6);
        lastTime = lastTime + "_" + sdf.format(c1.getTime());
        return lastTime;
    }

    /**
     * 获取上一天
     *
     * @param time
     * @return
     */
    public static String getLastDay(String time) {
        String lastDayString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c1.add(Calendar.DATE, -1);
        lastDayString = sdf.format(c1.getTime());
        return lastDayString;
    }

    public static String getNextDay(String time) {
        String lastDayString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c1.add(Calendar.DATE, 1);
        lastDayString = sdf.format(c1.getTime());
        return lastDayString;
    }

    /**
     * 获取当前时间的String格式
     * @return
     */
    public static String getCurrentTimeString2() {
        return parseDateToString(Calendar.getInstance().getTime(),
                PATTERN_YYYY_MM_DD_HHMMSS);
    }

    /**
     * 获取当前时间的Timestamp，精确到毫秒
     *
     * @return Timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        String strDate = parseDateToString(new Date(), PATTERN_YYYY_MM_DD_HHMMSS);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ts = Timestamp.valueOf(strDate);
        return ts;
    }

    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 大于dt2");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1小于dt2");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static Long getStrDateToLong(String day) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = (Date) sdf.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long time = date.getTime();
        return time;
    }

    public static Long getTimeStrToLong(String timeStr) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        return ts;
    }

    public static Date parseLongToDate(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.getTime();
    }


    /**
     * @param time     输入的某一个时间
     * @param timeType 输入的某个时间格式
     * @param type     “year month day”
     * @param k        “-1 表示 向前  1表示向后”
     * @param num      “表示数目”
     * @return
     */
    public static String previousYearOrMonthOrDay(String time, String timeType, String type, Integer k, Integer num) {
        String result = null;
        try {
            Date date = (new SimpleDateFormat(timeType)).parse(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            num = num * k;
            if ("day".equals(type)) {
                cal.add(Calendar.DATE, num);
            } else if ("month".equals(type)) {
                cal.add(Calendar.MONTH, num);
            } else if ("year".equals(type)) {
                cal.add(Calendar.YEAR, num);
            }
            result = (new SimpleDateFormat(timeType)).format(cal.getTime());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }
}
