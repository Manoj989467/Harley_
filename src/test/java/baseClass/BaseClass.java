package baseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class BaseClass {

    public RequestSpecification reqspec;
     public Response response;

    public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("C:\\Users\\Welcome\\eclipse-workspace\\Harley_Project\\src\\test\\resources\\PropertyFile\\config.properties"));
        Object object = properties.get(key);
        return (String) object;

    }

    public static String getPropertyPath() {

        return System.getProperty("user.dir");
    }

    public void addHeaders(Headers headers) {
        reqspec = RestAssured.given().headers(headers);

    }

    public void addHeader(String key, String value) {

        reqspec = RestAssured.given().header(key, value);

    }

    public void addReqBody(Object body) {

        reqspec = reqspec.header("Content-Type", "application/json").body(body);
    }

    public void addReqBody(String body) {

        reqspec = reqspec.body(body);
    }

    public Response addReqType(String Type, String endpoint) {

        switch (Type) {

            case "GET": {
                response = reqspec.get(endpoint);
                break;
            }
            case "POST": {
                response = reqspec.log().all().post(endpoint);
                break;
            }
            case "PUT": {
                response = reqspec.put(endpoint);
                break;
            }
            case "PATCH": {
                response = reqspec.patch(endpoint);
                break;
            }
            case "DELETE": {
                response = reqspec.delete(endpoint);
                break;
            }

        }
        return response;
    }

    public int getResponseCode() {
        return response.getStatusCode();

    }

    public ResponseBody getbody() {

        return response.getBody();
    }

    public String getResBodyAsString() {

        return response.getBody().asString();
    }

    public String getResBodyAsPrettyString() {

        return response.getBody().asPrettyString();
    }

    public RequestSpecification addBasicAuth(String userid, String Password) {

        return reqspec.auth().preemptive().basic(userid, Password);
    }
}
