package com.itheima;

public class People {
    //1.成员私有化(合理隐藏)
    //private修饰的变量只能在本类中访问
    private int age;
    
    //2.提供公开化的getter setter方法(合理暴露)
    public int getAge(){
        return age;
    }

    public void setAge(int age){
        if (age >= 0 && age <= 200){
            this.age = age;
        }else {
            System.out.println("年龄非法！");
        }
    }
}
