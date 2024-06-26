package get_requests;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get10 extends HerOkuAppBaseUrl {

/*Given     https://restful-booker.herokuapp.com/booking/91
  When      I send GET Request to the url
  Then      Response body should be like that;
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
     }                            */

    @Test
    public void test1() {

        //set the url
        spec.pathParams("first","booking","second",2322);

        //set the expected data
        //önce inner map olusturulur
        Map<String,String> bookingDatesData = new HashMap<>();
        bookingDatesData.put("checkin","2018-01-01");
        bookingDatesData.put("checkout","2019-01-01");

        //outer mapi yani expected datayi olusturuyoruz
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname","John");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingDatesData);
        expectedData.put("additionalneeds","Breakfast");
        System.out.println("expectedData = " + expectedData);

        //send request get response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));
        assertEquals(expectedData.get("bookingdates"),actualData.get("bookingdates"));

        /*Ic ice json datalarda doğrulama yaparken datalari Map e dönüsturmeliyiz.
        Assertions icindeki actual data Object data tipinde oldugu icin get methodunu kullanamayiz
        bunun icin Object data tipini Map data tipine cevirmemiz gerekiyor(Type casting) Ama bu tarz nested durumlarda
        jsonpath kullanarak bir json body icindeki dataya rahatlikla erisebiliriz   */

        Object object =     ((Map)    actualData.get("bookingdates")  ).get("checkout")  ;
        System.out.println("object = " + object);

        //Map ile dogrulama => TAVSİYE EDİLMEZ
        assertEquals(bookingDatesData.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin")  );
        assertEquals(bookingDatesData.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout")  );

        //Json Path ile dogrulama
        JsonPath jsonPath = response.jsonPath();

        assertEquals(bookingDatesData.get("checkin"),jsonPath.getString("bookingdates.checkin") );
        assertEquals(bookingDatesData.get("checkout"),jsonPath.getString("bookingdates.checkout") );

        Object obj = "Ali Can";
        boolean isContain = ((String) obj).contains("Can");

    }
}
