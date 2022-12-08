package com.example.githubuserapp

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.adapter.GithubUserAdapter
import com.example.githubuserapp.data_model.GithubUserData
import com.example.githubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<GithubUserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Github User's"

        binding.rv.setHasFixedSize(true)
        list.addAll(listUser)
        showRecylerView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var intent = Intent(this@MainActivity,ProfilActivity::class.java)
        startActivity(intent)
        return true
    }

    private val listUser:ArrayList<GithubUserData>

    get() {

        val dataName = resources.getStringArray(R.array.name)
        val dataUsername = resources.getStringArray(R.array.username)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataRepo = resources.getStringArray(R.array.repository)
        val dataCompany = resources.getStringArray(R.array.company)
        val dataFollowers = resources.getStringArray(R.array.followers)
        val dataFollowing = resources.getStringArray(R.array.following)
        val dataAvatar = resources.obtainTypedArray(R.array.avatar)

        val listGithub = ArrayList<GithubUserData>()

        for (i in dataName.indices){
            val git = GithubUserData(
                dataName[i],
                dataUsername[i],
                dataLocation[i],
                dataRepo[i],
                dataCompany[i],
                dataFollowers[i],
                dataFollowing[i],
                dataAvatar.getResourceId(i,1)
            )
            listGithub.add(git)
        }
        return listGithub
    }
    private fun showRecylerView(){
        val GithubAdapter = GithubUserAdapter(list)
        binding.rv.adapter = GithubAdapter

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rv.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rv.layoutManager = LinearLayoutManager(this)
        }

        GithubAdapter.setOnItemClickCallback(object :GithubUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: GithubUserData) {
                val sendToDetail = Intent(this@MainActivity,DetailActivity::class.java)
                sendToDetail.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(sendToDetail)
            }
        })
    }
}