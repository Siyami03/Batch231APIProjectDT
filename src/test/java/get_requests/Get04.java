package get_requests;

import baseUrl.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Get04 extends PetStoreBaseUrl {


      /*Given
            https://petstore.swagger.io/v2/pet/313
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type "application/json" olmalı   */

    @Test
    public void test01() {

        //set the url
        spec.pathParams("first","pet","second",313);

        //set the expected data / payload
        //send request and get response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
}
