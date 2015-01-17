package com.zzivi.sodexo.login.domain.model;

/**
 * Created by daniel on 27/09/14.
 */
public class LoginCredentials {
    String username;
    String password;
    boolean storeCredentials;

    public boolean isStoreCredentials() {
        return storeCredentials;
    }

    public void setStoreCredentials(boolean storeCredentials) {
        this.storeCredentials = storeCredentials;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
