package com.zzivi.sodexo.login.datasource.api.model;

/**
 * Created by daniel on 11/10/14.
 */
public class LoginRequestApiModel {

    String username;
    String password;
    String pwdaux;
    String mantenerSesion;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwdaux() {
        return pwdaux;
    }

    public void setPwdaux(String pwdaux) {
        this.pwdaux = pwdaux;
    }

    public String getMantenerSesion() {
        return mantenerSesion;
    }

    public void setMantenerSesion(String mantenerSesion) {
        this.mantenerSesion = mantenerSesion;
    }
}
