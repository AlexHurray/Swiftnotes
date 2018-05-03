package com.moonpi.swiftnotes.test

import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import org.hamcrest.CoreMatchers
import org.junit.Assert

open class EditMenu constructor(device : UiDevice) : BaseScreen(device) {
    private val noteFontSize = By.text("Note font size")
    private val hideNoteBody =  By.text("Hide note body in list")

    fun checkItems() : EditMenu {
        Assert.assertThat(find(noteFontSize), CoreMatchers.notNullValue())
        Assert.assertThat(find(hideNoteBody), CoreMatchers.notNullValue())

        return this
    }

    fun closeMenu(){
        device.pressBack()
    }
}