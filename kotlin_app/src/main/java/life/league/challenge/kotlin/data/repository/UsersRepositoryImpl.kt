package life.league.challenge.kotlin.data.repository

import life.league.challenge.kotlin.data.api.Api
import life.league.challenge.kotlin.data.model.AccountDTO
import life.league.challenge.kotlin.data.model.PostDTO
import life.league.challenge.kotlin.data.model.UserDTO
import life.league.challenge.kotlin.domain.repository.UsersRepository
import life.league.challenge.kotlin.util.SharedPrefs
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: Api,
    private val sharedPrefs: SharedPrefs
) : UsersRepository {

    override suspend fun login(username: String, password: String): AccountDTO {
        return api.login("")
    }

    override suspend fun getUsers(): List<UserDTO> {
        return api.users(sharedPrefs.getApiKey())
    }

    override suspend fun getPosts(): List<PostDTO> {
        return api.posts(sharedPrefs.getApiKey())
    }
}