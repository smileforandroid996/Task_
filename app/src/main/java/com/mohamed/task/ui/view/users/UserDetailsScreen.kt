package com.mohamed.task.ui.view.users

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mohamed.task.R
import com.mohamed.task.databinding.ActivityMainBinding
import com.mohamed.task.databinding.ActivityUserDetailsScreenBinding
import com.mohamed.task.domain.entities.UserEntity
import com.squareup.picasso.Picasso

class UserDetailsScreen : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.extras?.getParcelable<UserEntity>("user")!!

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = user.login
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)


        binding.name.text = user.login
        binding.userName.text = user.login
        binding.link.text = user.htmlUrl

        if (!user.avatarUrl.isNullOrEmpty()){
            Picasso.get().load(user.avatarUrl).into(binding.userImage)
        }

        binding.btnFollow.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(user.htmlUrl)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}