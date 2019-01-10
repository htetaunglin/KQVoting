package edu.ucsmub.kqvoting.Pref

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class MyPreference(var mContext: Context) {

    private val MY_PREFER = "MyPref"
    private val FIRST_ITME = "FIRST_TIME"

    private val KING_ID = "KING_ID"
    private val QUEEN_ID = "QUEEN_ID"
    private val POPULAR_ID = "POPULAR_ID"
    private val INNOCENCE_ID = "INNOCENCE_ID"

    private val YOUR_VOTE_KEY = "YOUR_KEY"

    private val UZAWFONT = "UZAW"

    private val prefer = getPreferences()
    private val editor = prefer.edit()

    private fun getPreferences(): SharedPreferences {
        val pref = mContext.getSharedPreferences(MY_PREFER, Context.MODE_PRIVATE)
        return pref
    }

    fun isFirstTime(): Boolean {
        return prefer.getBoolean(FIRST_ITME, true)
    }

    fun setFirstTime(boolean: Boolean) {
        editor.putBoolean(FIRST_ITME, boolean)
        editor.commit()
    }


    fun getKingID(): String {
        return prefer.getString(KING_ID, "")
    }

    fun setKingID(id: String) {
        editor.putString(KING_ID, id)
        editor.commit()
    }

    fun getQueenID(): String {
        return prefer.getString(QUEEN_ID, "")
    }

    fun setQueenID(id: String) {
        editor.putString(QUEEN_ID, id)
        editor.commit()
    }

    fun getPopularID(): String {
        return prefer.getString(POPULAR_ID, "")
    }

    fun setPopularID(id: String) {
        editor.putString(POPULAR_ID, id)
        editor.commit()
    }

    fun getInnocenceID(): String {
        return prefer.getString(INNOCENCE_ID, "")
    }

    fun setInnocenceID(id: String) {
        editor.putString(INNOCENCE_ID, id)
        editor.commit()
    }

    fun getQRKey(): String {
        return prefer.getString(YOUR_VOTE_KEY, "")
    }

    fun setQRKey(key: String) {
        editor.putString(YOUR_VOTE_KEY, key)
        editor.commit()
    }

    fun isZawgyi(): Boolean {
        return prefer.getBoolean(UZAWFONT, true)
    }

    fun setZawgyi(b: Boolean) {
        editor.putBoolean(UZAWFONT, b)
        editor.commit()
    }


}