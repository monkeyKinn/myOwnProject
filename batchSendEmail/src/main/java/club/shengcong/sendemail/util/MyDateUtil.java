package club.shengcong.sendemail.util;

import cn.hutool.core.lang.Console;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

/**
 * 日期工具类
 *
 * @author 金聖聰
 * @version v1.0
 * @email jinshengcong@163.com
 * @copyright 金聖聰©
 * @since Created in 2023/1/11 16:42
 */
public class MyDateUtil
{
    private MyDateUtil(){}

    /**
     * 获取某一年的第一天
     * @param year 年份
     * @return java.time.LocalDate
     * @author 金聖聰
     * @email jinshengcong@163.com
     * @date 2023/1/11 16:56
     * @copyright 金聖聰©
     */
    public static LocalDate getFirstDayOfYear(int year){
        return LocalDate.of(year, Month.JANUARY, 1);
    }


    /**
     * 查找一年中的第一个 星期几
     * @param firstDayOfYear 某一年的第一天
     * @param dayOfWeek 星期几
     * @return java.time.LocalDate
     * @author 金聖聰
     * @email jinshengcong@163.com
     * @date 2023/1/11 17:19
     * @copyright 金聖聰©
     */
    public static LocalDate getFstDayOfWeekInYear(LocalDate firstDayOfYear,DayOfWeek dayOfWeek){
        return firstDayOfYear.with(firstInMonth(dayOfWeek));
    }

    /**
     * 获取指定年份中每一个星期几
     * @param year 指定的年
     * @param dayOfWeek 需要获取的星期几
     * @return java.util.List<java.time.LocalDate>
     * @author 金聖聰
     * @email jinshengcong@163.com
     * @date 2023/1/11 17:33
     * @copyright 金聖聰©
     */
    public static List<LocalDate> getPerDayOfWeekInYear(int year,DayOfWeek dayOfWeek){
        // 创建代表一年中第一天的LocalDate对象。
        LocalDate firstDayOfYear = getFirstDayOfYear(year);
        // 查找一年中的第一个星期几
        LocalDate firstDayOfWeekInYear = getFstDayOfWeekInYear(firstDayOfYear,dayOfWeek);
        List<LocalDate> results = new ArrayList<>();
        do
        {
            results.add(firstDayOfWeekInYear);
            // 通过将Period.ofDays(7)添加到当前星期一来循环获取每个星期一。
            firstDayOfWeekInYear = firstDayOfWeekInYear.plus(Period.ofDays(7));
        } while (firstDayOfWeekInYear.getYear() == year);
        return results;
    }

    public static void main(String[] args) {
        List<LocalDate> perMondayOfYear = getPerDayOfWeekInYear(2023,DayOfWeek.THURSDAY);
        perMondayOfYear.forEach(Console :: log);
    }
}
