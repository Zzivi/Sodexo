package com.zzivi.sodexo.login.datasource.httpurl;


import com.zzivi.sodexo.login.datasource.httpurl.model.HttpUrlResultModel;
import com.zzivi.sodexo.login.datasource.httpurl.model.LoginRequestUrlModel;

import java.io.IOException;

/**
 * Created by daniel on 28/09/14.
 */
public interface LoginHttpUrl {

    public HttpUrlResultModel obtainCookies(LoginRequestUrlModel loginRequestUrlModel) throws IOException;
}
