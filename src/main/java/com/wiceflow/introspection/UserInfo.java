package com.wiceflow.introspection;

/**
 * @author BF
 * @date 2018/7/15
 *      Java 内省类 测试
 */
public class UserInfo {

    private long userId;
    private String userName;
    private int age;
    private String emailAddress;

    public long getuserId() {
        return userId;
    }
    public void setuserId(long userId) {
        this.userId = userId;
    }
    public String getuserName() {
        return userName;
    }
    public void setuserName(String userName) {
        this.userName = userName;
    }
    public int getage() {
        return age;
    }
    public void setage(int age) {
        this.age = age;
    }
    public String getemailAddress() {
        return emailAddress;
    }
    public void setemailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public UserInfo() {
    }

    public UserInfo(long userId, String userName, int age, String emailAddress) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.emailAddress = emailAddress;
    }
}