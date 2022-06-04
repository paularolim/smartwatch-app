package com.smartwatch.bluetooth

import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.os.Build
import androidx.annotation.RequiresApi

class BluetoothModule(reactApplicationContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactApplicationContext) {
    override fun getName(): String {
        return "BluetoothConnection"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @ReactMethod
    fun hasBluetoothSupport() {
        val bluetoothManager: BluetoothManager = reactApplicationContext.getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
        if (bluetoothAdapter == null) {
            Log.d("BluetoothConnection", "Sem suporte ao bluetooth")
        } else {
            Log.d("BluetoothConnection", "Com suporte ao bluetooth")
        }
    }
}