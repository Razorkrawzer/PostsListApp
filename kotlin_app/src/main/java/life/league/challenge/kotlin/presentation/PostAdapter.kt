package life.league.challenge.kotlin.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import life.league.challenge.kotlin.databinding.ItemPostBinding
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.model.User

class PostAdapter(private var postItem: List<Post>) : ListAdapter<User, PostAdapter.ViewHolder>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.bindPost(postItem[position])
    }

    class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User){
            binding.username.text = item.username
            Glide.with(binding.avatarImg)
                .load(item.avatar)
                .into(binding.avatarImg)
        }

        fun bindPost(item: Post) {
            binding.title.text = item.title
            binding.description.text = item.body
        }

    }

}