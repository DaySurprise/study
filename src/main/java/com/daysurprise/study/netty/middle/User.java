package com.daysurprise.study.netty.middle;

import java.io.Serializable;

/**
 * @Class: com.daysurprise.study.netty.middle.User
 * @Author: daysurprise
 * @Date: 2021/2/1
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class User implements Serializable {

    private Integer age;
    private String name;

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
