package driver;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.remote.options.UiAutomator2Options;

import java.net.URL;

public class DriverFactory {

    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {

        if (driver == null) {
            try {

                UiAutomator2Options options = new UiAutomator2Options();

                // 🔹 CHANGE THESE BASED ON YOUR APP
                options.setDeviceName("emulator-5554");     // Emulator name from "adb devices"
                options.setPlatformVersion("14");           // Android version of emulator/device

                // 🔹 Change package & activity
                options.setAppPackage("your.app.package");
                options.setAppActivity("your.app.activity");

                // 🔹 URL of running Appium server
                URL url = new URL("http://127.0.0.1:4723");

                driver = new AndroidDriver(url, options);
                System.out.println("🚀 Appium Driver Started Successfully");

            } catch (Exception e) {
                throw new RuntimeException("❌ Failed to start driver: " + e.getMessage());
            }
        }

        return driver;
    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("🛑 Driver Closed");
        }
    }
}
