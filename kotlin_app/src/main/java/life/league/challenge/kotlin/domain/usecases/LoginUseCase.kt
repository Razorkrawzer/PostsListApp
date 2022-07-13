package life.league.challenge.kotlin.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import life.league.challenge.kotlin.data.model.AccountDTO
import life.league.challenge.kotlin.data.model.toDomainAccount
import life.league.challenge.kotlin.domain.model.Account
import life.league.challenge.kotlin.domain.repository.UsersRepository
import life.league.challenge.kotlin.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: UsersRepository) {

    operator fun invoke(username: String, password: String): Flow<Resource<Account>> = flow {
        try {
            emit(Resource.Loading<Account>())
            val login = repository.login(username, password).toDomainAccount()
            emit(Resource.Success(login))
        }catch (e: HttpException){
            emit(Resource.Error<Account>(e.localizedMessage ?: "Unexpected error"))
        }catch (e: IOException){
            emit(Resource.Error<Account>("Couldn't reach server. Check your internet connection."))
        }
    }
}