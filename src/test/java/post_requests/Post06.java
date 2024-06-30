package post_requests;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.HerOkuAppGetResponesPojo;
import pojos.HerOkuAppBookingDatesPojo;
import pojos.HerOkuAppPostResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Post06 extends HerOkuAppBaseUrl {
     /*
        Given
          1)  https://restful-booker.herokuapp.com/booking
          2) {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
              }
        When
            I send POST Request to the URL
        Then
            Status code is 200
        And
            Response body is like
                 {
                    "bookingid": 16,
                    "booking" :{
                        "firstname": "Ali",
                        "lastname": "Can",
                        "totalprice": 999,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2021-09-21",
                            "checkout": "2021-12-21"
                        },
                        "additionalneeds": "Breakfast"
                     }
                  }
     */

    @Test
    public void test01() {
        //set the url
        spec.pathParam("first","booking");

        //set the expected data / payload
        HerOkuAppBookingDatesPojo bookingDatesPojo = new HerOkuAppBookingDatesPojo("2021-09-21","2021-12-21");
        HerOkuAppGetResponesPojo payload = new HerOkuAppGetResponesPojo("Ali","Can",999,true,bookingDatesPojo,"Breakfast");

        //send request get response
        Response response = given(spec).body(payload).when().post("{first}");

        //do assertion
        HerOkuAppPostResponsePojo actualData=  response.as(HerOkuAppPostResponsePojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(payload.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payload.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payload.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payload.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(payload.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(payload.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payload.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

    }
}
