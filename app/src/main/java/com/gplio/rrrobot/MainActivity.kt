package com.gplio.rrrobot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.gplio.rrrobot.common.IRRRobot
import dalvik.system.DexClassLoader
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import android.content.IntentFilter



class MainActivity : AppCompatActivity() {
    private val eventReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(ctx: Context?, intent: Intent?) {
            Log.d(TAG, "eventReceiver:: intent: $intent")
            reRun()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_action.setOnClickListener {
            reRun()
        }

        val filter = IntentFilter()
        filter.addAction(EVENT_RECEIVER_ACTION)
        registerReceiver(eventReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(eventReceiver)
    }

    private fun reRun() {
        val dexFile = File(Environment.getExternalStorageDirectory(), "rrrobot.dex")
        Log.d(TAG, "path:: dexFile: " + dexFile.absolutePath)

        val cls = "com.gplio.rrrobot.common.HelloLog"
        val read = read(dexFile, cls)

        val result = read?.run(this)

        Log.d(TAG, "Hello result: $result")
        updateUi(cls, result)
    }

    private fun updateUi(title: String, body: String?) {
        tv_title.text = title;
        tv_body.text = body
    }

    fun read(dex : File, cls : String): IRRRobot? {

        try {
            val cacheDirectory = getDir("rrrobot-cache-outdex", Context.MODE_PRIVATE)
            Log.d(TAG, "CacheDirectory: $cacheDirectory")

            val classLoader = DexClassLoader(dex.absolutePath, cacheDirectory.absolutePath,null, classLoader)

            val moduleClass = classLoader.loadClass(cls)
            return moduleClass.newInstance() as IRRRobot
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }

        return null
    }

    companion object {
        const val TAG = "RRRobot.MainActivity"
        const val EVENT_RECEIVER_ACTION = "$TAG.EVENT_RECEIVER_ACTION"
    }
}
