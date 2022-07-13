package life.league.challenge.kotlin.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import life.league.challenge.kotlin.data.model.toDomainUser
import life.league.challenge.kotlin.domain.model.User
import life.league.challenge.kotlin.domain.repository.UsersRepository
import life.league.challenge.kotlin.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UsersUseCase @Inject constructor(val repository: UsersRepository) {

    operator fun invoke(): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading<List<User>>())
            val users = repository.getUsers().map { it.toDomainUser() }
            emit(Resource.Success(users))
        }catch (e: HttpException){
            emit(Resource.Error<List<User>>(e.localizedMessage ?: "Unexpected error"))
        }catch (e: IOException){
            emit(Resource.Error<List<User>>("Couldn't reach server. Check your internet connection."))
        }
    }
}