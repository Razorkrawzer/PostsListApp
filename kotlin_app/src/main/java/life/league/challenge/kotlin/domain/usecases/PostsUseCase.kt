package life.league.challenge.kotlin.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import life.league.challenge.kotlin.data.model.toDomainPost
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.repository.UsersRepository
import life.league.challenge.kotlin.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostsUseCase @Inject constructor(private val repository: UsersRepository) {

    operator fun invoke(): Flow<Resource<List<Post>>> = flow {
        try {
            emit(Resource.Loading<List<Post>>())
            val posts = repository.getPosts().map { it.toDomainPost() }
            emit(Resource.Success(posts))
        }catch (e: HttpException){
            emit(Resource.Error<List<Post>>(e.localizedMessage ?: "Unexpected error"))
        }catch (e: IOException){
            emit(Resource.Error<List<Post>>("Couldn't reach server. Check your internet connection."))
        }
    }
}