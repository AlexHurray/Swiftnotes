package com.moonpi.swiftnotes.test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiObject2
import android.support.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import ru.tinkoff.allure.android.deviceScreenshot
import ru.tinkoff.allure.annotations.DisplayName
import ru.tinkoff.allure.step


@RunWith(AndroidJUnit4::class)
@DisplayName("Test Suite")
class Tests : BaseRunner() {
    @Test
    @DisplayName("First Test")
    fun test1() {
        lateinit var main : MainActivityPage
        lateinit var edit : EditActivityPage

        step("Проверка на главном экране наличия основных элементов") {
            main = MainActivityPage(mDevice).checkItems()
            deviceScreenshot("shot1_1")
        }

        step("Открытие экрана создания заметки") {
            edit = main.newNote()
            deviceScreenshot("shot1_2")
        }

        step("Нажать кнопку ‘<-’. Проверка диалога") {
            edit.checkItems().clickBack()
            deviceScreenshot("shot1_3")
        }

        step("Нажать ‘NO’. Возврат") {
            edit.checkSaveChangesItems().clickNo()
            deviceScreenshot("shot1_4")
        }
    }

    @Test
    @DisplayName("Second Test")
    fun test2() {
        val main = MainActivityPage(mDevice)
        lateinit var edit : EditActivityPage

        step("Открытие экрана создания заметки") {
            edit = main.newNote()
            deviceScreenshot("shot2_1")
        }

        step ("Проверка ввода текста"){
            edit.fillNote("Тестовая запись 1")
            edit.fillTitle("Заметка 1")

            assertEquals("Тестовая запись 1", edit.getNoteText())
            assertEquals("Заметка 1", edit.getTitleText())

            deviceScreenshot("shot2_2")
        }

        step("Нажать кнопку ‘<-’. Проверка диалога") {
            edit.clickBack()
                    .checkSaveChangesItems()
            deviceScreenshot("shot2_3")
        }

        step("Нажать кнопку YES. Проверить, что появилась новая запись.") {
            edit.clickYes()
            main.checkByText("Заметка 1")
            deviceScreenshot("shot2_4")
        }
    }

    @Test
    @DisplayName("Third Test")
    fun test3() {
        val main = MainActivityPage(mDevice)
        lateinit var edit : EditActivityPage
        lateinit var mainMenu : MainMenu

        step("Проверяем главное меню"){
            mainMenu = main.clickMenu().checkItems()
            deviceScreenshot("shot3_1")
        }

        step("Открытие экрана создания заметки") {
            mainMenu.closeMenu()
            edit = main.newNote()
            deviceScreenshot("shot3_2")
        }

        step("Проверяем меню новой заметки"){
            edit.clickMenu().checkItems()
            deviceScreenshot("shot3_3")
        }
    }
}