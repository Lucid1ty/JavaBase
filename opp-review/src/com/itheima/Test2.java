package com.itheima;

public class Test2 {
    public static void main(String[] args) {
        //复习this的作用
        Car c = new Car("奔驰", 250000);
        System.out.println(c.getName());
        System.out.println(c.getPrice());
        c.goWith("法拉利");

    }
}
