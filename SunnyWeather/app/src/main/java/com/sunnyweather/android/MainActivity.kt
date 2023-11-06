package com.sunnyweather.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sunnyweather.android.SunnyWeatherApplication.Companion.context
import com.sunnyweather.android.init.LoginActivity
import com.sunnyweather.android.ui.user.ActivityCollector

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCollector.addActivity(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        //用于删除当前Activity到activities的List中：用于可以随时随地的退出程序
        ActivityCollector.removeActivity(this)
    }
}
