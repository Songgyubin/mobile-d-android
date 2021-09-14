package op.gg.joinus.util

import android.content.Context
import android.content.SharedPreferences


open class SharedPreferenceManager {
    companion object {
        private const val TAG = "SharedPreferenceManager"
        private const val APP_PREFERENCE_NAME = "join_us"

        private fun getPreferences(context: Context): SharedPreferences? {
            return context.getSharedPreferences(APP_PREFERENCE_NAME, Context.MODE_PRIVATE)
        }

        fun setString(context: Context, key: String, value: String) {
            val preference = getPreferences(context)
            preference?.edit()?.let {
                it.putString(key, value)
                it.apply()
            }
        }

        fun setInt(context: Context, key: String, value: Int) {
            val preference = getPreferences(context)
            preference?.edit()?.let {
                it.putInt(key, value)
                it.apply()
            }
        }

        fun setBoolean(context: Context, key: String, value: Boolean) {
            val preference = getPreferences(context)
            preference?.edit()?.let {
                it.putBoolean(key, value)
                it.apply()
            }
        }

        fun getString(context: Context, key: String): String =
            getPreferences(context)?.getString(key, "")!!

        fun getInt(context: Context, key: String): Int =
            getPreferences(context)?.getInt(key, 0)!!

        fun getBoolean(context: Context, key: String): Boolean =
            getPreferences(context)?.getBoolean(key, false)!!
    }
}