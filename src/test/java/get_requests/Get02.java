package get_requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get02 {

      /*Given
            https://petstore.swagger.io/v2/pet/0
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status code 404 olmalı
        And
            Status Line "HTTP/1.1 404 Not Found" olmalı
        And
            Response body "Pet not found" içermeli
        And
            Response body "TechProEd" içermemeli
        And
            Server değeri "Jetty(9.2.9.v20150224)" olmalı    */

    @Test
    public void test01() {

        //set the url
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "/pet/0";

        //set the expected data / payload
        //send request get response
        Response response = given().when().get();
        response.prettyPrint();

        /*400 kodlu response larda HttpResponseException almamak icin bu kodu pom.xml dosyamizdaki properties
        tagi icinde yazmaliyiz   ==>     <argLine>-Duser.language=en</argLine>         */

        //do assertion
        response
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .body(containsString("Pet not found"))
                .body(not(containsString("TechProEd")))
                .header("Server","Jetty(9.2.9.v20150224)");

    }
}
