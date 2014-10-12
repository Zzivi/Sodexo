package com.zzivi.sodexo.login.domain.mapper;

import com.zzivi.sodexo.login.datasource.api.model.LoginRequestApiModel;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;

/**
 * Created by daniel on 27/09/14.
 */
public class LoginMapper {

    public LoginRequestApiModel transform (LoginCredentials domainModel ){
        LoginRequestApiModel apiModel = new LoginRequestApiModel();
        apiModel.setUsername(domainModel.getUsername());
        apiModel.setPassword(domainModel.getPassword());
        apiModel.setMantenerSesion("1");
        apiModel.setPwdaux("Contraseña");
        return apiModel;
    }
}
