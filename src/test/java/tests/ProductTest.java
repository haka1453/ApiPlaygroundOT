package tests;

import io.restassured.RestAssured;
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
    public void verifyGetRequest() {
        requestFactory.getAllProducts().then().log().all().statusCode(200);
    }

    @Test
    public void verifyAddProduct(String requestPayload) {
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