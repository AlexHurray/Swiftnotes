package com.moonpi.swiftnotes.test

import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert

open class MainActivityPage constructor(device : UiDevice) : BaseScreen(device){

    //private val toolbar = By.res("$id/toolbarMain")
    private val toolbarText = By.text("Swiftnotes")
    private val noNotes = By.res("$id/noNotes")
    private val newNote = By.res("$id/newNote")
    private val menu = By.desc("More options")


    fun newNote() : EditActivityPage {
        find(newNote).click()
        device.waitForWindowUpdate(null, WAIT_TIMEOUT)
        return EditActivityPage(device)
    }

    fun checkItems() : MainActivityPage {
        Assert.assertThat(find(toolbarText), notNullValue())
        Assert.assertTrue(find(noNotes).text == "Press '+' to add new note")
        Assert.assertTrue(find(newNote).isClickable)

        return this
    }

    fun clickMenu() : MainMenu {
        find(menu).click()
        return MainMenu(device)
    }
}