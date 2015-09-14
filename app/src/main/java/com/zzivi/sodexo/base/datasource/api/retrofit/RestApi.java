package com.zzivi.sodexo.base.datasource.api.retrofit;

import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by daniel on 11/10/14.
 */
public interface RestApi {

    @FormUrlEncoded
    @POST("/lib/login.php")
    public Response login(@Field("usr") String username, @Field("pwd-login") String password,
               @Field("pwdaux") String pwdaux, @Field("mantenerSesion") String mantenerSesion);

    @GET("/mis-servicios-tarjetas")
    public Response getCardsBalance();

    @GET("/")
    public Response getHome();

}
