package com.moonpi.swiftnotes.test

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.*
import org.hamcrest.CoreMatchers
import org.junit.Assert

abstract class BaseScreen(val device: UiDevice) {
    val WAIT_TIMEOUT: Long = 5000

    val id = "${InstrumentationRegistry.getTargetContext().packageName}:id"
    fun find(by: BySelector): UiObject2 = device.wait(Until.findObject(by), WAIT_TIMEOUT)
    fun click(by: BySelector) = find(by).click()
    fun findFromObject(obj: UiObject2, by: BySelector) = obj.wait(Until.findObject(by), WAIT_TIMEOUT)
    fun checkByText(text: String) = Assert.assertThat(device.wait(Until.findObject(By.text(text)), WAIT_TIMEOUT), CoreMatchers.notNullValue())
}
