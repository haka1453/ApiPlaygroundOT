package tests;

import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import request.RequestFactory;

public class BaseTest {
    RequestFactory requestFactory;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        requestFactory = new RequestFactory();
    }
    @AfterClass
    public void cleanup() {
        RestAssured.reset();
    }
}
