package com.pixis.trakt_api

import android.content.Context
import android.content.SharedPreferences
import com.pixis.trakt_api.network_models.AccessToken

/**
 * Created by Dan on 11/15/2016.
 */
class TokenDatabase(val context: Context) {

    val prefs : SharedPreferences

    init {
        prefs = context.getSharedPreferences("TRAKT_ACCESS_TOKEN", Context.MODE_PRIVATE)
    }

    companion object {
        private val ACCESS_TOKEN_KEY: String = "ACCESS_TOKEN_KEY"
        private val TOKEN_TYPE_KEY: String = "TOKEN_TYPE_KEY"
        private val EXPIRES_IN_KEY: String = "EXPIRES_IN_KEY"
        private val REFRESH_TOKEN_KEY: String = "REFRESH_TOKEN_KEY"
        private val SCOPE_KEY: String = "SCOPE_KEY"
    }

    fun isAuthenticated(): Boolean {
        return prefs.getBoolean("AUTHENTICATED", false)
    }

    fun getAccessToken(): String {
        return prefs.getString(ACCESS_TOKEN_KEY, "")
    }

    fun getToken(): AccessToken {
        return AccessToken(
                prefs.getString(ACCESS_TOKEN_KEY, ""),
                prefs.getString(TOKEN_TYPE_KEY, ""),
                prefs.getString(EXPIRES_IN_KEY, "0").toInt(),
                prefs.getString(REFRESH_TOKEN_KEY, ""),
                prefs.getString(SCOPE_KEY, "")
        )
    }

    fun saveToken(accessToken: AccessToken) {
        val editor = prefs.edit()
        editor.putString(ACCESS_TOKEN_KEY, accessToken.access_token)
        editor.putString(TOKEN_TYPE_KEY, accessToken.token_type)
        editor.putString(EXPIRES_IN_KEY, accessToken.expires_in.toString())
        editor.putString(REFRESH_TOKEN_KEY, accessToken.refresh_token)
        editor.putString(SCOPE_KEY, accessToken.scope)
        editor.putBoolean("AUTHENTICATED", true)
        editor.apply()
    }

}