package com.wyx.java.new_features.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 练习一些lambada的实例应用
 * @author: yuxiang
 * @date: Create in 2019/4/2
 */
public class TestLambda {

    /**
     * 利用自定义的函数式接口进行数字运算
     */
    @Test
    public void test1(){

        int sum = operation(100, new MyFunction() {
            @Override
            public int getValue(int num) {
                return num + 100;
            }
        });
        System.out.println(sum);
        System.out.println("**************");
        int num = operation(100 , x -> x * x);
        System.out.println(num);
        System.out.println("--------------");
        System.out.println(operation(200 , y -> y + 200));

    }
    public int operation(int num , MyFunction myFunction){
        return myFunction.getValue(num);
    }

    /**
     * 利用lambda进行排序
     */
    @Test
    public void test2(){
        List<Employee> list = Arrays.asList(
                new Employee(1,"张三",3,22.2),
                new Employee(3,"李四",30,33.3),
                new Employee(4,"王五",15,2.2),
                new Employee(2,"马六",15,50)
        );
        Collections.sort(list,(x,y) -> {
            if(x.getAge() == y.getAge()){
                return x.getName().compareTo(y.getName());
            }else {
                return -Integer.compare(x.getAge(),y.getAge());
            }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
        System.out.println("++++++++++++++++++++++++");
        List<Employee> collect = list.stream().sorted(Comparator.comparingDouble(Employee::getMoney)).collect(Collectors.toList());
        for (Employee employee : collect) {

            System.out.println(employee);
        }
    }

    /**
     * 两个泛型的函数式接口测试
     */
    @Test
    public void test3(){
        op(100L,100L,(x,y) -> x + y);
        op(200L,200L,(x,y) -> x * y);
    }

    /**
     * 处理两个long型数据
     */
    private void op(Long l1,Long l2,MyFunction2<Long , Long> my2){
        System.out.println(my2.getValue(l1,l2));
    }




}
