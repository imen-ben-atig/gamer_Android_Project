package com.example.gamer

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.gamer.databinding.HomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

        private lateinit var binding: HomeBinding
    private lateinit var switchMode: SwitchCompat
    private var nightMode: Boolean = false
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = HomeBinding.inflate(layoutInflater)
            setContentView(binding.root)


          /*  val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.view_bar)
            setSupportActionBar(toolbar)
            val navView: BottomNavigationView = binding.bottomNavigation
*/


            val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
            val cartIcon = findViewById<ImageView>(R.id.shop)

            bottomNavigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item_news -> {
                        goToFragment(NewsFragment())
                        cartIcon.visibility = View.GONE
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.item_store -> {
                        goToFragment(StoreFragment())
                        cartIcon.visibility = View.VISIBLE
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.item_profile -> {
                        goToFragment(ProfileFragment())
                        cartIcon.visibility = View.GONE
                        return@setOnNavigationItemSelectedListener true
                    }
                    else -> false
                }
            }


            switchMode = findViewById(R.id.switchMode)
                sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE)
                nightMode = sharedPreferences.getBoolean("nightMode", false)

                if (nightMode) {
                    switchMode.isChecked = true
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }

                switchMode.setOnClickListener {
                    if (nightMode) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        editor = sharedPreferences.edit()
                        editor.putBoolean("nightMode", false)
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        editor = sharedPreferences.edit()
                        editor.putBoolean("nightMode", true)
                    }
                    editor.apply()
                }
            
            }


    private  fun goToFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
    }
        private fun changeFragment(fragment: Fragment, tag: String) {
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()

            if (tag.isEmpty()) {
                transaction.replace(R.id.fragment_container, fragment).commit()
            } else {
                transaction.replace(R.id.fragment_container, fragment, tag).commit()
                transaction.addToBackStack(tag)
            }

        }


    }



