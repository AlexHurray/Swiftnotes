package com.moonpi.swiftnotes.test

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.Until
import android.util.Log
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Before
import org.junit.BeforeClass
import java.io.File


open class BaseRunner {
    val mDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    val TARGET_PACKAGE: String = InstrumentationRegistry.getTargetContext().getPackageName()
    val WAIT_TIMEOUT: Long = 5000
    val LAUNCH_TIMEOUT: Long = 5000

    companion object {
        @BeforeClass @JvmStatic
        fun clear() {
            val cache = getInstrumentation().getTargetContext().getCacheDir()
            File(cache.getParent()).deleteRecursively()
            File("sdcard/allure-results").deleteRecursively()
        }
    }

    @Before
    fun  setUp(){
        mDevice.pressHome()

        val launcherPackage: String = getLauncherPackageName()
        assertThat(launcherPackage, notNullValue())
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT)
        val context: Context = InstrumentationRegistry.getContext()
        val intent: Intent = context.getPackageManager().getLaunchIntentForPackage(TARGET_PACKAGE)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        mDevice.wait(Until.hasObject(By.pkg(TARGET_PACKAGE).depth(0)), LAUNCH_TIMEOUT)

        //File("sdcard/allure-results").deleteRecursively()
    }

    private fun getLauncherPackageName(): String {
        // Create launcher Intent
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)

        // Use PackageManager to get the launcher package name
        val pm = InstrumentationRegistry.getContext().packageManager
        val resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return resolveInfo.activityInfo.packageName
    }
}
