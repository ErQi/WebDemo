package com.erqi.domain;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 系统用户对象
 */
public class User {
    /*
      `user_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_code` VARCHAR(32) NOT NULL COMMENT '用户账号',
  `user_name` VARCHAR(64) NOT NULL COMMENT '用户名称',
  `user_password` VARCHAR(32) NOT NULL COMMENT '用户密码',
  `user_state` CHAR(1) NOT NULL COMMENT '1:正常,0:暂停',
     */

    /** 主键 */
    private Long user_id;
    /** 账户名 */
    private String user_acct;
    /** 密码 */
    private String user_pwd;
    /** 用户名 */
    private String user_name;
    /** 账户状态 1正常 0暂停 */
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
}
