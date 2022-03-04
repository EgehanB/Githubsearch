package com.example.githubsearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val UserList = MutableLiveData<List<GithubUser>>()

    val RepoList = MutableLiveData<List<GithubRepo>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllUsers(search: String?) {
        val response = repository.getAllUsers(search)
        response.enqueue(object : Callback<GithubUserResponse> {
            override fun onResponse(
                call: Call<GithubUserResponse>,
                response: Response<GithubUserResponse>
            ) {
                UserList.postValue(response.body()!!.items)
            }

            override fun onFailure(call: Call<GithubUserResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getAllRepos(search: String?) {
        val response = repository.getAllRepos(search)
        response.enqueue(object : Callback<GithubRepoResponse> {
            override fun onResponse(
                call: Call<GithubRepoResponse>,
                response: Response<GithubRepoResponse>
            ) {
                RepoList.postValue(response.body()!!.items)
            }

            override fun onFailure(call: Call<GithubRepoResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}



