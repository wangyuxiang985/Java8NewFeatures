package com.wyx.java.new_features.stream.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 交易类
 * @author: yuxiang
 * @date: Create in 2019/4/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable{
    /**
     * 交易员
     */
    private Trader trader;
    /**
     * 年份
     */
    private int year;
    /**
     * 属性
     */
    private int value;
}
