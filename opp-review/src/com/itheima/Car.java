package com.itheima;

public class Car {
    private String name;
    private double price;

    public Car() {
    }

    public Car(String name, double price){
        //this代表了当前对象,也是在你调用这个构造器时产生的对象
        //作用:用于区分当前这个变量是局部变量还是当前对象的成员变量
        this.name = name;
        this.price = price;
    }

    public void goWith(String name){
        //谁调用这个方法,this就代表谁！
        System.out.println(name + "正在和" + this.name + "比赛");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
