package com.example.githubsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubsearch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding


    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter{position ->onListItemClick(position)}

    private fun onListItemClick(position: Int){

        val detailfragment = DetailsFragment()
      supportFragmentManager.beginTransaction().replace(R.id.main, detailfragment).addToBackStack(null).commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        binding.recyclerview.adapter = adapter

        viewModel.UserList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setUserList(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.RepoList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
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

}