package com.example.gamer

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.gamer.databinding.HomeBinding

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

            binding.btnNews.setOnClickListener {
                changeFragment(NewsFragment(), "News")
            }
            val btnNews = findViewById<Button>(R.id.btnNews)
            val btnEvents = findViewById<Button>(R.id.btnStore)
            val btnProfile = findViewById<Button>(R.id.btnProfile)

            btnNews.setOnClickListener {
                changeFragment(NewsFragment(), "News")
            }

            btnEvents.setOnClickListener {
                changeFragment(StoreFragment(), "Events")
            }

            btnProfile.setOnClickListener {
                changeFragment(ProfileFragment(), "Change")
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



