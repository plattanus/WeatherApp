package com.sunnyweather.android.ui.user

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.sunnyweather.android.MainActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.init.AccountDatabase
import com.sunnyweather.android.init.LoginActivity

class DialogPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.updatepassbg)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("修改密码")
        val dialogLayout = layoutInflater.inflate(R.layout.updatepassword, null)
        val editOldPassword = dialogLayout.findViewById<EditText>(R.id.editOldPassword)
        val editNewPassword = dialogLayout.findViewById<EditText>(R.id.editNewPassword)
        val editConfirmNewPassword = dialogLayout.findViewById<EditText>(R.id.editConfirmNewPassword)
        builder.setView(dialogLayout)
        builder.setPositiveButton("确认") { _, _ ->

            if(editOldPassword.text.toString() != UserWhoLogin.LOAN_PERSON_Password){
                Toast.makeText(this, "修改失败，原密码输入错误", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@DialogPassword, MainActivity::class.java)
                startActivityForResult(intent, 1)
            }
            else if(editNewPassword.text.toString() != editConfirmNewPassword.text.toString()){
                Toast.makeText(this, "修改失败，密码两次输入不一致", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@DialogPassword, MainActivity::class.java)
                startActivityForResult(intent, 1)
            }
            else{
                val dbHelper = AccountDatabase(this, "WeatherUser.db", 1)
                val db = dbHelper.writableDatabase
                val values = ContentValues()
                values.put("Password", editNewPassword.text.toString())
                db.update("User", values, "Account = ?", arrayOf(UserWhoLogin.LOAN_PERSON_Account))
                Toast.makeText(this, "修改成功，请重新登录", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@DialogPassword, LoginActivity::class.java)
                startActivityForResult(intent, 1)
            }
        }
        builder.setNegativeButton("取消") {_,_->
            val intent = Intent(this@DialogPassword, MainActivity::class.java)
            startActivityForResult(intent, 1)
        }
        builder.show()
        ActivityCollector.addActivity(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        //用于删除当前Activity到activities的List中：用于可以随时随地的退出程序
        ActivityCollector.removeActivity(this)
    }
}
