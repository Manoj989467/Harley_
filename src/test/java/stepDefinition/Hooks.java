package stepDefinition;

import baseClass.BaseClass;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import javax.imageio.ImageIO;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Hooks extends BaseClass {

//    @After
//    public void takesScreenshot(Scenario scenario) throws IOException, AWTException {
//        if (scenario.isFailed()) {
//
//            String screenshotPath = ScreenshotUtil.captureSystemScreenshot(scenario.getName());
//            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
//
//        }
//    }
//    public static class ScreenshotUtil {
//
//        public static String captureSystemScreenshot(String scenarioName) throws IOException, AWTException {
//
//            String screenshotPath = "test-output/SparkReport" + scenarioName + "_" + ".png";
//            BufferedImage screenCapture = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
//            ImageIO.write(screenCapture, "png", new File(screenshotPath));
//            return screenshotPath;
//        }
//    }
}








