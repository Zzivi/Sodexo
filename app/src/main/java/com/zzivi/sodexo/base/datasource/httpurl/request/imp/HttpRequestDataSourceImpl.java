package com.zzivi.sodexo.base.datasource.httpurl.request.imp;

import com.zzivi.sodexo.base.datasource.httpurl.request.HttpRequestDataSource;
import com.zzivi.sodexo.login.datasource.httpurl.LoginHttpUrl;
import com.zzivi.sodexo.login.datasource.httpurl.model.HttpUrlResultModel;
import com.zzivi.sodexo.login.datasource.httpurl.model.LoginRequestUrlModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;

/**
 * Created by daniel on 28/09/14.
 */
public class HttpRequestDataSourceImpl implements HttpRequestDataSource {

    @Inject
    public HttpRequestDataSourceImpl() {}

    public HttpUrlResultModel obtainLoginCookie(LoginHttpUrl loginHttpUrl, LoginRequestUrlModel loginRequestUrlModel) throws IOException {
        return loginHttpUrl.obtainCookies(loginRequestUrlModel);
    }


    @Override
    public String getPageContent(String url, HttpUrlResultModel httpUrlResultModel) throws IOException {
        HttpURLConnection conn;
        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();

        // default is GET
        conn.setRequestMethod("GET");

        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        conn.setRequestProperty("Host", "www.mysodexo.es");
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        //conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Accept-Encoding", "gzip-deflate");
        if (httpUrlResultModel.getCookies() != null) {
            for (String cookie : httpUrlResultModel.getCookies()) {
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }
        }
        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
