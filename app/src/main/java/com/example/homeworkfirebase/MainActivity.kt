package com.example.homeworkfirebase

        import android.content.Intent
                import androidx.appcompat.app.AppCompatActivity
                import android.os.Bundle
                import android.widget.Toast
                import com.google.android.gms.auth.api.signin.GoogleSignIn
                import com.google.android.gms.common.api.ApiException
                import com.google.android.gms.maps.SupportMapFragment
                import com.google.firebase.auth.FirebaseAuth
                import com.google.firebase.auth.GoogleAuthProvider

        class MainActivity : AppCompatActivity(), OnAutentificationLaunch {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
    }

    override fun launch(intent: Intent) {
        startActivityForResult(intent, 1)
    }

    override fun showMapFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MapFragment())
            .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            showMapFragment()
            try {
                val result = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(result.idToken, null)
                val firebaseAuth = FirebaseAuth.getInstance()
                firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showMapFragment()
                        } else {
                            Toast.makeText(this, "Firebase Error",  Toast.LENGTH_SHORT).show()
                        }
                    }
            } catch (e:ApiException) {
                Toast.makeText(this, "Error ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

interface OnAutentificationLaunch {
    fun launch(intent: Intent)
    fun showMapFragment()
}
