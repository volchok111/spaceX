package com.example.spacexserv.ui


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.spacexserv.R
import com.example.spacexserv.databinding.ActivityMainBinding
import com.example.spacexserv.model.ships.Ships


class MainActivity : MvpAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupActionBarWithNavController(findNavController(R.id.mainNavHost))
    }

    override fun onSupportNavigateUp(): Boolean {
        val bind = findNavController(R.id.mainNavHost)
        return super.onSupportNavigateUp() || bind.navigateUp()
    }


    }
