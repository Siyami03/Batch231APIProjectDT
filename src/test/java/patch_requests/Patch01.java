package patch_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {

  /*Given        1) https://jsonplaceholder.typicode.com/todos/198
                 2) {
                     "title": "Wash the dishes"
                     }
    When      I send PATCH Request to the Url
    Then      Status code is 200
    And       response body is like
              {
                "userId": 10,
                "title": "Wash the dishes",
                "completed": true,
                "id": 198
               }                  */

    @Test
    public void test01() {

        //set the url
        spec.pathParams("first","todos","second",198);

        //set the expected data / payload
        //Degistirmek istedigimiz kısmı eklememiz yeterlidir
        //cunku partially update (kısmi güncelleme) yapiyoruz
        Map<String,Object> payload = new HashMap<>();
        payload.put("title","Wash the dishes");
        //  payload.put("completed",true);

        //send request get response
        Response response = given(spec)
                .body(payload)
                .when()
                .patch("{first}/{second}");

        //do assertion
        Map<String,Object> actualData= response.as(HashMap.class);

        assertEquals(200,response.statusCode());

        //payload a assertion icin gerekli olan datalari ekleyerek expected data olarak kullanabiliriz
        payload.put("userId",10);
        payload.put("completed",true);
        payload.put("id",198);

        assertEquals(payload.get("userId"),actualData.get("userId"));
        assertEquals(payload.get("title"),actualData.get("title"));
        assertEquals(payload.get("completed"),actualData.get("completed"));
        assertEquals(payload.get("id"),actualData.get("id"));

    }
}
