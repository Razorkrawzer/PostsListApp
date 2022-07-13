package life.league.challenge.kotlin.data.api

import life.league.challenge.kotlin.data.model.AccountDTO
import life.league.challenge.kotlin.data.model.PostDTO
import life.league.challenge.kotlin.data.model.UserDTO
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Retrofit API interface definition using coroutines. Feel free to change this implementation to
 * suit your chosen architecture pattern and concurrency tools
 */
interface Api {

    @GET("login")
    suspend fun login(@Header("Authorization") credentials: String?): AccountDTO

    @GET("users")
    suspend fun users(@Header("x-access-token") apiKey: String): List<UserDTO>

    @GET("posts")
    suspend fun posts(@Header("x-access-token") apiKey: String): List<PostDTO>

}
