package com.example.nazariy.geekapps.presentation.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.nazariy.geekapps.R
import com.example.nazariy.geekapps.presentation.view.fragments.*

class MainActivity : AppCompatActivity(), ItunesFragment.OnFragmentAppearedListener {

    private var isAlreadySelected: Boolean = false

    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigationView()
        showFragment(AudiobooksFragment())
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
            } else {
                when (item.itemId) {
                    R.id.action_audiobooks -> {
                        showFragment(AudiobooksFragment())
                    }
                    R.id.action_movies -> {
                        showFragment(MoviesFragment())
                    }
                    R.id.action_podcasts -> {
                        showFragment(PodcastsFragment())
                    }
                    R.id.action_favorites -> {
                        showFragment(FavouritesFragment())
                    }
                }
            }
            true
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                .replace(R.id.itunes_container, fragment)
                .addToBackStack(fragment::class.java.simpleName)
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
