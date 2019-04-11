package com.wyx.java.new_features.lambda;

import lombok.*;

import java.io.Serializable;

/**
 * @author: yuxiang
 * @date: Create in 2019/4/3
 */
@Getter
@Setter
@ToString
public class Employee implements Serializable{
    private int id;
    private String name;
    private int age;
    private double money;

    public Employee(){

    }
    public Employee(String name){
        this.name = name;
    }
    public Employee(int id,String name,int age,double money){
        this.id = id;
        this.name = name;
        this.age = age;
        this.money = money;
    }
}
