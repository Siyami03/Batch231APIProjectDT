package get_requests;
import baseUrl.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get06 extends HerOkuAppBaseUrl {

      /*Given            https://restful-booker.herokuapp.com/booking/2193
        When             User send a GET request to the URL
        Then             HTTP Status Code should be 200
        And              Response content type should contain "application/json"
        And              Response body should be like;
        {
            "firstname": "John",
            "lastname": "Smith",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Breakfast"
        }     */

    @Test
    public void test01() {

        //1) Set the url
        spec.pathParams("first","booking","second",2193);

        //2) Set the expected data / payload
        //3) Send request get response
        //4) Do assertion
        given(spec)
                .when()
                .get("{first}/{second}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",equalTo("John"))
                .body("lastname",equalTo("Smith"))
                .body("totalprice",equalTo(111))
                .body("depositpaid",equalTo(true))
                .body("bookingdates.checkin",equalTo("2018-01-01"))
                .body("bookingdates.checkout",equalTo("2019-01-01"))
                .body("additionalneeds",equalTo("Breakfast"))
                .log().all();
    }

    @Test
    public void test02() {

        //1) Set the url
        spec.pathParams("first","booking","second",897);

        //2) Set the expected data / payload
        //3) Send request get response
        //4) Do assertion
        given(spec)
                .when()
                .get("{first}/{second}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(equalTo("{\"firstname\":\"John\",\"lastname\":\"Smith\",\"totalprice\":111,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"Breakfast\"}"))
                .log().all();
    }
}
