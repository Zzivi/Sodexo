package com.zzivi.sodexo.base.datasource.api.retrofit.exceptions;

import java.net.HttpURLConnection;

/**
 * Created by daniel on 12/10/14.
 */

public class ApiRuntimeControlledException extends RuntimeException {

    // Code of the error
    private String error;

    // Identifier of the R.string resource error message associated
    private int localizedMessageResource;

    private int status = HttpURLConnection.HTTP_INTERNAL_ERROR;

    public ApiRuntimeControlledException() {
        super();
    }
    /**
     * Constructor with the error code and a description of the error. The
     * status property is set to the default HTTP 500 Internal Server error code
     *
     * @param error
     *            error code
     * @param msg
     *            error description
     */
    public ApiRuntimeControlledException(String error, String msg) {
        super(msg);
        this.error = error;
    }

    /**
     * Constructor with the error code and a description of the error. The
     * status property is set to the default HTTP 500 Internal Server error code
     *
     * @param error
     *            error code
     * @param msg
     *            error description
     * @param status the http status code
     */
    public ApiRuntimeControlledException(String error, String msg, int status) {
        super(msg);
        this.error = error;
        this.status = status;
    }

    public ApiRuntimeControlledException(ApiErrorCode apiErrorCode) {
        super(apiErrorCode.getDescription());
        this.error = apiErrorCode.getCode();
        this.localizedMessageResource = apiErrorCode.getLocalizedMessageResourceId();
    }

    /**
     * Code getter
     * @return the code of the error
     */
    public String getError() {
        return error;
    }

    /**
     * @return 	The http status code.
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Get the Identifier of the R.string resource error message associated
     * @return R.string. identifier
     */
    public int getLocalizedMessageResource() {
        return localizedMessageResource;
    }
}

