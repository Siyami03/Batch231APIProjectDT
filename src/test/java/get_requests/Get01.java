package get_requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

    /*
        Given
            https://petstore.swagger.io/v2/pet/313
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type "application/json" olmalı
        And
            Status Line "HTTP/1.1 200 OK" olmalı
    */

    @Test
    public void get01() {
        //1) Set the url

        //Birinci yol
        //String baseUrl ="https://petstore.swagger.io/v2";
        //String pathParameter="/pet/313";

        //ikinci yol
        RestAssured.baseURI="https://petstore.swagger.io/v2";
        RestAssured.basePath="/pet/313";

        //2) Set the expected data / payload
        //3) Send request get response

        Response response = given().when().get();
        response.prettyPrint();

        //4) Do assertion
        response
                .then().assertThat().statusCode(200)//status code 200 oldugunu dogrula
                .and().assertThat().contentType("application/json")//content tyepin application/json oldugunu doğrular
                .and().assertThat().statusLine("HTTP/1.1 200 OK");// Status line HTTP/1.1 200 OK oldugunu dogrular

    }

    //İkinci Yol
    @Test
    public void get02() {
        //1) Set the url
        RestAssured.baseURI="https://petstore.swagger.io/v2";
        RestAssured.basePath="/pet/313";

        //2) Set the expected data / payload
        //3) Send request get response

        Response response = given().when().get();
        response.prettyPrint();

        //4) Do assertion
        response.then()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 OK");

    }
}
