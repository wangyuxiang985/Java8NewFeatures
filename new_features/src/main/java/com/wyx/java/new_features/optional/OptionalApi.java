package com.wyx.java.new_features.optional;

import com.wyx.java.new_features.lambda.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional 容器类：用于尽量避免空指针异常
 * @author: yuxiang
 * @date: Create in 2019/4/16
 */
public class OptionalApi {

    /*
    * 一、Optional 容器类：用于尽量避免空指针异常
    * Optional.of(T t) : 创建一个 Optional 实例，不能构建一个null
    * Optional.empty() : 创建一个空的 Optional 实例
    * Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
    * isPresent() : 判断是否包含值
    * orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
    * orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
    * map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
    * flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
    */

    @Test
    public void test1(){
        Optional<Employee> op1 = Optional.of(new Employee(101, "张三", 18, 9999.99));
        Optional<String> op2 = op1.map(Employee::getName);
        System.out.println(op2.get());
        Optional<Double> op3 = op1.flatMap(employee -> Optional.of(employee.getMoney()));
        System.out.println(op3.get());
    }

    @Test
    public void test2(){
        Optional<Employee> optional = Optional.ofNullable(new Employee());
        if(optional.isPresent()){
            System.out.println(optional.get());
        }

        Employee employee = optional.orElse(new Employee("张三"));
        System.out.println(employee);

        Employee employee1 = optional.orElseGet(Employee::new);
        System.out.println(employee1);
    }

    @Test
    public void test3(){
        Optional<Object> o = Optional.ofNullable(null);
        if(o.isPresent()){
            System.out.println(o.get());
        }

        Optional<Object> empty = Optional.empty();
        if(empty.isPresent()){
            System.out.println(empty.get());
        }

    }

}
