package com.example.syscredit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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

        with (binding) {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController.apply {
                addOnDestinationChangedListener { _, destination, _ ->
                    hideKeyboard()
                    when (destination.id) {
                        R.id.splash_fragment, R.id.login_fragment -> {
                            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                            appBarLayout.hide()
                        }
                        R.id.main_fragment -> {
                            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                            appBarLayout.show()
                            toolbar.setNavigationOnClickListener {
                                drawerLayout.openDrawer(GravityCompat.START)
                            }
                        }
                        R.id.detail_customer, R.id.history_payments_fragment -> {
                            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                            appBarLayout.show()
                            toolbar.setNavigationOnClickListener {
                                navController.navigateUp()
                            }

                        }
                    }
                }
            }

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.splash_fragment,
                    R.id.login_fragment,
                    R.id.main_fragment
                ), drawerLayout
            )
            setSupportActionBar(toolbar)
            setupActionBarWithNavController(navController, appBarConfiguration)

            navView.setupWithNavController(navController)
            navView.setNavigationItemSelectedListener {
                drawerLayout.closeDrawer(GravityCompat.START)
                when (it.itemId) {
                    R.id.nav_logout -> MaterialAlertDialogBuilder(this@MainActivity).apply {
                        setTitle(R.string.title_logout)
                        setMessage(R.string.message_logout)
                        setPositiveButton(R.string.action_logout) { dialog, _ ->
                            CoroutineScope(Dispatchers.Main + Job()).launch {
                                context.sharedPreferences {
                                    putInt("id", 0)
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

        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp(appBarConfiguration)

    override fun onOptionsItemSelected(item: MenuItem) =
        item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() = when {
        binding.drawerLayout.isDrawerOpen(GravityCompat.START) -> {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        else -> {
            super.onBackPressed()
        }
    }
}