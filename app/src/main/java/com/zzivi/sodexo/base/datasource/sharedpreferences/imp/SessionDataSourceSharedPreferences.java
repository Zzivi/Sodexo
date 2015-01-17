package com.zzivi.sodexo.base.datasource.sharedpreferences.imp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.zzivi.sodexo.base.daggerutils.ForApplication;
import com.zzivi.sodexo.base.datasource.sharedpreferences.SessionDataSource;
import com.zzivi.sodexo.base.crypto.AesCbcWithIntegrity;
import com.zzivi.sodexo.login.datasource.api.model.CookiesResultModel;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.inject.Inject;

/**
 * Created by daniel on 28/09/14.
 */
public class SessionDataSourceSharedPreferences implements SessionDataSource {
    private static final String AUTHORIZE_FILE = "AUTHORIZE";
    private static final String PASSWORD = "PulpiRatit08";

    public final Context context;

    @Inject
    public SessionDataSourceSharedPreferences(@ForApplication Context context){
        this.context = context;
    }

    @Override
    public void storeLoginCookie(CookiesResultModel cookiesResultModel) {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        String cookie = cookiesResultModel.getCookie();
        if (cookie != null) {
             editor.putString("cookie", cookie);
        }
        editor.commit();
    }

    @Override
    public void deleteLoginCookie() {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("cookie");
        editor.commit();
    }


    @Override
    public CookiesResultModel obtainLoginCookie() {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);

        CookiesResultModel cookiesResultModel = new CookiesResultModel();
        cookiesResultModel.setCookie(settings.getString("cookie", "not found"));
        return cookiesResultModel;
    }

    @Override
    public void storeCredentials(LoginCredentials loginCredentials) {
        Crashlytics.setUserIdentifier(loginCredentials.getUsername());
        String salt = "";
        AesCbcWithIntegrity.CipherTextIvMac userEncrypted = null;
        AesCbcWithIntegrity.CipherTextIvMac passEncrypted = null;
        try {
            //encrypt password: more info in https://github.com/tozny/java-aes-crypto
            salt = AesCbcWithIntegrity.saltString(AesCbcWithIntegrity.generateSalt());
            AesCbcWithIntegrity.SecretKeys key = AesCbcWithIntegrity.generateKeyFromPassword(PASSWORD, salt);
            userEncrypted = AesCbcWithIntegrity.encrypt(loginCredentials.getUsername(), key);
            passEncrypted = AesCbcWithIntegrity.encrypt(loginCredentials.getPassword(), key);
        } catch (GeneralSecurityException e) {
            Crashlytics.log(Log.ERROR, "Zzivi", "GeneralSecurityException Encoding credentials");
            Crashlytics.logException(e);
            Log.e("Zzivi", "GeneralSecurityException", e);
        } catch (UnsupportedEncodingException e) {
            Crashlytics.log(Log.ERROR, "Zzivi", "UnsupportedEncodingException Encoding credentials");
            Crashlytics.logException(e);
            Log.e("Zzivi", "UnsupportedEncodingException", e);
        }

        //store in sharedPreferences
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("salt", salt);
        editor.putString("username", userEncrypted.toString());
        editor.putString("password", passEncrypted.toString());
        editor.putBoolean("storecredentials", loginCredentials.isStoreCredentials());
        editor.commit();
    }

    @Override
    public LoginCredentials obtainCredentials()  {

        LoginCredentials loginCredentials = new LoginCredentials();
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);
        String userEncrypted = settings.getString("username", "");
        String passEncrypted = settings.getString("password", "");
        loginCredentials.setStoreCredentials(settings.getBoolean("storecredentials", false));

        //decrypt password: more info in https://github.com/tozny/java-aes-crypto
        String userDecrypted = "";
        String passDecrypted = "";
        try {
            if (!"".equals(userEncrypted) && !"".equals(passEncrypted)) {
                String salt = settings.getString("salt", "");
                if (!"".equals(salt)) {
                    AesCbcWithIntegrity.SecretKeys key = AesCbcWithIntegrity.generateKeyFromPassword(PASSWORD, salt);
                    AesCbcWithIntegrity.CipherTextIvMac civUserEncrypted = new AesCbcWithIntegrity.CipherTextIvMac(userEncrypted);
                    AesCbcWithIntegrity.CipherTextIvMac civPassEncrypted = new AesCbcWithIntegrity.CipherTextIvMac(passEncrypted);
                    userDecrypted = AesCbcWithIntegrity.decryptString(civUserEncrypted, key);
                    passDecrypted = AesCbcWithIntegrity.decryptString(civPassEncrypted, key);
                }
            }
        } catch (GeneralSecurityException e) {
            Crashlytics.log(Log.ERROR, "Zzivi", "GeneralSecurityException Decoding credentials");
            Crashlytics.logException(e);
            Log.e("Zzivi", "GeneralSecurityException", e);
        } catch (UnsupportedEncodingException e) {
            Crashlytics.log(Log.ERROR, "Zzivi", "UnsupportedEncodingException Decoding credentials");
            Crashlytics.logException(e);
            Log.e("Zzivi", "UnsupportedEncodingException", e);
        }

        loginCredentials.setUsername(userDecrypted);
        loginCredentials.setPassword(passDecrypted);

        return loginCredentials;
    }

    @Override
    public void removeCredentials() {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("username");
        editor.remove("password");
        editor.putBoolean("storecredentials", false);
        editor.commit();
    }

    @Override
    public boolean isLoggedIn() {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);
        return settings.getBoolean("isLoggedIn", false);
    }

    @Override
    public void setLoggedIn(boolean isLoggedIn) {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.commit();
    }
}
