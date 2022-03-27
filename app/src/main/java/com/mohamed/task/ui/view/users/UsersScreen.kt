package com.mohamed.task.ui.view.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DefaultItemAnimator
import com.mohamed.task.R
import com.mohamed.task.databinding.ActivityMainBinding
import com.mohamed.task.domain.entities.UserEntity
import com.mohamed.task.ui.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UsersScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var usersAdapter: UsersAdapter
    private val usersViewModel: UsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersAdapter = UsersAdapter()

        setUpUi()

        usersViewModel.geUsers()
        usersViewModel.users.observe(this){

            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        binding.progressCircular.visibility = View.GONE
                        binding.swipeContainer.isRefreshing = false
                        usersAdapter.clear()
                        usersAdapter.submitList(resource.data)
                    }
                    Status.ERROR -> {
                        binding.progressCircular.visibility = View.GONE
                        binding.errorMessage.text = resource.message
                    }
                }
            }

        }
    }

    private fun setUpUi() {
        with(binding.usersRecyclerViewList) {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = usersAdapter
        }

        binding.swipeContainer.setOnRefreshListener {
            usersViewModel.geUsers()
        }

        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)

        usersAdapter.onItemClickListener = object :
            UsersAdapter.OnItemClickListener {
            override fun setOnItemClickListener(userEntity: UserEntity) {
                val intent = Intent(this@UsersScreen, UserDetailsScreen::class.java)
                intent.putExtra("user", userEntity)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.light -> {
                applyTheme("light")
            }
            R.id.dark -> {
                applyTheme("dark")
            }
            R.id.battery -> {
                applyTheme("battery")
            }
            R.id.system -> {
                applyTheme("default")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun applyTheme(theme: String) {
        when (theme) {
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "battery" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            "default" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        }
    }

}