package jcx_092217107.xx.note.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 2019/5/15:15:30
 */

public class DateUtil {


    /**
     * 获取系统时间戳
     *
     * @return
     */
    public static long getCurTimeLong() {
        long time = System.currentTimeMillis();
        return time;
    }

    /**
     * 获取当前时间
     *
     * @param pattern
     * @return
     */
    public static String getCurDate(String pattern) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date());
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = getStringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 时间戳转换成字符窜
     *
     * @param milSecond
     * @param pattern
     * @return
     */
    public static String getLongToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 字符窜转换成字符窜
     *
     * @param milSecond
     * @param pattern
     * @return
     */
    public static String getStringToString(String milSecond, String pattern, String pattern2) {
        return getLongToString(getStringToLong(milSecond, pattern), pattern2);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static long getStringToLong(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 将字符串转为DATE
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * 将字符串转为Calendar
     */
    public static Calendar getStringToCalender(String dateString, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    //十位时间戳字符串转月日
    public static String MonthDay(String time, String pattern) {
        SimpleDateFormat sdr = new SimpleDateFormat(pattern);
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 日期减几年
     */
    public static String dateMinusYear(String str, int YEAR, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date dt = sdf.parse(str);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.YEAR, YEAR);// 日期减1年
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }


    /**
     * 日期减几月
     */
    public static String dateMinusMonth(String str, int MONTH, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date dt = sdf.parse(str);//将字符串生成Date
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);//使用给定的 Date 设置此 Calendar 的时间。
        rightNow.add(Calendar.MONTH, MONTH);// 日期减1个月
        Date dt1 = rightNow.getTime();//返回一个表示此 Calendar 时间值的 Date 对象。
        String reStr = sdf.format(dt1);//将给定的 Date 格式化为日期/时间字符串，并将结果添加到给定的 StringBuffer。
        return reStr;
    }

    /**
     * 日期减几ri
     */
    public static String dateMinusDay(String str, int day, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date dt = sdf.parse(str);//将字符串生成Date
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);//使用给定的 Date 设置此 Calendar 的时间。
        rightNow.add(Calendar.DAY_OF_YEAR, day);// 日期减1个月
        Date dt1 = rightNow.getTime();//返回一个表示此 Calendar 时间值的 Date 对象。
        String reStr = sdf.format(dt1);//将给定的 Date 格式化为日期/时间字符串，并将结果添加到给定的 StringBuffer。
        return reStr;
    }

    /**
     * 对比返天和小时
     */
    public static String TimeDifference(long start, long end) {

        long between = end - start;

        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                - min * 60 * 1000 - s * 1000);
//        String timeDifference = day + "天" + hour + "小时" + min + "分" + s + "秒" + ms
//                + "毫秒";
        String timeDifference;
        if (day == 0) {
            timeDifference = hour + "小时";
        } else {
            timeDifference = day + "天" + hour + "小时";
        }
        return timeDifference;
    }

    /**
     * 对比返回秒
     */
    public static long TimeDifference_s(long start, long end) {
        long between = end - start;
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                - min * 60 * 1000 - s * 1000);
        long time = 0;
        if ((((day * 24 + hour) * 60 + min) * 60 + s) <= 0) {
            time = (((day * 24 + hour) * 60 + min) * 60 + s) + 86399;
        } else {
            time = (((day * 24 + hour) * 60 + min) * 60 + s);
        }
        return time;
    }

}
