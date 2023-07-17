package com.Utilities;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporter {
	
	private ExtentReporter() {
        throw new IllegalStateException("Extent Reporter class instantiation is not allowed");
    }

    /**
     * Method to configure and get the ExtentReporter instance
     * 
     * @param testProperties - {@link TestProperties}
     * @return ExtentReports - Returns {@link ExtentReports} instance
     * @throws IOException - Throws {@link IOException}
     */
    public static ExtentReports getExtentReporter(TestProperties testProperties) throws IOException {
        ExtentSparkReporter reporter = new ExtentSparkReporter(testProperties.getProperty("extentReportPath"));
        reporter.loadXMLConfig("./src/main/resources/extent-report-config.xml");

        reporter.config().setCss("img.r-img { width: 30%; }");
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);

        String applicationURL = "<a href=\"" + testProperties.getProperty("url")
                + "\" target=\"_blank\">Wipit</a>";
        extentReports.setSystemInfo("Application", applicationURL);

        extentReports.setSystemInfo("OS", System.getProperties().getProperty("os.name"));
        extentReports.setSystemInfo("Browser", testProperties.getProperty("browser"));

//        if (Boolean.getBoolean(testProperties.getProperty("enableRecordVideo"))) {
//            String filePath = Paths.get(testProperties.getProperty("recordVideoDirectory")).toAbsolutePath()
//                    .toString();
//            String recordedVideoFilePath = "<a href=\"" + filePath
//                    + "\" target=\"_blank\"Open cart Demo Application</a>";
//            extentReports.setSystemInfo("Execution Recorded Video", recordedVideoFilePath);
//        }
        return extentReports;
    }

    /**
     * Method to add the log the step to extent report
     * 
     * @param extentTest - {@link ExtentTest}
     * @param status     - {@link Status}
     * @param message    - {@link String} log message
     */
    public static void extentLog(ExtentTest extentTest, Status status, String message) {
        extentTest.log(status, message);
        log(status, message);
    }

    /**
     * Method to add the log step with the screenshot to the extent report
     * 
     * @param extentTest - {@link ExtentTest}
     * @param status     - {@link Status}
     * @param message    - {@link String} log message
     * @param base64Path - {@link java.util.Base64} {@link String} of screenshot
     */
    public static void extentLogWithScreenshot(ExtentTest extentTest, Status status, String message, String base64Path) {
//        String imageElement = "<br/><img class='r-img' src='data:image/png;base64," + base64Path
//                + "' href='data:image/png;base64," + base64Path + "'data-featherlight='image'>";
    	String imageElement = String.format("<br/><img class='r-img' src='data:image/png;base64,%s' href='data:image/png;base64,%s' data-featherlight='image'>", base64Path, base64Path);
        extentTest.log(status, message + imageElement);
      //  extentTest.addScreenCaptureFromBase64String(base64Path);
        log(status, message);
    }

    /**
     * Method to log the message to console and log file.
     * It removes any HTML element in the message before printing logging
     * 
     * @param status  - {@link Status}
     * @param message - {@link String} log message
     */
    private static void log(Status status, String message) {
        message = message.replaceAll("\\<.*?\\>", "");
       // System.out.println("Messageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee--------"+message);
        Logger log = LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName().split("\\.")[1]+ "." + Thread.currentThread().getStackTrace()[3].getMethodName());
       // System.out.println("loggggggggggggggggggggggggggggggggggg--------"+log);
        Marker marker = MarkerManager.getMarker("ReportLog");
       // System.out.println("Markerrrrrrrrrrrrr------------------"+marker);
        switch (status) {
            case FAIL:
                log.warn(marker, message);
                break;
            case WARNING:
                log.warn(marker, message);
                break;
            case SKIP:
                log.warn(marker, message);
                break;
            case INFO:
                log.info(marker, message);
                break;
            default:
                log.debug(marker, message);
                break;
        }
    }

}
