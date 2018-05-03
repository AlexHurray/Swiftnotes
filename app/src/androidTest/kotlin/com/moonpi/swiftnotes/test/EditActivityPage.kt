package com.moonpi.swiftnotes.test

import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObject2
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert

open class EditActivityPage constructor(device : UiDevice) : BaseScreen(device){

    private val title = By.res("$id/titleEdit")
    private val note = By.res("$id/bodyEdit")
    private val back = By.clazz("android.widget.ImageButton").pkg("com.moonpi.swiftnotes")
    private val dialogText = By.res("android:id/message")//By.text("Save changes?")
    private val buttonYes = By.res("android:id/button1")//By.text("YES")
    private val buttonNo = By.res("android:id/button2")//By.text("NO")
    private val menu = By.desc("More options")

    /*fun getTitle(): UiObject2 {
        return find(title)
    }

    fun getNote(): UiObject2 {
        return find(note)
    }
*/
    fun fillTitle(text: String){
        find(title).text = text
    }

    fun fillNote(text: String){
        find(note).text = text
    }

    fun getTitleText(): String {
        return find(title).text
    }

    fun getNoteText(): String {
        return find(note).text
    }

    fun clickBack() : EditActivityPage {
        find(back).click()
        device.waitForWindowUpdate(null, WAIT_TIMEOUT)
        return this
    }

    fun clickYes(){
        find(buttonYes).click()
        device.waitForWindowUpdate(null, WAIT_TIMEOUT)
    }

    fun clickNo(){
        find(buttonNo).click()
        device.waitForWindowUpdate(null, WAIT_TIMEOUT)
    }

    fun checkItems() : EditActivityPage {
        Assert.assertTrue(find(title).text == "Title")
        Assert.assertTrue(find(note).text == "Note")

        return this
    }

    fun checkSaveChangesItems() : EditActivityPage {
        Assert.assertTrue(find(buttonNo).text == "NO")
        Assert.assertTrue(find(buttonYes).text == "YES")
        Assert.assertTrue(find(dialogText).text == "Save changes?")

        return this
    }

    fun clickMenu() : EditMenu{
        find(menu).click()
        return EditMenu(device)
    }
}