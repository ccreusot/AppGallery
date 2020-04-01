package fr.cedriccreusot.data_adapter.mocks

import fr.cedriccreusot.data_adapter.retrofit.UnsplashService
import fr.cedriccreusot.data_adapter.retrofit.model.Photo
import fr.cedriccreusot.data_adapter.retrofit.model.Statistics
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.Calls
import retrofit2.mock.MockRetrofit
import java.io.IOException

object UnsplashServiceMocks {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com")
        .build()
    val behaviorDelegate: BehaviorDelegate<UnsplashService> =
        MockRetrofit.Builder(retrofit).build().create(UnsplashService::class.java)

    fun createServiceThatFail() =
        object : UnsplashService {
            override fun photos(authorization: String, perPage: Int): Call<List<Photo>> {
                return behaviorDelegate.returning(Calls.failure<IOException>(IOException()))
                    .photos(authorization, perPage)
            }

            override fun userPhotos(authorization: String, username: String): Call<List<Photo>> {
                return behaviorDelegate.returning(Calls.failure<IOException>(IOException()))
                    .userPhotos(authorization, username)
            }

            override fun statistics(authorization: String, photoId: String): Call<Statistics> {
                return behaviorDelegate.returning(Calls.failure<IOException>(IOException()))
                    .statistics(authorization, photoId)
            }
        }

    fun createServiceWithResponses(
        photos: Any? = null,
        userPhotos: Any? = null,
        statistics: Any? = null
    ) =
        object : UnsplashService {
            override fun photos(authorization: String, perPage: Int): Call<List<Photo>> {
                return behaviorDelegate.returningResponse(photos).photos(authorization, perPage)
            }

            override fun userPhotos(authorization: String, username: String): Call<List<Photo>> {
                return behaviorDelegate.returningResponse(userPhotos)
                    .userPhotos(authorization, username)
            }

            override fun statistics(authorization: String, photoId: String): Call<Statistics> {
                return behaviorDelegate.returningResponse(statistics)
                    .statistics(authorization, photoId)
            }
        }
}
