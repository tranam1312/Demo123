package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  SeverApi {
//    https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=4d66484ce7c4edd441f078c247071aad&per_page=&page=&format=json&nojsoncallback=1&auth_token=72157716618410316-874ba66307f187e7&api_sig=d5c6de389bb3c9b29213cd42928c54b0
    @GET("services/rest/")
 Call<Response> list(@Query("method") String method,
                     @Query("api_key")String api_key,
                     @Query("lat") String lat,
                     @Query("lon")  String lon,
                     @Query("format") String format,
                     @Query("nojsoncallback") String nojsoncallback);
}
//https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=fba361dc711479fe4ef110416489cf6a&format=json&nojsoncallback=1&auth_token=72157716648440062-5c8d7c4a17c9f563&api_sig=14fe3d2e2bc8193585917fa9fc2c73a5