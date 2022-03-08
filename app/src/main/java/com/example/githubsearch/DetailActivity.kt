package com.example.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.githubsearch.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userdetail = intent.getSerializableExtra("userdetail") as? GithubUser
        val repodetail = intent.getSerializableExtra("repodetail") as? GithubRepo

        Glide.with(binding.image.context).load(userdetail?.avatarUrl).into(binding.image)
        binding.userdetails.text = userdetail?.login
        binding.repodetails.text = repodetail?.name


    }

}