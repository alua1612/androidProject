package com.example.aluakosamanova.newsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by aluakosamanova on 10.10.17.
 */

public interface ApiService {
    @GET("posts")
    Call<List<Contact>> getPosts();


    @GET("posts")
    Call<List<Contact>> getPostsById(@retrofit2.http.Query("userId") int userId);


//    @GET("posts/{id}/comments")
//    Call<List<Comment>> getCommentsByPost(@Path("id") int postId);


    @POST("posts")
    Call<Contact> createPost();


    @FormUrlEncoded
    @POST("posts")
    Call<Contact> createPostWithParams(@Field("title") String title,
                                    @Field("body") String body,
                                    @Field("userId") int userId);



    @FormUrlEncoded
    @PUT("posts/{postId}")
    Call<Contact> updatePost(@Field("title") String title, @Path("postId") int postId);


    @DELETE("posts/{postId}")
    Call<Contact> deletePost(@Path("postId") int postId);



    @Headers({"Cache-Control: max-age=640000",
            "User-Agent: Retrofit-Sample-App"})
    @POST("posts")
    Call<Contact> createPostWithJson(@Body Contact post, @Header("Authorization") String auth);
}
