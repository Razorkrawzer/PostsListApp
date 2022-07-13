package life.league.challenge.kotlin.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import life.league.challenge.kotlin.data.model.AccountDTO
import life.league.challenge.kotlin.domain.repository.UsersRepository
import life.league.challenge.kotlin.domain.usecases.LoginUseCase
import org.junit.Before
import org.junit.Test

class LoginUseCaseTest{
    @RelaxedMockK
    private lateinit var repository: UsersRepository

    lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        loginUseCase = LoginUseCase(repository)
    }

    @Test
    fun `when send empty parameters in login then return apiKey`() = runBlocking {
        //Given
        coEvery { repository.login("", "") } returns AccountDTO("EADASDAD")
        //When
        val response = loginUseCase("", "")
        //Then
        assert(response != null)
    }
}