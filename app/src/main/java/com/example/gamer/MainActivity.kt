package com.example.gamer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent1 = Intent(this@MainActivity, HomeActivity::class.java)
        startActivity(intent1)

    }
}
