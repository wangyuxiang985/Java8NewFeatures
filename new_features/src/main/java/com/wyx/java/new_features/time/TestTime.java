package com.wyx.java.new_features.time;

import com.sun.media.sound.SoftTuning;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * 使用LocalDate、LocalTime、LocalDateTime类的实例是不可变的对象，分别表实：日期、时间、日期和时间
 * 提供了简单的日期或时间，并不包含当前时间信息，也不包含与时区相关的信息
 *
 * Instant时间戳：用于“时间戳”运算，开始于1970年1月1日午夜时分；
 *
 * Duration：用于计算两个“时间”间隔
 * Period：用于计算两个“日期”间隔
 *
 * 日期的操纵：
 * TemporaAdjuster：时间校正器，eg:将日期调整到”下个周日“等操作
 * TemporalAdjusters：该类通过静态方法提供了大量的常用TemporalAdjuster的实现
 * eg:获取下周日：LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
 *
 * 解析与格式化：
 * java.time.format.DateTimeFormatter类：提供了三种格式化方法：
 * 预定义的标准格式
 * 语言环境相关的格式
 * 自定义的格式
 *
 * 时区的处理
 * Java8中加入了对时区的支持，分类别为：ZonedDate、ZonedTime、ZonedDateTime
 * 每个时区都有对应ID，地区id为“{区域}/{城市}”的格式 eg:Asia/Shanghai
 * ZoneId:该类中包含所有的时区信息
 * getAvailableZonelds()：可以获取所有时区信息
 * of(id):用于指定的时区信息获取ZoneId对象
 *
 * @author: yuxiang
 * @date: Create in 2019/4/16
 */
public class TestTime {

    //ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
    @Test
    public void test1(){
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(now);
        System.out.println("#################");
        ZonedDateTime now1 = ZonedDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(now1);
    }

    @Test
    public void test2(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out :: println);
    }

    //DateTimeFormatter : 解析和格式化日期或时间
    @Test
    public void test3(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日\n" +
                "HH:mm:ss E");
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(dateTimeFormatter);
        System.out.println(format);
        System.out.println("##############");
    }

    //TemporalAdjuster : 时间校正器
    @Test
    public void test4(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime localDateTime = now.withDayOfMonth(10);
        System.out.println(localDateTime);
        LocalDateTime with = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(with);

        //自定义：下一个工作日
        LocalDateTime ldt5 = now.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dow = ldt4.getDayOfWeek();
            if(dow.equals(DayOfWeek.FRIDAY)){
                return ldt4.plusDays(3);
            }else if(dow.equals(DayOfWeek.SATURDAY)){
                return ldt4.plusDays(2);
            }else{
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    //Duration : 用于计算两个“时间”间隔
    //Period : 用于计算两个“日期”间隔
    @Test
    public void test5(){
        Instant now = Instant.now();
        System.out.println("----------------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       Instant now2 = Instant.now();
        System.out.println("所耗费的时间为："+Duration.between(now,now2));

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2011, 1, 1);
        Period pe = Period.between(ld2, ld1);
        System.out.println(pe.getYears());
        System.out.println(pe.getMonths());
        System.out.println(pe.getDays());
    }

    //Instant : 时间戳。 （使用 Unix 元年 1970年1月1日 00:00:00 所经历的毫秒值）
    @Test
    public void test6(){
        Instant now = Instant.now();
        System.out.println(now);
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(now.getNano());

        Instant instant = Instant.ofEpochSecond(5);
        System.out.println(instant);
    }

    //LocalDate、LocalTime、LocalDateTime
    @Test
    public void test7(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime of = LocalDateTime.of(2018, 8, 8, 8, 8, 8);
        System.out.println(of);
        LocalDateTime localDateTime = of.plusYears(10L);
        System.out.println(localDateTime);
        LocalDateTime localDateTime1 = of.minusMonths(3L);
        System.out.println(localDateTime1);

        System.out.println(now.getYear());
        System.out.println(now.getMonth());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getHour());
    }
}
