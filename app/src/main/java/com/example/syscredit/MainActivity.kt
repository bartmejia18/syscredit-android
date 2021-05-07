package com.example.syscredit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.syscredit.core.extensions.hide
import com.example.syscredit.core.extensions.hideKeyboard
import com.example.syscredit.core.extensions.show
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
                    hideKeyboard()
                    when (destination.id) {
                        R.id.splash_fragment, R.id.login_fragment -> {
                            appBarLayout.hide()
                        }
                        R.id.main_fragment -> {
                            appBarLayout.show()
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