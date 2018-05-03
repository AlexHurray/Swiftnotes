package com.moonpi.swiftnotes.test

import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import org.hamcrest.CoreMatchers
import org.junit.Assert

open class MainMenu constructor(device : UiDevice) : BaseScreen(device) {
    private val backUp = By.text("Backup notes")
    private val restore =  By.text("Restore notes")
    private val rateApp = By.text("Rate app")

    fun checkItems() : MainMenu {
        Assert.assertThat(find(backUp), CoreMatchers.notNullValue())
        Assert.assertThat(find(restore), CoreMatchers.notNullValue())
        Assert.assertThat(find(rateApp), CoreMatchers.notNullValue())

        return this
    }

    fun closeMenu(){
        device.pressBack()
    }
}