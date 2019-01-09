package com.gplio.rrrobot

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.gplio.rrrobot.common.IRRRobot
import dalvik.system.DexClassLoader
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val dexFile = File(Environment.getExternalStorageDirectory(), "rrrobot.dex")
        Log.d(TAG, "path:: dexFile: " + dexFile.absolutePath)


        val read = read(dexFile)

        Log.d(TAG, "Hello result: " + read?.run())
    }

    fun read(dex : File, cls : String = "com.gplio.rrrobot.common.Hello"): IRRRobot? {

        try {
            val cacheDirectory = getDir("rrrobot-cache-outdex", Context.MODE_PRIVATE)
            Log.d(TAG, "CacheDirectory: $cacheDirectory")

            val classLoader = DexClassLoader(dex.absolutePath, cacheDirectory.absolutePath,null, this.javaClass.classLoader)

            val moduleClass = classLoader.loadClass(cls)
            return moduleClass.newInstance() as IRRRobot
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }

        return null
    }

    companion object {
        const val TAG = "RRRobot.MainActivity"
    }
}
