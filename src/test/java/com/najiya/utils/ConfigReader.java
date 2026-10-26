
package com.najiya.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties =
            new Properties();

    static {
        try (InputStream input =
                ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                    "config.properties file not found");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new IllegalStateException(
                "Unable to load config.properties", e);
        }
    }

    // Read website URL
    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    // Read browser name
    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    // Read explicit wait time
    public static int getExplicitWait() {
        return Integer.parseInt(
            properties.getProperty("explicitWait", "15"));
    }

    // Check slow mode
    public static boolean isSlowMode() {
        return Boolean.parseBoolean(
            properties.getProperty("slowMode", "false"));
    }

    // Read step delay
    public static int getStepDelay() {
        return Integer.parseInt(
            properties.getProperty("stepDelay", "2000"));
    }

    // Pause automation for learning/demo
    public static void pause() throws InterruptedException {
        if (isSlowMode()) {
            Thread.sleep(getStepDelay());
        }
    }
}
