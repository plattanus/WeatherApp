package com.sunnyweather.android.init

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AccountDatabase(val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {

    private val createUser = "create table User (" +
            "Account text primary key not null," +
            "Password text not null)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createUser)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists User")
        onCreate(db)
    }
}