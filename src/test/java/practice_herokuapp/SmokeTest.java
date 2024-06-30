package practice_herokuapp;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pojos.HerOkuAppBookingDatesPojo;
import pojos.HerOkuAppGetResponesPojo;
import pojos.HerOkuAppPostResponsePojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Junit 5 te test methodlarinin belirli bir sira ile calistirilmasini saglamak icin kullanilir
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    static int bookingId;

    @Order(1)
    @Test
    public void createBooking() {
        //set the url
        spec.pathParam("first", "booking");

        //set the expected data / payload
        HerOkuAppBookingDatesPojo bookingDatesPojo = new HerOkuAppBookingDatesPojo("2018-01-01", "2019-01-01");
        HerOkuAppGetResponesPojo expectedData = new HerOkuAppGetResponesPojo("Jim", "Brown", 111, true, bookingDatesPojo, "Breakfast");

        //send request get response
        Response response = given(spec).body(expectedData).when().post("{first}");
        response.prettyPrint();

        //do assertion
        HerOkuAppPostResponsePojo actualData = response.as(HerOkuAppPostResponsePojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        //1 yol
//      int bookingId =  response.jsonPath().getInt("bookingid");
//      System.out.println("bookingId = " + bookingId);

        //2.yol tavsiye edilir
        bookingId = actualData.getBookingid();
        System.out.println("bookingId = " + bookingId);


    }


/*
    Given
         https://restful-booker.herokuapp.com/booking/:id
    When
         Send get request
    Then
         Status code is 200
    And
    Body: {
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
     */

    @Order(2)
    @Test
    public void getBooking() {
        //set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //set the expected data
        HerOkuAppBookingDatesPojo bookingDatesPojo = new HerOkuAppBookingDatesPojo("2018-01-01", "2019-01-01");
        HerOkuAppGetResponesPojo expectedData = new HerOkuAppGetResponesPojo("Jim", "Brown", 111, true, bookingDatesPojo, "Breakfast");

        //send request and get response
        Response response = given(spec).when().get("{first}/{second}");

        //do assertion
        HerOkuAppGetResponesPojo actualData = response.as(HerOkuAppGetResponesPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

    }



      /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
        "firstname" : "Ali",
        "lastname" : "Can",
        "totalprice" : 100,
        "depositpaid" : true,
        "bookingdates" : {
            "checkin" : "2018-01-01",
            "checkout" : "2019-01-01"
        },
        "additionalneeds" : "Dinner"
        }
    When
        Send put request
    Then
        Status code is 200
    And
        Body:
        {
        "firstname" : "Ali",
        "lastname" : "Can",
        "totalprice" : 100,
        "depositpaid" : true,
        "bookingdates" : {
            "checkin" : "2018-01-01",
            "checkout" : "2019-01-01"
        },
        "additionalneeds" : "Dinner"
        }
     */

    @Order(3)
    @Test
    public void updateBooking() {

        //set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //set the expected data / payload
        HerOkuAppBookingDatesPojo bookingDatesPojo = new HerOkuAppBookingDatesPojo("2018-01-01", "2019-01-01");
        HerOkuAppGetResponesPojo expectedData = new HerOkuAppGetResponesPojo("Ali", "Can", 100, true, bookingDatesPojo, "Dinner");

        //send request get response
        Response response = given(spec).body(expectedData).when().put("{first}/{second}");

        //do assertion

        HerOkuAppGetResponesPojo actualData = response.as(HerOkuAppGetResponesPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

    }

     /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {

        "firstname" : "Mehmet",
        "lastname" : "Can"
        }
    When
        Send patch request
    Then
        Status code is 200
    And
        Body:
        {
        "firstname" : "Mehmet",
        "lastname" : "Can",
        "totalprice" : 100,
        "depositpaid" : true,
        "bookingdates" : {
            "checkin" : "2018-01-01",
            "checkout" : "2019-01-01"
        },
        "additionalneeds" : "Dinner"
        }
     */

    @Order(4)
    @Test
    public void partiallyUpdateBooking() {
        //set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //set the payload / expected data
        Map<String, String> payload = new HashMap<>();
        payload.put("firstname", "Mehmet");
        payload.put("lastname", "Can");


        //send request get response
        Response response = given(spec).body(payload).when().patch("{first}/{second}");

        //do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(payload.get("firstname"), actualData.get("firstname"));
        assertEquals(payload.get("lastname"), actualData.get("lastname"));

    }

     /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Send delete request
    Then
        Status code is 201
    And
        Body should be :

         "Created"

     */

    @Order(5)
    @Test
    public void deleteBooking() {

        spec.pathParams("first", "booking", "second", bookingId);
        String expectedData = "Created";
        Response response = given(spec).when().delete("{first}/{second}");
        assertEquals(201, response.statusCode());
        String actualData = response.asString();
        assertEquals(expectedData, actualData);

    }

         /*
    Given
       https://restful-booker.herokuapp.com/booking/:id
    When
       Send get request
    Then
       Status code is 404
    And
       Body is "Not Found"
    */

    @Order(6)
    @Test
    public void getNegativeScenario() {

        //set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //set the expected data
        String expectedData = "Not Found";

        //send request get response
        Response response = given(spec).when().get("{first}/{second}");

        //do assertion
        assertEquals(404, response.statusCode());
        assertEquals(expectedData, response.asString());

    }
}
