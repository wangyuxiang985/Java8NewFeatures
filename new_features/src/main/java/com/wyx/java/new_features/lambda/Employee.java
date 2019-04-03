package com.wyx.java.new_features.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: yuxiang
 * @date: Create in 2019/4/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable{
    private int id;
    private String name;
    private int age;
    private double money;
}
