package com.zzivi.sodexo.login.datasource;

import java.io.IOException;

/**
 * Created by daniel on 27/09/14.
 */
public interface HomeDataSource {
    boolean getCookies() throws IOException;
}
