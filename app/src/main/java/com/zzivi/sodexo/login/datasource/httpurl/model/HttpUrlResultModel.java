package com.zzivi.sodexo.login.datasource.httpurl.model;

import java.util.List;

/**
 * Created by daniel on 28/09/14.
 */
public class HttpUrlResultModel {

    private List<String> cookies;

    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }

    public List<String> getCookies() {
        return cookies;
    }
}
