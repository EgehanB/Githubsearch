package com.example.githubsearch

import com.google.gson.annotations.SerializedName

 class GithubUserResponse {
     @SerializedName("total_count"        ) var totalCount        : Int?             = null
     @SerializedName("incomplete_results" ) var incompleteResults : Boolean?         = null
     @SerializedName("items"              ) var items             : ArrayList<GithubUser> = arrayListOf()
}

class GithubRepoResponse {
    @SerializedName("total_count"        ) var totalCount        : Int?             = null
    @SerializedName("incomplete_results" ) var incompleteResults : Boolean?         = null
    @SerializedName("items"              ) var items             : ArrayList<GithubRepo> = arrayListOf()
}
