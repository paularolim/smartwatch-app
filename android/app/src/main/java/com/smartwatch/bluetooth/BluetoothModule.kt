package com.smartwatch.bluetooth

import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi

const val DISCOVERY_TIME_DELAY_IN_MS = 15000

class BluetoothModule(reactApplicationContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactApplicationContext) {
    override fun getName(): String {
        return "BluetoothConnection"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @ReactMethod
    fun hasBluetoothSupport(): Boolean  {
        val bluetoothManager: BluetoothManager = reactApplicationContext.getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter
        return if (bluetoothAdapter == null) {
            Log.d("BluetoothConnection", "Sem suporte ao bluetooth")
            false
        } else {
            Log.d("BluetoothConnection", "Com suporte ao bluetooth")
            true
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @ReactMethod
    fun isBluetoothEnabled(): Boolean {
        val bluetoothManager: BluetoothManager = reactApplicationContext.getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter = bluetoothManager?.adapter

        return if (bluetoothAdapter?.isEnabled) {
            Log.d("BluetoothConnection", "Bluetooth habilitado")
            true
        } else {
            Log.d("BluetoothConnection", "Bluetooth desabilitado")
            false
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @ReactMethod
    fun listPairedDevices() {
        val bluetoothManager: BluetoothManager = reactApplicationContext.getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter = bluetoothManager?.adapter

        val hasBluetoothSupport = hasBluetoothSupport()
        if (hasBluetoothSupport) {
            val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
            pairedDevices?.forEach { device ->
                val deviceName = device.name
                val deviceHardwareAddress = device.address // MAC address
                Log.d("BluetoothConnection", "Device $deviceName - $deviceHardwareAddress")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @ReactMethod
    fun listDiscoveryDevices() {
        val bluetoothManager: BluetoothManager = reactApplicationContext.getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter = bluetoothManager?.adapter

        val deviceScanCallback: DeviceScanCallback = DeviceScanCallback()
        val bluetoothScanner: BluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner

        if (bluetoothScanner != null) {
            Log.d("BluetoothConnection", "Buscando dispositivos...")
            bluetoothScanner.startScan(deviceScanCallback)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            bluetoothAdapter?.bluetoothLeScanner.stopScan(deviceScanCallback)
            Log.d("BluetoothConnection", "Busca encerrada")
            deviceScanCallback.getDiscoveredDevices()
        }, DISCOVERY_TIME_DELAY_IN_MS.toLong())
    }
}