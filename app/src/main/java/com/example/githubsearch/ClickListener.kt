package com.example.githubsearch


interface ClickListener {
    fun onUserClicked(user: GithubUser)
    fun onRepoClicked(repo: GithubRepo)
}