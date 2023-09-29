package com.example.gamer


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class SignUpActivity : AppCompatActivity() {
    private lateinit var switchMode: SwitchCompat
    private var nightMode: Boolean = false
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

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
}