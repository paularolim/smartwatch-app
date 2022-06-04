package com.smartwatch.bluetooth

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import java.util.*
import kotlin.collections.ArrayList

class BluetoothPackage : ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        val modules = ArrayList<NativeModule>()
        modules.add(BluetoothModule(reactContext))
        return modules
    }

    override fun createViewManagers(
        reactContext: ReactApplicationContext
    ): MutableList<com.facebook.react.uimanager.ViewManager<View, ReactShadowNode<*>>> {
        return Collections.emptyList()
    }
}