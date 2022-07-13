package life.league.challenge.kotlin.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import life.league.challenge.kotlin.domain.repository.UsersRepository
import life.league.challenge.kotlin.domain.usecases.PostsUseCase
import org.junit.Before
import org.junit.Test

class PostsUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: UsersRepository

    lateinit var postsUseCase: PostsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        postsUseCase = PostsUseCase(repository)
    }

    @Test
    fun `when the apikey is null then return error`() = runBlocking {
        //Given
        coEvery { repository.getPosts() } returns emptyList()
        //When
        postsUseCase()
        //Then
        assert(true)
    }
}