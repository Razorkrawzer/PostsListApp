package life.league.challenge.kotlin.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import life.league.challenge.kotlin.domain.usecases.LoginUseCase
import life.league.challenge.kotlin.domain.usecases.PostsUseCase
import life.league.challenge.kotlin.domain.usecases.UsersUseCase
import life.league.challenge.kotlin.domain.model.Account
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.model.User
import life.league.challenge.kotlin.util.Resource
import life.league.challenge.kotlin.util.SharedPrefs
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sharedPrefs: SharedPrefs,
    private val usersUseCase: UsersUseCase,
    private val postsUseCase: PostsUseCase
) : ViewModel() {

    data class UiState(
        val loading: Boolean = false,
        val error: String = "",
        val account: Account? = null,
        val users: List<User>? = null,
        val userPost: List<Post>? = null
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()


    fun onLogin(username: String, password: String) {

        loginUseCase(username, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UiState(account = result.data)
                    result.data?.apiKey?.let { sharedPrefs.saveApiKey(it) }
                }
                is Resource.Error -> {
                    _state.value = UiState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = UiState(loading = true)
                }

            }
        }.launchIn(viewModelScope)

    }

    fun getUsers() {

        usersUseCase().onEach { result ->
            when(result){
                is Resource.Error -> {
                    _state.value = UiState(error = result.message ?: "")
                }
                is Resource.Loading -> {
                    _state.value = UiState(loading = true)
                }
                is Resource.Success -> {
                    _state.value = UiState(users = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getPosts(){

        postsUseCase().onEach { result ->
            when(result){
                is Resource.Error -> {
                    _state.value = UiState(error = result.message ?: "")
                }
                is Resource.Loading -> {
                    _state.value = UiState(loading = true)
                }
                is Resource.Success -> {
                    _state.value = UiState(userPost = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}