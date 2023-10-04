package com.example.gamer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var switchMode: SwitchCompat
    private var nightMode: Boolean = false
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        val paragraphTextView = findViewById<TextView>(R.id.forgotPassword)
        val paragraphText = paragraphTextView.text.toString()
        val wordsToUnderline = listOf("Forgot ", "Password?")
        val spannableStringBuilder = SpannableStringBuilder(paragraphText)

        for (word in wordsToUnderline) {
            val start = paragraphText.indexOf(word)
            val end = start + word.length

            if (start >= 0) {
                spannableStringBuilder.setSpan(
                    UnderlineSpan(),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(primaryColor),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        paragraphTextView.text = spannableStringBuilder


        paragraphTextView.setOnClickListener {

            val intent = Intent(this@MainActivity, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
        val signUpTextView = findViewById<TextView>(R.id.SignUp)

        signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val facebook: ImageView = findViewById(R.id.facebook)

        facebook.setOnClickListener {
            try {
                val intent = packageManager.getLaunchIntentForPackage("com.facebook.katana")
                if (intent != null) {
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("fb://homepage") // Replace with the Facebook page ID or another URL
                    startActivity(intent)
                } else {
                    // Facebook app is not installed, open Facebook in a web browser
                    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"))
                    startActivity(webIntent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        val gmail: ImageView = findViewById(R.id.gmail)

        gmail.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmail.com/"))
            startActivity(intent)
        }

        val submitButton = findViewById<TextView>(R.id.logIn)

        submitButton.setOnClickListener {
            val emailEditText = findViewById<EditText>(R.id.eMail1)
            var enteredText1 = emailEditText.text.toString()
            Log.d("Tag", "email : $enteredText1")

            val passwordEditText = findViewById<EditText>(R.id.password)
            var enteredText2 = passwordEditText.text.toString()
            Log.d("Tag", "password : $enteredText2")



            println(enteredText1)
            println(enteredText2)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }



    }
    }
