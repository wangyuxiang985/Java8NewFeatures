package com.wyx.java.new_features.lambda.lambda2;

import com.wyx.java.new_features.lambda.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 六、 方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 * @author: yuxiang
 * @date: Create in 2019/4/11
 */
public class MethodReference {


    /**
     * 对象的引用 :: 实例方法名 （对象引用和实例方法的 返回类以及形参必须一致）
     */
    @Test
    public void test1(){
        PrintStream printStream = System.out;
        Consumer<String> consumer = s -> printStream.println(s);
        consumer.accept("Hello World !");
        System.out.println("----------------------");
        //对象::实例方法名 Lambda体的返回值类型以及形参必须与println方法的返回值类型形参一致
        Consumer<String> con = printStream :: println;
        con.accept("Hello China");

        System.out.println("######################");
        //类::实例方法名
        Consumer<String> consumer1 = System.out :: println;
        consumer1.accept("I Love You");
    }

    @Test
    public void test2(){
        Employee emp = new Employee(1,"张三",22,666666.66);
        Supplier<String> spu = () -> emp.getName();
        System.out.println("姓名：" + spu.get());
        System.out.println("-------------------");
        Supplier<Integer> spu2 = emp :: getId;
        System.out.println("Id:" + spu2.get());
        System.out.println("#####################");
        Supplier<Double> spu3 = emp :: getMoney;
        System.out.println("银行卡余额：" + spu3.get() + "元");
    }

    /**
     * 类名 :: 静态方法名
     */
    @Test
    public void test4(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        System.out.println("------------------");
        Comparator<Integer> com2 = Integer :: compare;
        int compare = com2.compare(3, 4);
        System.out.println(compare);
    }

    @Test
    public void test5(){
        BiFunction<Double,Double,Double> bif = (x,y) -> Math.max(x,y);
        System.out.println(bif.apply(1.5,3.6));
        System.out.println("------------");
        BiFunction<Double,Double,Double> bif2 = Math :: max;
        System.out.println(bif2.apply(2.6,5D));
    }

    /**
     * 类名 :: 实例方法名
     * 若 Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，
     * 格式： ClassName::MethodName
     */
    @Test
    public void test6(){
        BiPredicate<String,String> bip = (x,y) -> x.equals(y);
        System.out.println(bip.test("abc","abc"));
        System.out.println("-------------------");
        //若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例
        //方法的参数时，格式： ClassName::MethodName
        BiPredicate<String,String> bip2 = String :: equals;
        System.out.println(bip2.test("woshi","kaixin"));

        System.out.println("#####################");
        Function<Employee,String> fun = (emp) -> emp.getName();
        System.out.println(fun.apply(new Employee()));

        System.out.println("++++++++++++++++++++");
        Function<Employee,String> fun2 = Employee::getName;
        System.out.println(fun2.apply(new Employee(1,"ori",21,22.2)));

    }

    /**
     * 构造器引用:: 构造器的参数列表，需要与函数式接口中参数列表保持一致！
     * 类名 :: new
     */
    @Test
    public void test7(){
        Supplier<Employee> sup = () -> new Employee();
        System.out.println(sup.get());
        System.out.println("-------------");
        //调用对象的哪个构造器取决于lambda的方法的入参（sup2.get()）,这里无参，就调用无参构造器
        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());

        //调用对象的哪个构造器取决于lambda的方法的入参（fun.apply("张三")）,这里一个参数，就调用一个参数的构造器
        Function<String,Employee> fun = Employee :: new;
        System.out.println(fun.apply("张三"));
    }

    /**
     * 数组引用
     * 类型[] :: new;
     */
    @Test
    public void test8(){
        Function<Integer,String []> fun = (args) -> new String[args];
        System.out.println(fun.apply(10).length);

        System.out.println("------------------");
        Function<Integer,String []> fun2 = String [] ::new;
        System.out.println(fun2.apply(5).length);
    }


}
