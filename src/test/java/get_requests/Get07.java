package get_requests;
import baseUrl.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class Get07 extends HerOkuAppBaseUrl {

    //JSONPATH

      /*Given
            https://restful-booker.herokuapp.com/booking/2229
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type should contain "application/json"
        And
            Response body should be like;
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
    public void jsonPath() {
        //1) Set the url
        spec.pathParams("first","booking","second",285);

        //2) Set the expected data / payload
        //3) Send request get response
        Response response =given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //response'un body kısmını jsonpath objectine donusturuyoruz,
        //bu islemi yaptigimizda sadece body kısmı jsonpath formatina dönüsür

        JsonPath jsonPath = response.jsonPath();

        String firstName =jsonPath.getString("firstname");
        System.out.println("firstName = " + firstName);

        Integer totalPrice = jsonPath.getInt("totalprice");
        System.out.println("totalPrice = " + totalPrice);

        boolean depositPaid =jsonPath.getBoolean("depositpaid");
        System.out.println("depositPaid = " + depositPaid);

        String additionalneeds = jsonPath.getString("additionalneeds");
        System.out.println("additionalneeds = " + additionalneeds);

        System.out.println("bookingDates ==> " + jsonPath.getJsonObject("bookingdates"));

        String checkin = jsonPath.getString("bookingdates.checkin");
        System.out.println("checkin = " + checkin);

        //4) Do assertion
        assertEquals("John",jsonPath.getString("firstname"));
        assertEquals("Smith",jsonPath.getString("lastname"));
        assertEquals(111,jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast",jsonPath.getString("additionalneeds"));

    }
}
