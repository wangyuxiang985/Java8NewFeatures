package com.wyx.java.new_features.stream;

import org.junit.Test;

import java.util.stream.LongStream;

/**
 * 并行流：就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。
 StreamAPI 可以声明性地通过 parallel() 与 sequential() 在并行流与顺序流之间进行切换。
 * @author: yuxiang
 * @date: Create in 2019/4/14
 */
public class ParallelFlow {

    @Test
    public void test1(){
        System.out.println("-------------并行流求和-------------");
        long start = System.currentTimeMillis();
        Long sum = LongStream.rangeClosed(0L, 1000000000L)
                .parallel()
                .sum();

        long end = System.currentTimeMillis();
        System.out.println("0-1000000000求和为:"+sum+",耗时为:"+(end-start));
        System.out.println("-------------顺序流求和-------------");

        long start2 = System.currentTimeMillis();
        Long sum2 = LongStream.rangeClosed(0L, 1000000000L)
                .sequential()
                .sum();

        long end2 = System.currentTimeMillis();
        System.out.println("0-1000000000求和为:"+sum2+",耗时为:"+(end2-start2));

    }
}
