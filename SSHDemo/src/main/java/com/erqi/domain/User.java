package com.erqi.domain;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 管理用户类
 */
public class User {
    private Long user_id;
    /**
     * 用户账户
     */
    private String user_acct;
    /**
     * 用户密码
     */
    private String user_pwd;
    /**
     * 用户昵称
     */
    private String user_name;
    /**
     * 用户状态  0:暂停 1:正常
     */
    private String user_state;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_acct() {
        return user_acct;
    }

    public void setUser_acct(String user_acct) {
        this.user_acct = user_acct;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_state() {
        return user_state;
    }

    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_acct='" + user_acct + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_state='" + user_state + '\'' +
                '}';
    }
}
