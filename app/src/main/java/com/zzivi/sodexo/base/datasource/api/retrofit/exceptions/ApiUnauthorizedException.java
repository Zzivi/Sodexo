package com.zzivi.sodexo.base.datasource.api.retrofit.exceptions;

/**
 * Created by daniel on 12/10/14.
 */
public class ApiUnauthorizedException extends ApiRuntimeControlledException {
    public ApiUnauthorizedException() {
        super(ApiErrorCode.API_HTTP_UNAUTHORIZED);
    }
}
