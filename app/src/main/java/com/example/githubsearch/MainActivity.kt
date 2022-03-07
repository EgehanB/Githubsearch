package com.example.githubsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubsearch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding


    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter { position -> onListItemClick(position) }


    private fun onListItemClick(position: Int) {


        startActivity(Intent(this, DetailActivity::class.java))
        /*
    val intent = Intent(MainAdapter.UserViewHolder() , DetailActivity::class.java)
         startActivity(intent.putExtra("detail", GithubUser()!!.login))*/
        /*   Intent(this,DetailActivity::class.java).also{
                it.putExtra("detail",  viewModel.UserList.toString())*/
    }
/*
        startActivity(
            Intent(this@MainActivity, DetailActivity::class.java)).
*/

    /*

      .putExtra("detail",(Serializable) )
)
*/
    //    startActivity(Intent(this,DetailActivity::class.java).putExtra("detail", viewModel.UserList))


    /*     val detailfragment = DetailsFragment()
         supportFragmentManager.beginTransaction().replace(R.id.main, detailfragment)
             .addToBackStack(null).commit()
 */


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

}