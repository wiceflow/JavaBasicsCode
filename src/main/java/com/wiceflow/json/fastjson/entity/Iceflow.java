package com.wiceflow.json.fastjson.entity;

/**
 * Created by BF on 2017/12/6.
 */
public class Iceflow {
    private int age;
    private String name;
    private String word;
    private String school;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "\r\niceflow{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", word='" + word + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
