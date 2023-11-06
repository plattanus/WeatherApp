package com.sunnyweather.android.init

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import com.sunnyweather.android.MainActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.user.ActivityCollector
import com.sunnyweather.android.ui.user.UserWhoLogin

class LoginActivity : AppCompatActivity() {

    lateinit var loginLayout : RelativeLayout
    lateinit var edit_text_email : EditText
    lateinit var edit_text_password : EditText
    lateinit var text_view_forget_password : TextView
    lateinit var button_sign_in : Button
    lateinit var text_view_register : TextView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginLayout = findViewById(R.id.loginLayout)
        edit_text_email = findViewById(R.id.edit_text_email)
        edit_text_password = findViewById(R.id.edit_text_password)
        text_view_forget_password = findViewById(R.id.text_view_forget_password)
        button_sign_in = findViewById(R.id.button_sign_in)
        text_view_forget_password = findViewById(R.id.text_view_forget_password)
        progressBar = findViewById(R.id.progressBar)
        text_view_register = findViewById(R.id.text_view_register)

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
            val cursor = db.query("User", null, null, null, null, null, null)
            var checkmatch = false
            if (cursor.moveToFirst()) {
                do {
                    val Account = cursor.getString(cursor.getColumnIndex("Account"))
                    val Password = cursor.getString(cursor.getColumnIndex("Password"))
                    if(Account == email && Password == password){
                        checkmatch = true
                    }
                } while (cursor.moveToNext())
            }
            cursor.close()
            if(checkmatch == true){
                UserWhoLogin.LOAN_PERSON_Account = email
                UserWhoLogin.LOAN_PERSON_Password = password
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "账号或者密码输入错误", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
        text_view_register.setOnClickListener {
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

        text_view_forget_password.setOnClickListener {
            val intent = Intent(this@LoginActivity,ForgetPasswordActivity::class.java)
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