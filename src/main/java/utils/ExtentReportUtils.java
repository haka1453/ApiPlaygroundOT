package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportUtils {
    ExtentHtmlReporter htmlReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;
    public ExtentReportUtils(String filename) {
        htmlReporter = new ExtentHtmlReporter(filename);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }
    public void createTestcase(String testcaseName) {
        extentTest = extentReports.createTest(testcaseName);
    }
    public void addLog(String status, String comments) {
        // extentTest.log(status, comments);
        extentTest.log(Status.valueOf(status), comments);
    }
    public void closeReports(){
        extentReports.flush();
    }
}
