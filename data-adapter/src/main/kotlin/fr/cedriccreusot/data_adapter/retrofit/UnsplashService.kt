package fr.cedriccreusot.data_adapter.retrofit

import fr.cedriccreusot.data_adapter.retrofit.model.Photo
import fr.cedriccreusot.data_adapter.retrofit.model.Statistics
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashService {

    @GET("/photos")
    fun photos(@Header("Authorization") authorization: String, @Query("per_page") perPage: Int = 20): Call<List<Photo>>

    @GET("/users/{username}/photos")
    fun userPhotos(@Header("Authorization") authorization: String, @Path("username") username: String): Call<List<Photo>>

    @GET("/photos/{id}/statistics")
    fun statistics(@Header("Authorization") authorization: String, @Path("id") photoId: String): Call<Statistics>

    companion object {
        fun createService(baseUrl: String): UnsplashService =
            Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UnsplashService::class.java)
    }
}