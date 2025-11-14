package com.example.aurasense.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

class ThemeManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)

    fun saveTheme(isDarkMode: Boolean) {
        prefs.edit().putBoolean("isDarkMode", isDarkMode).apply()
        applyTheme(isDarkMode)
    }

    fun getTheme(): Boolean {
        return prefs.getBoolean("isDarkMode", false)
    }

    fun applyTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}