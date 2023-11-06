package com.sunnyweather.android.ui.user

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sunnyweather.android.MainActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.init.AccountDatabase
import com.sunnyweather.android.init.LoginActivity

class AppShare : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.updatepassbg)
        val builder = AlertDialog.Builder(this)
//        builder.setTitle("修改密码")
        val dialogLayout = layoutInflater.inflate(R.layout.myappshare, null)
        builder.setView(dialogLayout)
        builder.setPositiveButton("确认") { _, _ ->
            val intent = Intent(this@AppShare, MainActivity::class.java)
            startActivityForResult(intent, 1)
        }
        builder.setNegativeButton("取消") {_,_->
            val intent = Intent(this@AppShare, MainActivity::class.java)
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
