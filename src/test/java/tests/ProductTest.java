package tests;

import com.aventstack.extentreports.Status;
import groovy.transform.ToString;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import request.RequestFactory;

import java.util.HashMap;
import java.util.Map;

public class ProductTest extends BaseTest {
    RequestFactory requestFactory;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        requestFactory = new RequestFactory();
    }

    @Test
    public void verifyGetProduct() {
        extentReport.createTestcase("Verify Get Product");
        // ValidatableResponse valResponse = requestFactory.getAllProducts().then();
        Response response = requestFactory.getAllProducts();
        // extentReport.addLog(Status.INFO, valResponse.log().all().toString());
        extentReport.addLog(String.valueOf(Status.INFO), response.asPrettyString());
        response.then().statusCode(200);
    }

    @Test
    public void verifyAddProduct(String requestPayload) {
        extentReport.createTestcase("Verify Add Product");
        requestPayload = "{\n" +
                "  \"name\": \"Samsung Mobile\",\n" +
                "  \"type\": \"Mobile\",\n" +
                "  \"price\": 100,\n" +
                "  \"shipping\": 10,\n" +
                "  \"upc\": \"string\",\n" +
                "  \"description\": \"string\",\n" +
                "  \"manufacturer\": \"string\",\n" +
                "  \"model\": \"string\",\n" +
                "  \"url\": \"string\",\n" +
                "  \"image\": \"string\"\n" +
                "}";
        requestFactory.addProducts(requestPayload).then().log().all().statusCode(201);
    }

    @Test
    public void verifyAddProductWithRequestPayloadAsMap() {
        extentReport.createTestcase("Verify Add Product With Request Payload As Map");
        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("type", "Mobile");
        requestPayload.put("price", 100);
        requestPayload.put("shipping", 10);
        requestPayload.put("upc", "string");
        requestPayload.put("description", "string");
        requestPayload.put("manufacturer", "string");
        requestPayload.put("model", "string");
        requestPayload.put("url", "string");
        requestPayload.put("image", "string");
        requestPayload.put("name", "string");

        requestFactory.addProducts(requestPayload).then().log().all().statusCode(201);
    }

}