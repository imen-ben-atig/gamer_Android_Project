package com.example.gamer


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import android.util.Log
import android.widget.Button
import android.widget.EditText

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

        val primaryColor = ContextCompat.getColor(applicationContext, R.color.colorPrimary)
        val paragraphTextView = findViewById<TextView>(R.id.terms)
        val paragraphText = paragraphTextView.text.toString()
        val wordsToUnderline = listOf("our Terms and Conditions ", " Privacy Policy")
        val colorSpan = ForegroundColorSpan(primaryColor)
        val spannableStringBuilder = SpannableStringBuilder(paragraphText)

        for (word in wordsToUnderline) {
            val start = paragraphText.indexOf(word)
            val end = start + word.length

            if (start >= 0) {
                spannableStringBuilder.setSpan(UnderlineSpan(), start, end, 0)
                spannableStringBuilder.setSpan(colorSpan, start, end, 0)
                spannableStringBuilder.setSpan(ForegroundColorSpan(primaryColor), start, end, 0)
            }
        }

        paragraphTextView.text = spannableStringBuilder
        val backArrowButton = findViewById<ImageView>(R.id.backArrowsignup)

        backArrowButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val submitButton = findViewById<TextView>(R.id.submitButton)

        submitButton.setOnClickListener {
            val nameEditText = findViewById<EditText>(R.id.name)
            var enteredText = nameEditText.text.toString()
            Log.d("Tag", "nom : $enteredText")

            val emailEditText = findViewById<EditText>(R.id.eMail)
            var enteredText1 = emailEditText.text.toString()
            Log.d("Tag", "adress : $enteredText1")

            val passwordEditText = findViewById<EditText>(R.id.password)
            var enteredText2 = passwordEditText.text.toString()
            Log.d("Tag", "password : $enteredText2")

            val confirmpwdEditText = findViewById<EditText>(R.id.confirmpassword)
            var enteredText3 = confirmpwdEditText.text.toString()
            Log.d("Tag", "confirm password : $enteredText3")

            println(enteredText)
            println(enteredText1)
            println(enteredText2)
            println(enteredText3)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }




    }

}