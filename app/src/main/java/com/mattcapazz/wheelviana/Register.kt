package com.mattcapazz.wheelviana

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth
val TAG = "Login"



class Register : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        auth = Firebase.auth

        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)
        val nav = findViewById<NavigationView>(R.id.navView)


        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        nav.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.gMap -> {
                    val gmapAct = Intent(this, Maps::class.java).apply {
                    }
                    startActivity(gmapAct)

                }

                R.id.schedule -> Toast.makeText(
                    applicationContext,
                    "Clicked Item 2",
                    Toast.LENGTH_SHORT
                ).show()

                R.id.issues -> {
                    val loginAct = Intent(this, ActivityReport::class.java).apply {  }
                    startActivity(loginAct)
                }

                R.id.login -> {
                    val loginAct = Intent(this, Register::class.java).apply {  }
                    startActivity(loginAct)
                }

            }
            true

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun createAccount(view: android.view.View) {
        val email = findViewById<EditText>(R.id.email).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
                }
            }
    }

    fun loginAccount(view: android.view.View) {
        startActivity(Intent(this, Login::class.java))
    }
}