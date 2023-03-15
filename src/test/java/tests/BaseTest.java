package tests;

import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import org.testng.IDynamicGraph;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import request.RequestFactory;
import utils.ConfigRead;
import utils.ExtentReportUtils;

import java.util.Properties;

public class BaseTest {
    String configFilename;
    Properties configProperties;
    RequestFactory requestFactory;
    String currentWorkingDirectory;
    String htmlReportFilename;
    ExtentReportUtils extentReport;
    @BeforeSuite
    public void preSetup() throws Exception{
        currentWorkingDirectory = System.getProperty("user.dir");
        configFilename = currentWorkingDirectory + "/src/test/resources/config/config.properies";
        configProperties = ConfigRead.readConfigProperties(configFilename);
        htmlReportFilename = currentWorkingDirectory + "/src/test/resources/reports/htmlReport.html";
        extentReport = new ExtentReportUtils(htmlReportFilename);
    }
    @BeforeClass
    public void setup() {
        extentReport.createTestcase("Setup: Update all configrations");
        // RestAssured.baseURI = "http://localhost";
        RestAssured.baseURI = configProperties.getProperty("baseUrl");
        // RestAssured.port = 3030;
        RestAssured.port = Integer.parseInt(configProperties.getProperty("portNumber"));
        extentReport.addLog(String.valueOf(Status.INFO), "Base Url - " + configProperties.getProperty("baseUrl"));
        extentReport.addLog(String.valueOf(Status.INFO), "Base port - " + configProperties.getProperty("portNumber"));
        requestFactory = new RequestFactory();
    }
    @AfterMethod
    public void postTestCheck(ITestResult result){
        if (result.getStatus() == ITestResult.SUCCESS) {
            extentReport.addLog(Status.PASS, "All test step passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            extentReport.addLog(Status.FAIL, "One or more test step failed");
        } else {
            extentReport.addLog(Status.SKIP, "One or more test step skipped");
    }

    @AfterClass
    public void cleanup() {
        RestAssured.reset();
        extentReport.closeReports();
    }
}
