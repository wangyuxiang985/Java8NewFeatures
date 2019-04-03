package com.wyx.java.new_features.lambda;

/**
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface修饰
 * @author: yuxiang
 * @date: Create in 2019/4/3
 */
@FunctionalInterface
public interface MyFunction {

    int getValue(int num);
}
