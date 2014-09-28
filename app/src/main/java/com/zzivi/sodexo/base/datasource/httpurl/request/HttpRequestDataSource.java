package com.zzivi.sodexo.base.datasource.httpurl.request;

import com.zzivi.sodexo.login.datasource.httpurl.LoginHttpUrl;
import com.zzivi.sodexo.login.datasource.httpurl.model.HttpUrlResultModel;
import com.zzivi.sodexo.login.datasource.httpurl.model.LoginRequestUrlModel;

import java.io.IOException;

/**
 * Created by daniel on 28/09/14.
 */
public interface HttpRequestDataSource {
    public HttpUrlResultModel obtainLoginCookie(LoginHttpUrl loginHttpUrl,
                              LoginRequestUrlModel loginRequestUrlModel) throws IOException;

    public String getPageContent(String url, HttpUrlResultModel httpUrlResultMode) throws IOException;
}
