package com.example.producthunt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.*
import com.example.producthunt.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /*navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        bottom_nav.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this,navController)
*/



        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout)

    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    private fun setupNavigation(){
        val navController=Navigation.findNavController(this,R.id.nav_host_fragment)

        setupActionBarWithNavController(this,navController,drawerLayout)

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true


        }
        setupWithNavController(navigation_view, navController)

    }
}
