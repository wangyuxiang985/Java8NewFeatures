package com.wyx.java.new_features.stream.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 交易员类
 * @author: yuxiang
 * @date: Create in 2019/4/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trader implements Serializable{
    /**
     * 姓名
     */
    private String name;
    /**
     * 城市
     */
    private String city;
}
