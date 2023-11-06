package com.sunnyweather.android.init

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.user.ActivityCollector

class RegisterActivity : AppCompatActivity() {

    lateinit var loginLayout : RelativeLayout
    lateinit var edit_text_email : EditText
    lateinit var edit_text_password : EditText
    lateinit var button_sign_in : Button
    lateinit var text_view_login : TextView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loginLayout = findViewById(R.id.loginLayout)
        edit_text_email = findViewById(R.id.edit_text_email)
        edit_text_password = findViewById(R.id.edit_text_password)
        button_sign_in = findViewById(R.id.button_sign_in)
        progressBar = findViewById(R.id.progressBar)
        text_view_login = findViewById(R.id.text_view_login)

        button_sign_in.setOnClickListener {
            val email = edit_text_email.text.toString()
            val password = edit_text_password.text.toString()

            if (email.isEmpty()&&password.isEmpty()){
                edit_text_email.error = "Email Required"
                edit_text_email.requestFocus()
                edit_text_password.error = "Password Required"
                edit_text_password.requestFocus()
                return@setOnClickListener
            }
            else if (email.isEmpty()){
                edit_text_email.error = "Email Required"
                edit_text_email.requestFocus()
                return@setOnClickListener
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                edit_text_email.error = "Valid Email Required"
                edit_text_email.requestFocus()
                return@setOnClickListener
            }
            else if (password.isEmpty()){
                edit_text_password.error = "Password Required"
                edit_text_password.requestFocus()
                return@setOnClickListener
            }

            val dbHelper = AccountDatabase(this, "WeatherUser.db", 1)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("Account", email)
                put("Password", password)
            }
            db.insert("User", null , values)

            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
        }

        text_view_login.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        ActivityCollector.addActivity(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        //用于删除当前Activity到activities的List中：用于可以随时随地的退出程序
        ActivityCollector.removeActivity(this)
    }

}