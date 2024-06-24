package get_requests;
import baseUrl.ContactListBaseUrl;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Get08 extends ContactListBaseUrl {

  /*Given
        https://thinking-tester-contact-list.herokuapp.com/contacts
    When
        Kullanıcı URL'e bir GET request gönderir
    Then
        HTTP Status Code 200 olmalı
    And
        Content Type "application/json" olmalı       */

    @Test
    public void test01() {
        //set the url
        spec.pathParam("first","contacts");
        //set the expected data
        //send request get response
        //do assertion
        given(spec)
                .when()
                .get("{first}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all();
    }
}
