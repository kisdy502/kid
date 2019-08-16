package com.sdt.kid.bean;

import javax.validation.constraints.NotBlank;

/**
 * Created by Luo_xuri on 2017/9/30.
 */
public class NameAndPass {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "[" + username + "|" + password + "]";
    }
}
