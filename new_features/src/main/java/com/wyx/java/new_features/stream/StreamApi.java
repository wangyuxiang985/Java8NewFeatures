package com.wyx.java.new_features.stream;

import com.wyx.java.new_features.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 是 Java8 中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行非常复
 *杂的查找、过滤和映射数据等操作。 使用 Stream API 对集合数据进行操作，就类似于使用 SQL 执行的
 *数 据库查询。也可以使用 Stream API 来并行执行操作。简而言之， Stream API 提供了一种高效且易
 *于使用的处理数据的方式。
 * 流 (Stream) 到底是什么呢 ?
 *是数据渠道，用于操作数据源(集合、数组等)所生成的元素序列。
 *“集合讲的是数据，流讲的是计算!”
 *注意:
 *1、Stream 自己不会存储元素。
 *2、Stream 不会改变源对象。相反，他们会返回一个持有结果的新 Stream。
 *3、Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行
 * @author: yuxiang
 * @date: Create in 2019/4/13
 */
public class StreamApi {


    /**
     * 创建stream流
     */
    @Test
    public void test1(){
        //1. Collection 提供了两个方法 stream() 与 parallelStream()
        List<String> list = new ArrayList<>();
        //获取一个顺序流
        Stream<String> stream = list.stream();
        //获取一个并行流
        Stream<String> stringStream = list.parallelStream();

        //2. 通过 Arrays 中的 stream() 获取一个数组流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

        //3. 通过 Stream 类中静态方法 of()
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 45);

        //4. 创建无限流
        //迭代
        Stream<Integer> limit = Stream.iterate(0, x -> x + 2).limit(5);
        limit.forEach(System.out :: println);
        System.out.println("----------------------");
        //生成
        Stream<Double> limit1 = Stream.generate(Math::random).limit(5);
        limit1.forEach(System.out :: println);
    }
    public static Stream<String> filterCharacter(String str) {
        List<String> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch.toString());
        }
        return list.stream();
    }

    //2、Stream 中间操作
    /*
    *2.1、筛选与切片
    *filter——接收 Lambda ， 从流中排除某些元素。
    *limit——截断流，使其元素不超过给定数量。
    *skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
    *distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
    */
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 3333.33),
            new Employee(104, "赵六", 8, 8888.88),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    //内部迭代：迭代操作 Stream API 内部完成
    @Test
    public void test2(){
        //所有的中间操作不会做任何的处理
        Stream<Employee> stream = emps.stream().filter(employee -> {
            System.out.println("测试中间操作");
            return employee.getAge() > 35;
        });
        //只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
        stream.forEach(System.out :: println);
    }

    //外部迭代

    @Test
    public void test3(){
        Iterator<Employee> iterator = emps.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test4(){
        emps.stream().filter(employee -> {
            System.out.println("短路");
            return employee.getMoney() > 5000;
        }).limit(3).forEach(System.out :: println);

    }

    @Test
    public void test5(){
        emps.stream()
                .filter(employee -> employee.getMoney() > 5000)
                .skip(2)
                .forEach(System.out :: println);
    }

    @Test
    public void test6(){
        emps.stream()
                .distinct()
                .forEach(System.out :: println);
    }


    /*
    *2.2、映射
    *map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个
    *元素上，并将其映射成一个新的元素。
    *flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
    */
    @Test
    public void test7(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out :: println);
        System.out.println("--------------------------");
        emps.stream()
                .map(Employee :: getName)
                .forEach(System.out :: println);

    }

    /*
    2.3、排序
        sorted()——自然排序
        sorted(Comparator com)——定制排序
     */
    @Test
    public void test8(){
        emps.stream()
                .map(Employee::getName)
                .sorted()
                .forEach(System.out :: println);
        System.out.println("---------------------------------");

        emps.stream()
                .sorted((x,y) -> {
                    if(x.getAge() == y.getAge()){
                        return x.getName().compareTo(y.getName());
                    }else {
                        return Integer.compare(x.getAge(),y.getAge());
                    }
                })
                .forEach(System.out :: println);
    }

    //3、Stream 终止操作
    /*
    allMatch——检查是否匹配所有元素
    anyMatch——检查是否至少匹配一个元素
    noneMatch——检查是否没有匹配的元素
    findFirst——返回第一个元素
    findAny——返回当前流中的任意元素
    count——返回流中元素的总个数
    max——返回流中最大值
    min——返回流中最小值
    */

    @Test
    public void test9(){
        boolean b = emps.stream()
                .allMatch(employee -> employee.getAge() >= 18);
        System.out.println("集合中是否所有人年龄都大于等于18岁："+b);
        System.out.println("##################");
        boolean b1 = emps.stream()
                .anyMatch(employee -> employee.getAge() >= 18);
        System.out.println("集合中是否有人年龄大于等18岁:"+b1);
        System.out.println("##################");
        boolean b2 = emps.stream()
                .noneMatch(employee -> employee.getName().equals("曹操"));
        System.out.println("集合中没有人叫曹操:"+b2);
        System.out.println("##################");
        Optional<Employee> first = emps.stream()
                .sorted(Comparator.comparingDouble(Employee::getMoney))
                .findFirst();
        System.out.println("集合中最穷的员工是："+first.get());
        System.out.println("##################");
        //Stream()线行流，parallelStream()并行流-多个线程同时找，谁找到算谁
        Optional<Employee> any = emps.parallelStream()
                .filter(employee -> employee.getAge() >= 18)
                .findAny();
        System.out.println("集合中任意一个大于18岁的员工:"+any.get());
        System.out.println("##################");
        long count = emps.stream()
                .filter(employee -> employee.getAge() >= 18)
                .count();
        System.out.println("集合中年龄大于等于18岁的有"+count+"人");
        System.out.println("##################");
        Optional<Employee> max = emps.stream()
                .max(Comparator.comparingDouble(Employee::getMoney));
        System.out.println("集合中最有钱的一个员工为:"+max.get());
        System.out.println("##################");
        Optional<Integer> min = emps.stream()
                .map(Employee::getAge)
                .min(Integer::compareTo);
        System.out.println("集合中年龄最小的一个员工的年龄为:"+min.get());
    }

    /**
     * 注意：流进行了终止操作后，不能再次使用
     */
    @Test
    public void test10(){
        Stream<Employee> employeeStream = emps.stream()
                .filter(employee -> employee.getAge() >= 18);
        long count = employeeStream.count();
        System.out.println("集合中年龄大于等于18岁的人数:"+count);
        //关闭后在使用直接报错：java.lang.IllegalStateException: stream has already been operated upon or closed
        employeeStream.map(Employee::getAge)
                .max(Integer::compareTo);
    }

    /*
    终止操作：
    归约
    reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流
    中元素反复结合起来，得到一个值。
    */
    @Test
    public void test11(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println("集合元素求和为:"+sum);
        System.out.println("##################");
        Optional<Double> reduce = emps.stream()
                .map(Employee::getMoney)
                .reduce(Double::sum);
        System.out.println("所有员工的金钱总和:"+reduce.get());
        System.out.println("##################");
        Optional<Integer> reduce1 = emps.stream()
                .map(Employee::getName)
                .flatMap(StreamApi::filterCharacter)
                .map(str -> {
                    if ("六".equals(str)) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .reduce(Integer::sum);
        System.out.println("集合中名字中有‘六’的总人数:"+reduce1.get());
    }

    /*
    collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做
    汇总的方法，Collectors是Collector的工具类
    */
    @Test
    public void test12(){
        List<String> collect = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out :: println);
        System.out.println("##################");
        Set<String> collect1 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        collect1.forEach(System.out :: println);
        System.out.println("##################");
        HashSet<String> collect2 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        collect2.forEach(System.out :: println);
        System.out.println("##################");
        Optional<Double> collect3 = emps.stream()
                .map(Employee::getMoney)
                .collect(Collectors.maxBy(Double::compare));
        System.out.println("集合中最有钱的员工是:"+collect3.get());
    }

    //分组
    @Test
    public void test13(){
        Map<Integer, List<Employee>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(collect);
    }

    //多级分组
    @Test
    public void test14(){
        Map<Integer, Map<String, List<Employee>>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getAge, Collectors.groupingBy(emp -> {
                    if (emp.getMoney() >= 8000) {
                        return "小康";
                    } else if (emp.getMoney() >= 5000) {
                        return "温饱";
                    } else {
                        return "贫困";
                    }
                })));
        System.out.println(collect);
    }

    //分区
    @Test
    public void test15(){
        Map<Boolean, List<Employee>> collect = emps.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getMoney() >= 5000));
        System.out.println(collect);
        System.out.println("##################");
        Optional<Double> collect1 = emps.stream()
                .map(Employee::getMoney)
                .collect(Collectors.reducing(Double::sum));
        System.out.println(collect1.get());
    }




}
