package com.baksha97.turingmachineapp.android

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * A generic class that holds a value or an exception
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

abstract class SharedPreferenceLiveData<T>(
    val sharedPrefs: SharedPreferences,
    val key: String,
    val defValue: T
) : LiveData<T>() {

    private val preferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == this.key) {
                value = getValueFromPreferences(key, defValue)
            }
        }

    abstract fun getValueFromPreferences(key: String, defValue: T): T

    override fun onActive() {
        super.onActive()
        value = getValueFromPreferences(key, defValue)
        sharedPrefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    override fun onInactive() {
        sharedPrefs.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        super.onInactive()
    }
}

class SharedPreferenceIntLiveData(sharedPrefs: SharedPreferences, key: String, defValue: Int) :
    SharedPreferenceLiveData<Int>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Int): Int =
        sharedPrefs.getInt(key, defValue)
}

class SharedPreferenceStringLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: String
) :
    SharedPreferenceLiveData<String>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: String): String =
        sharedPrefs.getString(key, defValue) ?: "" //???
}

class SharedPreferenceBooleanLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: Boolean
) :
    SharedPreferenceLiveData<Boolean>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Boolean): Boolean =
        sharedPrefs.getBoolean(key, defValue)
}

class SharedPreferenceFloatLiveData(sharedPrefs: SharedPreferences, key: String, defValue: Float) :
    SharedPreferenceLiveData<Float>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Float): Float =
        sharedPrefs.getFloat(key, defValue)
}

class SharedPreferenceLongLiveData(sharedPrefs: SharedPreferences, key: String, defValue: Long) :
    SharedPreferenceLiveData<Long>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Long): Long =
        sharedPrefs.getLong(key, defValue)
}

class SharedPreferenceStringSetLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: Set<String>
) :
    SharedPreferenceLiveData<Set<String>>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Set<String>): Set<String> =
        sharedPrefs.getStringSet(key, defValue) ?: setOf() //???
}

fun SharedPreferences.intLiveData(key: String, defValue: Int): SharedPreferenceLiveData<Int> {
    return SharedPreferenceIntLiveData(this, key, defValue)
}

fun SharedPreferences.stringLiveData(
    key: String,
    defValue: String
): SharedPreferenceLiveData<String> {
    return SharedPreferenceStringLiveData(this, key, defValue)
}

fun SharedPreferences.booleanLiveData(
    key: String,
    defValue: Boolean
): SharedPreferenceLiveData<Boolean> {
    return SharedPreferenceBooleanLiveData(this, key, defValue)
}

fun SharedPreferences.floatLiveData(key: String, defValue: Float): SharedPreferenceLiveData<Float> {
    return SharedPreferenceFloatLiveData(this, key, defValue)
}

fun SharedPreferences.longLiveData(key: String, defValue: Long): SharedPreferenceLiveData<Long> {
    return SharedPreferenceLongLiveData(this, key, defValue)
}

fun SharedPreferences.stringSetLiveData(
    key: String,
    defValue: Set<String>
): SharedPreferenceLiveData<Set<String>> {
    return SharedPreferenceStringSetLiveData(this, key, defValue)
}