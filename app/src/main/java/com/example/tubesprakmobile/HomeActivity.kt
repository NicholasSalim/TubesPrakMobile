// HomeActivity.kt
package com.example.tubesprakmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tubesprakmobile.MainActivity
import com.example.tubesprakmobile.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupListener()
    }

    private fun setupListener() {
        val buttonGoToMainActivity: Button = findViewById(R.id.start)

        buttonGoToMainActivity.setOnClickListener {
            // Create an Intent to start MainActivity
            val intent = Intent(this@HomeActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
