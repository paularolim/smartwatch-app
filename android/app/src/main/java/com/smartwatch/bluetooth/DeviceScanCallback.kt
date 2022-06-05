package com.smartwatch.bluetooth

import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.util.Log
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeMap

const val UNKNOWN_DEVICE: String = "Unknown Device"
const val UNKNOWN_ADDRESS: String = "Unknown Address"

class DeviceScanCallback: ScanCallback() {
    private val discoveredDevices: MutableMap<String, String> = mutableMapOf()

    override fun onScanResult(callbackType: Int, result: ScanResult?) {
        Log.d("BluetoothConnection", "onScanResult")

        var deviceName: String? = result?.device?.name
        var deviceAddress: String? = result?.device?.address

        if (deviceName == null) {
            deviceName = UNKNOWN_DEVICE
        }
        if (deviceAddress == null) {
            deviceAddress = UNKNOWN_ADDRESS
        }

        if (discoveredDevices.containsValue(deviceAddress)) {
            // Log.d("BluetoothConnection", "Device with mac: $deviceAddress already discovered")
        } else {
            discoveredDevices[deviceName] = deviceAddress
        }
    }

    override fun onBatchScanResults(results: MutableList<ScanResult>?) {
        Log.d("BluetoothConnection", "onBatchScanResults")
    }

    override fun onScanFailed(errorCode: Int) {
        Log.e("BluetoothConnection", "Scan failed with error code $errorCode")
    }

    fun getDiscoveredDevices(): WritableMap {
        Log.d("BluetoothConnection", "getDiscoveredDevices 1")
        val mappedDevices: WritableMap = WritableNativeMap()
        discoveredDevices.keys.forEach {
            mappedDevices.putString("deviceName", it)
            mappedDevices.putString("deviceAddress", discoveredDevices[it])
            Log.d("BluetoothConnection", "device $it - ${discoveredDevices[it]}")
        }
        Log.d("BluetoothConnection", "getDiscoveredDevices 2")
        return mappedDevices
    }
}