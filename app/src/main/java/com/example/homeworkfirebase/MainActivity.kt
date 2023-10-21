package com.example.homeworkfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException

class MainActivity : AppCompatActivity(), OnAutentificationLaunch {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun launch(intent: Intent) {
        startActivityForResult(intent, 1)
    }

    override fun showListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, EmployeeListFragment())
            .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            showListFragment()
            try {
                val result = task.getResult(ApiException::class.java)
            } catch (e:ApiException) {
                Toast.makeText(this, "Error ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

interface OnAutentificationLaunch {
    fun launch(intent: Intent)
    fun showListFragment()
}
