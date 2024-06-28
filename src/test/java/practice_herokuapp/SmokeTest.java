package practice_herokuapp;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.HerOkuAppGetResponsePojo;
import pojos.HerokuAppBookingDatesPojo;
import pojos.HerokuAppPostResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmokeTest extends HerOkuAppBaseUrl {

    /*
   https://restful-booker.herokuapp.com/booking
And
   {
       "firstname" : "Jim",
       "lastname" : "Brown",
       "totalprice" : 111,
       "depositpaid" : true,
       "bookingdates" : {
           "checkin" : "2018-01-01",
           "checkout" : "2019-01-01"
       },
       "additionalneeds" : "Breakfast"
   }Given
When
   Send post request
Then
   Status code is 200
And
   Body:
    {
       "bookingid": 1,
       "booking": {
           "firstname": "Jim",
           "lastname": "Brown",
           "totalprice": 111,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2018-01-01",
               "checkout": "2019-01-01"
           },
           "additionalneeds": "Breakfast"
       }
   }
*/

    @Test
    public void createBooking() {
        //set the url
        spec.pathParam("first", "booking");

        //set the expected data / payload
        HerokuAppBookingDatesPojo bookingDatesPojo = new HerokuAppBookingDatesPojo("2018-01-01","2019-01-01");
        HerOkuAppGetResponsePojo expectedData = new HerOkuAppGetResponsePojo("Jim","Brown",111,true,bookingDatesPojo,"Breakfast");

        //send request get response
        Response response = given(spec).body(expectedData).when().post("{first}");

        //do assertion
        HerokuAppPostResponsePojo actualData = response.as(HerokuAppPostResponsePojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

    }
}
