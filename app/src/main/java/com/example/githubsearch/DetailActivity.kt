package com.example.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProviders
import com.example.githubsearch.databinding.ActivityDetailBinding
import com.example.githubsearch.databinding.ActivityMainBinding


class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding


    lateinit var viewModel: MainViewModel

//    private val retrofitService = RetrofitService.getInstance()

    //   val adapter = MainAdapter { position -> onListItemClick(position) }
    val detailss = intent!!.getSerializableExtra("detail") as GithubUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.details.text = detailss.login


        //    getData()

    }


    private fun getData() {


    }
}