package get_requests;
import baseUrl.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get05 extends PetStoreBaseUrl {


  /*Given
        https://petstore.swagger.io/v2/pet/findByStatus?status=available
    When
        Kullanıcı URL'e bir GET request gönderir
    Then
        HTTP Status Code 200 olmalı
    And
        Content Type "application/json" olmalı
    And
        Listede id değeri 313 olan bir eleman olmalı
    And
        Listede name değeri "Tekir" olan bir eleman olmalı
    And
        Listede name değerleri "Tekir", "doggie", "fish" olan elemanlar olmalı
    And
        Listede en az 200 tane eleman olmalı
    And
        Listede 500'den az eleman olmalı
    And
        Listenin ilk elemanının category - id değeri 0 olmalı
    And
        Listenin ilk elemanının photoUrls değeri "string" olmalı
    And
        Listenin ilk elemanının tags - id değeri 0 olmalı         */

    @Test
    public void test01() {

        //set the url
        spec.pathParams("first","pet","second","findByStatus")
                .queryParam("status","available");

        //set the expected data / payload
        //send request get response
        Response response = given(spec)
                .when()
                .get("{first}/{second}");

        response.prettyPrint();

        //do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", hasItem(313))
                .body("name",hasItem("Tekir"))
                .body("name",hasItems("Tekir","doggie","fish"))
                .body("id",hasSize(greaterThan(200)))
                .body("id",hasSize(lessThan(500)))
                .body("[0].category.id",equalTo(0))
                .body("[0].photoUrls[0]",equalTo("string"))
                .body("[0].tags[0].id",equalTo(0)) ;
    }
}
