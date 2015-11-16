package br.com.celularegistrado.appcelularregistrado.WS;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by renanjunior on 06/03/15.
 */
public class RestClient {

    private static final String BASE_URL = "http://pet.brittus.com";

    private static AppService appService;

    public static AppService getInstance(){

     if(appService == null){

         Gson gson = new GsonBuilder().create();

         RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(BASE_URL).setConverter(new GsonConverter(gson)).build();

         appService = restAdapter.create(AppService.class);


     }

        return appService;

    }


}
