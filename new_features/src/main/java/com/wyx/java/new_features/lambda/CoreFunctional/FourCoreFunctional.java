package com.wyx.java.new_features.lambda.CoreFunctional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置的四大核心函数式接口
 * @author: yuxiang
 * @date: Create in 2019/4/10
 */
public class FourCoreFunctional {

    /**
     * Consumer<T> : 消费型接口 （无返回值，需要传参数，只进不出）
     * void accept(T t);
     */
    private void happy(double money , Consumer<Double> con){
        con.accept(money);
    }

    @Test
    public void test1(){
        happy(1000.00,x -> System.out.println("每次点86号技师都需要"+ x + "软妹币"));
    }

    /**
     * Supplier<T> : 供给型接口 （有返回值，无入参）
     *T get();
     */
    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num , Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
             Integer n = sup.get();
            list.add(n);
        }
        return list;
    }

    @Test
    public void test2(){
        List<Integer> numList = getNumList(5, () -> (int) (Math.random() * 1000));
        for (Integer integer : numList) {
            System.out.println(integer);
        }
    }

    /**
     * Function<T, R> : 函数型接口 传进去 T, 返回 R R
     *R apply(T t);
     */
    //需求：用于处理字符串
    public String strHandler(String str , Function<String,String> func){
        return func.apply(str);
    }

    @Test
    public void test3(){
        String s = strHandler("我,乃,常山,赵子龙", x -> x.substring(1, 3));
        System.out.println(s);

        String s1 = strHandler("我,乃,常山,赵子龙", x -> x.replace(",",""));
        System.out.println(s1);
    }

    /**
     * Predicate<T> : 断言型接口回 （做一个判断，符合条件的返回 true ）
     *boolean test(T t);
     */
    //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> strs , Predicate<String> pre){
        List<String> list = new ArrayList<>();
        for (String str : strs) {
            if(pre.test(str)){
                list.add(str);
            }
        }
        return list;
    }

    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello","World","I","Love","You");
        List<String> list1 = filterStr(list, x -> x.length() > 3);
        for (String s : list1) {
            System.out.println(s);
        }
    }

}
