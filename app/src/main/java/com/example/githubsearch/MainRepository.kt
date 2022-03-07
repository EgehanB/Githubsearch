package com.example.githubsearch

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllUsers(search: String?) = retrofitService.getAllUsers(search)
    fun getAllRepos(search: String?) = retrofitService.getAllRepos(search)
}