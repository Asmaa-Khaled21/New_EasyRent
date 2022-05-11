package com.sakura.easyrent.control.managers

import android.content.Context
import android.content.SharedPreferences

/** [SPManager] this kotlin class is used to get and store any value in main preferences */
class SPManager(context: Context) {

    // Fields:
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("SPManager", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    // Static:
    companion object {

        // Fields(Keys):
        const val USER_META_DATA: String = "USER_META_DATA"
        const val ACCESS_TOKEN: String = "ACCESS_TOKEN"
        const val REFRESH_TOKEN: String = "REFRESH_TOKEN"
    }

    // Method(Write):
    fun write(key: String, data: Any): Boolean {
        // Checking:
        when (data) {
            // Writing:
            is String -> editor.putString(key, data)
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
            is Float -> editor.putFloat(key, data)
            is Boolean -> editor.putBoolean(key, data)
        }
        return editor.commit()
    }

    // Method(Read):
    fun read(key: String, type: Any): Any {
        // Checking:
        when (type) {
            // Reading:
            is String -> return sharedPreferences.getString(key, "")!!
            is Int -> return sharedPreferences.getInt(key, 0)
            is Long -> return sharedPreferences.getLong(key, 0)
            is Float -> return sharedPreferences.getFloat(key, 0F)
            is Boolean -> return sharedPreferences.getBoolean(key, false)
        }
        // Retuning:
        return 0F
    }
}