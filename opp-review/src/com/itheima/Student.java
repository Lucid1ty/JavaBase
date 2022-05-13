package com.itheima;

public class Student {
    //成员变量
    private String name;
    private int age;
    private char sex;

    //构造器的作用：初始化类的对象,并返回对象的地址
    //无参构造器,初始化对象时,成员变量的数据采用默认值
    public Student() {
    }

    //有参数构造器,在初始化对象的时候,同时可以为对象进行赋值
    public Student(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
