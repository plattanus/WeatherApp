package com.sunnyweather.android.ui.user

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.sunnyweather.android.MainActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.init.LoginActivity
import kotlinx.android.synthetic.main.fragment_user.*
import kotlin.system.exitProcess


class UserFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userVersion.setOnClickListener {
            Toast.makeText(context, "当前版本：1.1.1", Toast.LENGTH_SHORT).show()
        }
        userPassword.setOnClickListener {
            var intent = Intent(context, DialogPassword::class.java);
            context?.startActivity(intent)
        }
        userShare.setOnClickListener {
            var intent = Intent(context, AppShare::class.java);
            context?.startActivity(intent)
        }
        userLogout.setOnClickListener {
            ActivityCollector.finishAll()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

}
