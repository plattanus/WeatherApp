package com.sunnyweather.android.init

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.user.ActivityCollector

class ForgetPasswordActivity : AppCompatActivity() {

    lateinit var edit_text_email : EditText
    lateinit var button_check : Button
    lateinit var text_view_login : TextView
    lateinit var progresbar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        edit_text_email = findViewById(R.id.edit_text_email)
        button_check = findViewById(R.id.button_check)
        text_view_login = findViewById(R.id.text_view_login)
        progresbar = findViewById(R.id.progressBar)

        button_check.setOnClickListener {
            val email = edit_text_email.text.toString()
            if (email.isEmpty()){
                edit_text_email.error = "Email Required"
                edit_text_email.requestFocus()
                return@setOnClickListener
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                edit_text_email.error = "Valid Email Required"
                edit_text_email.requestFocus()
                return@setOnClickListener
            }

            val dbHelper = AccountDatabase(this, "WeatherUser.db", 1)
            val db = dbHelper.writableDatabase
            val cursor = db.query("User", null, null, null, null, null, null)
            var checkmatch = false
            var password = "11111"
            if (cursor.moveToFirst()) {
                do {
                    val Account = cursor.getString(cursor.getColumnIndex("Account"))
                    if(Account == email){
                        password = cursor.getString(cursor.getColumnIndex("Password"))
                        checkmatch = true
                    }
                } while (cursor.moveToNext())
            }
            cursor.close()
            if(checkmatch == true){
                Toast.makeText(this, "账号"+email+"的密码是"+password, Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "账号不存在", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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