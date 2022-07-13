package life.league.challenge.kotlin.domain.repository

import life.league.challenge.kotlin.data.model.AccountDTO
import life.league.challenge.kotlin.data.model.PostDTO
import life.league.challenge.kotlin.data.model.UserDTO

interface UsersRepository {

    suspend fun login(username: String, password: String): AccountDTO

    suspend fun getUsers(): List<UserDTO>

    suspend fun getPosts(): List<PostDTO>
}