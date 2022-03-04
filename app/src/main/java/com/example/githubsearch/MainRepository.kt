package com.example.githubsearch

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllUsers(search: String?) = retrofitService.gelAllUsers(search)
    fun getAllRepos(search: String?) = retrofitService.gelAllRepos(search)
}