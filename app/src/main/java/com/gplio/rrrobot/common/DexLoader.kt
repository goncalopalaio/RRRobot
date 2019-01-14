package com.gplio.rrrobot.common

import android.content.Context
import android.util.Log
import com.gplio.rrrobot.MainActivity
import dalvik.system.DexClassLoader
import java.io.File

class DexLoader {
    companion object {
        const val TAG = "RRRobot.DexLoader"

        fun loadInstance(context: Context, parentClassLoader: ClassLoader, dex: File, cls: String): Any? {
            try {
                val cacheDirectory = context.getDir("rrrobot-cache-outdex", Context.MODE_PRIVATE)

                Log.d(TAG, "CacheDirectory: $cacheDirectory")

                val dexLoader = DexClassLoader(dex.absolutePath, cacheDirectory.absolutePath, null, parentClassLoader)

                val moduleClass = dexLoader.loadClass(cls)
                return moduleClass.newInstance()
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
            }

            return null
        }
    }
}