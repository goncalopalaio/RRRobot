package com.gplio.rrrobot.common

import android.content.Context
import android.util.Log
import dalvik.system.DexClassLoader
import java.io.File

class DexLoader {
    companion object {
        const val TAG = "RRRobot.DexLoader"

        fun loadInstance(context: Context, parentClassLoader: ClassLoader, dex: File, cls: String, forceNewCache: Boolean = true): Any? {
            try {

                val cacheFile = if (forceNewCache) {
                    "rrrobot-cache-dex-${System.currentTimeMillis()}"
                } else {
                    "rrrobot-cache-dex"
                }

                val cacheDirectory = context.getDir(cacheFile, Context.MODE_PRIVATE)

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