package com.example.githubsearch


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.databinding.ItemHeaderBinding
import com.example.githubsearch.databinding.ItemRepoBinding
import com.example.githubsearch.databinding.ItemUserBinding


const val VIEW_TYPE_USER = 0
const val VIEW_TYPE_REPO = 1
const val VIEW_TYPE_HEADER = 2

class MainAdapter(
    private val listener: ClickListener? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var users = mutableListOf<GithubUser>()

    var repos = mutableListOf<GithubRepo>()

    fun setUserList(userResponses: List<GithubUser>) {
        users = userResponses.toMutableList()
    }

    fun setRepoList(repoResponses: List<GithubRepo>) {
        repos = repoResponses.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_USER) {
            val binding = ItemUserBinding.inflate(inflater, parent, false)
            return UserViewHolder(binding)
        }
        if (viewType == VIEW_TYPE_HEADER) {
            val binding = ItemHeaderBinding.inflate(inflater, parent, false)
            return HeaderViewHolder(binding)
        }
        if (viewType == VIEW_TYPE_REPO) {
            val binding = ItemRepoBinding.inflate(inflater, parent, false)
            return RepoViewHolder(binding)
        }
        throw IllegalStateException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is UserViewHolder) {
            val user = users[position - 1]
            holder.binding.login.text = user.login
            holder.itemView.setOnClickListener {
                listener?.onUserClicked(user)
                Log.d("tag", "user")
            }

        } else if (holder is RepoViewHolder) {
            val repo = repos[position - 2 - users.size]
            holder.binding.name.text = repo.name
            holder.itemView.setOnClickListener {
                listener?.onRepoClicked(repo)
                Log.d("tag", "repo")
            }
        } else if (holder is HeaderViewHolder) {
            if (position == 0) {
                holder.binding.header.text = "USER"
            } else {
                holder.binding.header.text = "REPO"
            }
        }
    }

    override fun getItemCount() = 2 + users.size + repos.size

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> VIEW_TYPE_HEADER
            position < users.size + 1 -> VIEW_TYPE_USER
            position == users.size + 1 -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_REPO
        }

    }

    class UserViewHolder(
        val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root)


    class RepoViewHolder(
        val binding: ItemRepoBinding
    ) : RecyclerView.ViewHolder(binding.root)


    class HeaderViewHolder(
        val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root)


}
