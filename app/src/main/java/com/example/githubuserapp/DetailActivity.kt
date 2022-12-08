package com.example.githubuserapp
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuserapp.data_model.GithubUserData
import com.example.githubuserapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra data"
    }

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title="Detail User"

        val getData = intent.getParcelableExtra<GithubUserData>(EXTRA_DATA) as GithubUserData
        binding.apply {
            avatar.setImageResource(getData.avatar)
            tvNameDetail.text = getData.name
            tvUsernameDetail.text = getData.username
            tvCompanyDetail.text = getData.company
            tvLocationDetail.text = getData.location
            tvRepositoryDetail.text = getData.repositori
            tvFollowersDetail.text = getData.follower
            tvFollowingDetail.text = getData.following
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar_detail_activity,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val shareName = binding.tvNameDetail.text.toString()
        val shareUsername = binding.tvUsernameDetail.text.toString()
        val shareCompany = binding.tvCompanyDetail.text.toString()
        val shareLocation = binding.tvLocationDetail.text.toString()
        val shareRepo = binding.tvRepositoryDetail.text.toString()
        val shareFollowers = binding.tvFollowersDetail.text.toString()
        val shareFollowings = binding.tvFollowingDetail.text.toString()
        val shareAllData = """
            Detail User Github dengan Username $shareUsername :
            Nama : $shareName
            Username : $shareUsername
            Company : $shareCompany
            Lokasi : $shareLocation
            Repositori : $shareRepo
            Follower : $shareFollowers
            Following : $shareFollowings
        """.trimIndent()

        val send : Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,shareAllData)
            type = "text/plain"
        }

        val share = Intent.createChooser(send,null)
        when(item.itemId){
            R.id.share ->
            startActivity(share)
        }
        return true
    }
}


