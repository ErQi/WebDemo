package com.erqi.domain;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: 管理用户类
 */
public class User {
    private Long uid;
    private String account;
    private String password;
    private String name;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
