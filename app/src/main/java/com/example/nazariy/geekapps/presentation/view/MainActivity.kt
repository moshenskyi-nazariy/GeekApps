package com.example.nazariy.geekapps.presentation.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.nazariy.geekapps.R

class MainActivity : AppCompatActivity() {
    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigationView()
        showAudiobooksFragment()
    }

    private fun initNavigationView() {
        navigationView = findViewById(R.id.itunes_list_category)
        navigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_audiobooks -> {
                    showAudiobooksFragment()
                }
            }
            true
        }
    }

    private fun showAudiobooksFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.itunes_container, ItunesItemsFragment())
                .commit()
    }

}
