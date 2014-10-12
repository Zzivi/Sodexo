package com.zzivi.sodexo.base.datasource.api.retrofit.exceptions;

import com.zzivi.sodexo.R;

/**
 * Created by daniel on 12/10/14.
 */
public enum ApiErrorCode {
    API_HTTP_UNAUTHORIZED("401", "HTTP UNAUTHORIZED", R.string.ApiUnauthorizedException);

    String code;
    String description;

    int localizedMessageResourceId;

    private ApiErrorCode(String code, String desc, int messageResourceId) {
        this.code = code;
        this.description = desc;
        this.localizedMessageResourceId = messageResourceId;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public int getLocalizedMessageResourceId() {
        return localizedMessageResourceId;
    }
}
