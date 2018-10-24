package com.example.nazariy.geekapps.presentation.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.nazariy.geekapps.R
import com.example.nazariy.geekapps.presentation.view.fragments.AudiobooksFragment
import com.example.nazariy.geekapps.presentation.view.fragments.ItunesFragment
import com.example.nazariy.geekapps.presentation.view.fragments.MoviesFragment
import com.example.nazariy.geekapps.presentation.view.fragments.PodcastsFragment

class MainActivity : AppCompatActivity(), ItunesFragment.OnFragmentAppearedListener {

    private var isAlreadySelected: Boolean = false

    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigationView()
        showAudiobooksFragment()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            supportFragmentManager.popBackStack()
    }

    private fun initNavigationView() {
        navigationView = findViewById(R.id.itunes_list_category)
        navigationView.setOnNavigationItemSelectedListener { item ->
            if (isAlreadySelected) {
                isAlreadySelected = false
            }
            else {
                when (item.itemId) {
                    R.id.action_audiobooks -> {
                        showAudiobooksFragment()
                    }
                    R.id.action_movies -> {
                        showMoviesFragment()
                    }
                    R.id.action_podcasts -> {
                        showPodcastsFragment()
                    }
                }
            }
            true
        }
    }

    private fun showAudiobooksFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.itunes_container, AudiobooksFragment())
                .addToBackStack(AudiobooksFragment::class.java.simpleName)
                .commit()
    }

    private fun showPodcastsFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.itunes_container, PodcastsFragment())
                .addToBackStack(PodcastsFragment::class.java.simpleName)
                .commit()
    }

    private fun showMoviesFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.itunes_container, MoviesFragment())
                .addToBackStack(MoviesFragment::class.java.simpleName)
                .commit()
    }

    override fun changeBottomNavItem(tag: String) {
        isAlreadySelected = true
        when (tag) {
            AudiobooksFragment::class.java.simpleName -> navigationView.selectedItemId = R.id.action_audiobooks
            MoviesFragment::class.java.simpleName -> navigationView.selectedItemId = R.id.action_movies
            else -> navigationView.selectedItemId = R.id.action_podcasts
        }
    }
}
