package com.example.syscredit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.syscredit.core.extensions.hide
import com.example.syscredit.core.extensions.hideKeyboard
import com.example.syscredit.core.extensions.show
import com.example.syscredit.data.local.sharedPreferences
import com.example.syscredit.databinding.ActivityMainBinding
import com.example.syscredit.ui.credits.CreditsFragmentDirections
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //with (binding) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController.apply {
            addOnDestinationChangedListener { _, destination, _ ->
                hideKeyboard()
                when (destination.id) {
                    R.id.splash_fragment, R.id.login_fragment -> {
                        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                        binding.appBarLayout.hide()
                    }
                    R.id.main_fragment -> {
                        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                        binding.appBarLayout.show()
                        binding.toolbar.setNavigationOnClickListener {
                            binding.drawerLayout.openDrawer(GravityCompat.START)
                        }
                    }
                    R.id.detail_customer -> {
                        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                        binding.appBarLayout.show()
                        binding.toolbar.setNavigationOnClickListener {
                            navController.navigateUp()
                        }

                    }
                }
            }
        }

        appBarConfiguration = AppBarConfiguration(setOf(R.id.splash_fragment, R.id.login_fragment, R.id.main_fragment), binding.drawerLayout)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navView.setupWithNavController(navController)
        binding.navView.setNavigationItemSelectedListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            when (it.itemId) {
                R.id.nav_logout -> MaterialAlertDialogBuilder(this@MainActivity).apply {
                        setTitle(R.string.title_logout)
                        setMessage(R.string.message_logout)
                        setPositiveButton(R.string.action_logout) { dialog, _ ->
                            CoroutineScope(Dispatchers.Main + Job()).launch {
                                context.sharedPreferences {
                                    putBoolean("logged_in", false)
                                }
                                dialog.dismiss()
                                navController.navigate(CreditsFragmentDirections.actionMainFragmentToSplashFragment())
                            }
                        }
                        setNegativeButton(R.string.action_cancel) { dialog, _ ->
                            dialog.dismiss()
                        }
                    }.create().show()

            }
            true
        }

        //}
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(binding.drawerLayout)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)

    override fun onBackPressed() = when {
        binding.drawerLayout.isDrawerOpen(GravityCompat.START) -> {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        else -> {
            super.onBackPressed()
        }
    }
}