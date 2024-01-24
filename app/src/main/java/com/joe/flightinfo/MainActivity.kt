package com.joe.flightinfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.joe.flightinfo.databinding.ActivityMainBinding
import com.joe.flightinfo.helper.DialogHelper
import com.joe.flightinfo.helper.SharePreferenceHelper
import com.joe.flightinfo.helper.TokenHelper
import com.joe.flightinfo.service.FlightInfoUpdateService

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Currency Calculator", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            DialogHelper().showCurrencyCalculator(
                this,
                "OK",
                "Cancel",
                "test",
                object : DialogHelper.ITwoEventCallback {
                    override fun onCancelClicked() {
                    }

                    override fun onOKClicked() {

                    }

                }).show()

        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        SharePreferenceHelper.setBaseAirport(baseContext, "TPE")
        SharePreferenceHelper.setBaseCurrency(baseContext, "USD")

        val serviceIntent = Intent(this, FlightInfoUpdateService::class.java)
        startService(serviceIntent)
    }

//    override fun onResume() {
//        super.onResume()
//        TokenHelper.checkAccessToken(baseContext)
//        Log.i("FlightInfo", "MainActivity onResume")
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {

    }
}