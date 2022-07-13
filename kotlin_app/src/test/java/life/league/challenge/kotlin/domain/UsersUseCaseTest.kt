package life.league.challenge.kotlin.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import life.league.challenge.kotlin.data.model.*
import life.league.challenge.kotlin.domain.model.User
import life.league.challenge.kotlin.domain.repository.UsersRepository
import life.league.challenge.kotlin.domain.usecases.UsersUseCase
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class UsersUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: UsersRepository

    lateinit var usersUseCase: UsersUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        usersUseCase = UsersUseCase(repository)
    }

    @Test
    fun `when called the use case to get a flow of the users`() = runBlocking {
        //Given
        val usersList = listOf(
            UserDTO(
                Address("", Geo("", ""), "", "", ""),
                Avatar("", "", ""),
                Company("", "", ""),
                "Sincere@april.biz", 1, "Leanne Graham", "92998-3874",
                "Bret", "hildegard.org"
            )
        )
        coEvery { repository.getUsers() } returns usersList
        //When
        val response = usersUseCase()

        //Then
        assert(response != usersList)
    }
}