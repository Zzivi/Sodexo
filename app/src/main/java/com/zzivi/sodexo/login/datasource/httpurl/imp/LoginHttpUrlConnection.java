package com.zzivi.sodexo.login.datasource.httpurl.imp;

import com.zzivi.sodexo.login.datasource.httpurl.LoginHttpUrl;
import com.zzivi.sodexo.login.datasource.httpurl.model.HttpUrlResultModel;
import com.zzivi.sodexo.login.datasource.httpurl.model.LoginRequestUrlModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by daniel on 27/09/14.
 *
 * http://www.mkyong.com/java/how-to-automate-login-a-website-java-example/
 */
public class LoginHttpUrlConnection implements LoginHttpUrl {

    private List<String> cookies;
    private HttpURLConnection conn;

    private final String USER_AGENT = "Mozilla/5.0";
    private final String HOME_URL = "http://www.mysodexo.es";
    private final String LOGIN_URL = "http://www.mysodexo.es/login";
    private final String CARDS_BALANCE_URL = "http://www.mysodexo.es/includes/modulo-saldos-tarjeta.php";


    public List<String> getCookies() {
        return cookies;
    }

    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }


    @Inject
    public LoginHttpUrlConnection() {
    }


    @Override
    public HttpUrlResultModel obtainCookies(LoginRequestUrlModel loginRequestUrlModel) throws IOException {
        // make sure cookies is turn on
        CookieHandler.setDefault(new CookieManager());
        // 1. Send a "GET" request, so that you can extract the form's data.
        String page = getPageContent(HOME_URL);
        String postParams = getFormParams(page,
                loginRequestUrlModel.getUsername(), loginRequestUrlModel.getPassword());
        // 2. Construct above post's content and then send a POST request for
        // authentication
        sendPost(LOGIN_URL, postParams);
        HttpUrlResultModel httpUrlResultModel = new HttpUrlResultModel();
        httpUrlResultModel.setCookies(cookies);
        return httpUrlResultModel;
        //HttpUrlResultModel

        // 3. success then go to cards balance.
        //String result = getPageContent(CARDS_BALANCE_URL);
        //System.out.println(result);
    }

    private void sendPost(String url, String postParams) throws IOException {

        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();

        // Acts like a browser
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Host", "www.mysodexo.es");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        for (String cookie : this.cookies) {
            conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
        }
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Accept-Encoding", "gzip-deflate");
        conn.setRequestProperty("Referer", "http://www.mysodexo.es/");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));

        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Send post request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + postParams);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //System.out.println(response.toString());

    }

    private String getPageContent(String url) throws IOException {

        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();

        // default is GET
        conn.setRequestMethod("GET");

        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Host", "www.mysodexo.es");
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Accept-Encoding", "gzip-deflate");
        if (cookies != null) {
            for (String cookie : this.cookies) {
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

        // Get the response cookies
        setCookies(conn.getHeaderFields().get("Set-Cookie"));

        return response.toString();

    }

    public String getFormParams(String html, String username, String password)
            throws UnsupportedEncodingException {

        System.out.println("Extracting form's data...");

        Document doc = Jsoup.parse(html);

        // Google form id
        Element loginform = doc.getElementById("formLogin");
        Elements inputElements = loginform.getElementsByTag("input");
        List<String> paramList = new ArrayList<String>();
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if (key.equals("usr")) {
                value = username;
            }
            else if (key.equals("pwd-login")) {
                value = password;
            }
            paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
        }

        // build parameters list
        StringBuilder result = new StringBuilder();
        for (String param : paramList) {
            if (result.length() == 0) {
                result.append(param);
            } else {
                result.append("&" + param);
            }
        }
        return result.toString();
    }
}
