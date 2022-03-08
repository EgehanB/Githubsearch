package com.example.githubsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubsearch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ClickListener {

    lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    val adapter = MainAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )
        binding.recyclerview.adapter = adapter

        viewModel.userList.observe(this, Observer {

            adapter.setUserList(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.repoList.observe(this, Observer {

            adapter.setRepoList(it)
            adapter.notifyDataSetChanged()
        })

        binding.userSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String?): Boolean {
                viewModel.getAllUsers(search)
                viewModel.getAllRepos(search)

                return false
            }

            override fun onQueryTextChange(search: String?): Boolean {

                return false
            }
        })

    }

    override fun onUserClicked(user: GithubUser) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("userdetail", user)
        startActivity(intent)
    }

    override fun onRepoClicked(repo: GithubRepo) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("repodetail", repo)
        startActivity(intent)
    }

}