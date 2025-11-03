package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    public static byte[] captureScreenshot(WebDriver driver, String testName) {
        try {
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            saveScreenshotToFile(bytes, testName);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void saveScreenshotToFile(byte[] screenshotBytes, String testName) {
        String screenshotDir = "target/screenshots/";
        try {
            File dir = new File(screenshotDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = String.format("%s_%s.png", testName, timestamp);
            File file = new File(screenshotDir + filename);

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(screenshotBytes);
            }

            System.out.println("📸 Screenshot saved: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
