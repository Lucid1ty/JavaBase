package com.itheima;

public class Test {
    public static void main(String[] args) {
        //创建对象
        Student s1 = new Student();  //通过无参构造器创建对象
        //通过:对象.set方法注入信息
        s1.setName("张三");
        s1.setAge(18);
        s1.setSex('男');
        //通过get方法得到其中的属性
        System.out.println(s1.getName());
        System.out.println(s1.getAge());
        System.out.println(s1.getSex());

        //创建对象
        Student s2 = new Student("李四",24,'男');  //通过有参构造器创建对象
        System.out.println(s2.getName());
        System.out.println(s2.getAge());
        System.out.println(s2.getSex());
    }
}

