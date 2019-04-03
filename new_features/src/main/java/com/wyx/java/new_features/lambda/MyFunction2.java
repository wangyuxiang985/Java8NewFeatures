package com.wyx.java.new_features.lambda;

/**
 * @author: yuxiang
 * @date: Create in 2019/4/3
 */
@FunctionalInterface
public interface MyFunction2<T,R> {
    R getValue(T t1 , T t2);
}
