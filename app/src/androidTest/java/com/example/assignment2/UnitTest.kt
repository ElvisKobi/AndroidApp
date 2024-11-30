package com.example.assignment2

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue

private const val LAUNCH_TIMEOUT = 5000L
private const val PACKAGE_NAME = "com.example.assignment2"

@RunWith(AndroidJUnit4::class)
class UnitTest {
    private lateinit var device: UiDevice

    @Before
    fun setUp() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        // Press home button to start from the home screen
        device.pressHome()

        val launcherPackage: String = device.launcherPackageName
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT
        )
    }

    @Test
    fun testStartAppAndMainActivity() {
        val packageName = "com.example.assignment2"
        val appName = "Assignment2"

        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT
        )

        // Find the app icon by text
        val appIcon = device.findObject(UiSelector().descriptionContains(appName))
        assertThat("App icon not found", appIcon, notNullValue())
        appIcon.click()

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(packageName).depth(0)), LAUNCH_TIMEOUT
        )
    }

    @Test
    @Throws(UiObjectNotFoundException::class)
    fun testStartAppAndCheckActivity() {
        // Launch the app by clicking on the launcher icon
        val appIcon = device.findObject(UiSelector().descriptionContains("Assignment2"))
        assertThat("App icon not found", appIcon, notNullValue())
        appIcon.click()

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT
        )

        // Click on the "Explicit" button
        val explicitButton = device.findObject(UiSelector().text("Explicit"))
        assertThat("Explicit button not found", explicitButton, notNullValue())
        explicitButton.click()

        // Check that the resulting activity has one of the mobile software engineering challenges
        val challengeText = device.findObject(UiSelector().textContains("Mobile Software Engineering Challenges"))
        assertTrue(challengeText.exists())
    }
}