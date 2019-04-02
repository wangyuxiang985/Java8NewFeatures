package com.wyx.java.new_features.lambda;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * lambda表达式基础语法
 * Java8 中引入了一个新的操作符 "->" 该操作为箭头操作符或 Lambda 操作符箭头操作符将 Lambda 表
 *达式拆分成两部分：
 *左侧：Lambda 表达式的参数列表
 *右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
 *
 *上联：左右遇一括号省
 *下联：左侧推断类型省
 *横批：能省则省
 * @author: yuxiang
 * @date: Create in 2019/4/2
 */
public class BasicGrammar {

    /**
     * 语法格式一：无参数，无返回值
     () -> System.out.println("Hello Lambda!");
     */
    @Test
    public void test1(){
        int num = 0;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };
        runnable.run();

        System.out.println("-------------------------------------");
        Runnable r = () ->
            System.out.println("Hello World!" + (num+1));
        r.run();
    }

    /**
     * 语法格式二：有一个参数，并且无返回值
     *(x) -> System.out.println(x)
     * 若只有一个参数，（）可以不写
     * x-> System.out.println(x)
     */
    @Test
    public void test2(){
        Consumer<String> c = x -> System.out.println(x);
        c.accept("最美天蝎座");
    }

    /**
     * 语法格式四：有两个以上的参数，有返回值，并且 a Lambda 体中有多条语句, , 必须有大括号} {}
     *Comparator<Integer> com = (x, y) -> {
     *System.out.println(" 函数式接口 ");
     *return Integer.compare(x, y);
     *};
     */
    @Test
    public void test3(){
        Comparator<Integer> com = (x,y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
    }

    /**
     * 语法格式五：若 Lambda 体中只有一条语句，return 和大括号都可以省略不写
     *Comparator<Integer> com = (x, y) -> Integer.compare(x,y);
     * Lambda 表达式的参数列表的数据类型可以省略不写，因为 JVM 编译器通过上下文推断
     出，数据类型，即“ 类型推断“
     */
    @Test
    public void test4(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
    }



}
