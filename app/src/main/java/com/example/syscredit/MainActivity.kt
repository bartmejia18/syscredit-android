package com.example.syscredit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.syscredit.core.extensions.hide
import com.example.syscredit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with (binding) {
            navController = findNavController(R.id.nav_host_fragment)
            navController.apply {
                addOnDestinationChangedListener { _, destination, _ ->
                    when (destination.id) {
                        R.id.splash_fragment -> {
                            appBarLayout.hide()
                        }
                    }
                }
            }
            appBarConfiguration = AppBarConfiguration(setOf(R.id.main_fragment), drawerLayout)
            setSupportActionBar(toolbar)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}