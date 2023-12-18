package com.example.tubesprakmobile

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndpoint {

    @GET("read.php")
    fun data() : Call<NoteModel>

    @FormUrlEncoded
    @POST("create.php")
    fun create(@Field("note")note: String) : Call<SubmitModel>

    @FormUrlEncoded
    @POST("update.php")
    fun update(
        @Field("id_notes")id: String,
        @Field("note")note: String
    ) : Call<SubmitModel>

    @FormUrlEncoded
    @POST("delete.php")
    fun delete(
        @Field("id_notes")id: String,
    ) : Call<SubmitModel>

}