package com.example.day3.bean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleServer {
    //https://www.wanandroid.com/project/list/1/json?cid=294

    String URL = "https://www.wanandroid.com/project/list/1/";
    //GET
    @GET("json")
    Call<ResponseBody> getData(@Query("cid") String cid);
}
