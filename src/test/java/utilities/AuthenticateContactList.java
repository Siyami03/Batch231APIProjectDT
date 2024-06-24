package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthenticateContactList {

    public static String generateToken(){

        //Bu endpoint bir token olusturmak icin swaggerdan aldik
        String url ="https://thinking-tester-contact-list.herokuapp.com/users/login";

        //set the payload/ expected data
        String body="{\n" +
                "    \"email\": \"techpro@techpro.com\",\n" +
                "    \"password\": \"1234567\"\n" +
                "}";

        //send request get response
        Response response = given().body(body).contentType(ContentType.JSON).when().post(url);

        return response.jsonPath().getString("token");

    }

}
