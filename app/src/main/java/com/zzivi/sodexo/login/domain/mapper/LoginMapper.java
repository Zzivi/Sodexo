package com.zzivi.sodexo.login.domain.mapper;

import com.zzivi.sodexo.login.datasource.model.LoginRequestUrlModel;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;

/**
 * Created by daniel on 27/09/14.
 */
public class LoginMapper {

    public LoginRequestUrlModel transform (LoginCredentials domainModel ){
        LoginRequestUrlModel apiModel = new LoginRequestUrlModel();
        apiModel.setUsername(domainModel.getUsername());
        apiModel.setPassword(domainModel.getPassword());
        return apiModel;
    }
}
