package com.gplio.rrrobot

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import android.util.Log
import com.gplio.rrrobot.common.DexLoader
import com.gplio.rrrobot.common.IRRRobotTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import java.io.File

/**
 */
@RunWith(AndroidJUnit4::class)
class BackgroundInstrumentedTest {
    private lateinit var device: UiDevice
    private lateinit var targetContext: Context
    private lateinit var dexFile: File
    private lateinit var classToRun: String
    private lateinit var arguments: Bundle

    private var loadedInstance: IRRRobotTest? = null

    @Before
    fun setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.setCompressedLayoutHeirarchy(true);
        targetContext = InstrumentationRegistry.getTargetContext()

        arguments = InstrumentationRegistry.getArguments()

        log("setup: arguments: $arguments")


        val dexPath = arguments.getString(DEX_PATH_ARG, "")
        dexFile = File(Environment.getExternalStorageDirectory(), dexPath)

        log("Using dex file ${dexFile.absolutePath}")

        classToRun = arguments.getString(CLASS_TO_RUN_ARG, "")

        log("Class to run $classToRun")

        log("Loading dex")

        loadedInstance = DexLoader.loadInstance(targetContext, targetContext.classLoader, dexFile, classToRun) as IRRRobotTest?
    }


    @Test
    fun runDex() {
        loadedInstance?.run(targetContext, device, arguments)
    }


    companion object {
        const val TAG = "RRRobot.BackgroundInstrumentedTest"
        const val DEX_PATH_ARG = "DEX_PATH"
        const val CLASS_TO_RUN_ARG = "CLASS_TO_RUN"

        fun log(text : String) {
            Log.d(TAG, text)
        }
    }
}
