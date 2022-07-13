package life.league.challenge.kotlin.presentation.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.databinding.ActivityMainBinding
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.model.User
import life.league.challenge.kotlin.presentation.PostAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var listAdapter: PostAdapter
    private lateinit var posts: List<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    updateUI(it)
                }
            }
        }


        lifecycleScope.launch {
            viewModel.onLogin("", "")
            delay(2000)
            viewModel.getUsers()
            viewModel.getPosts()
        }


    }

    private fun updateUI(state: MainViewModel.UiState) {
        binding.progressBar.visibility = if (state.loading) View.VISIBLE else View.GONE
        /*state.account?.let {
            Toast.makeText(this, it.apiKey, Toast.LENGTH_SHORT).show()
        }*/
        if (!state.userPost.isNullOrEmpty()) {
            posts = state.userPost
            listAdapter = PostAdapter(posts)
        }
        state.users?.let { setupRecyclerView(it) }
    }

    private fun setupRecyclerView(users: List<User>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
            listAdapter.submitList(users)
        }
    }


}
