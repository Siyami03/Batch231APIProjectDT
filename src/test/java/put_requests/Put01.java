package put_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl{

      /*Given            1) https://jsonplaceholder.typicode.com/todos/198
                         2) {
                             "userId": 21,
                             "title": "Wash the dishes",
                             "completed": false
                             }
        When            Kullanıcı URL'e bir PUT request gönderir
        Then            Status code 200 olmalı
            Response şu şekilde olmalı:
                            {
                            "userId": 21,
                            "title": "Wash the dishes",
                            "completed": false
                            "id": 198
                            }             */

    @Test
    public void test01() {

        //set the url
        spec.pathParams("first","todos","second",198);

        //set the expected data / payload
        Map<String,Object> payload = new HashMap<>();
        payload.put("userId",21);
        payload.put("title","Wash the dishes");
        payload.put("completed",false);

        //send request get response
        Response response = given(spec)
                .body(payload)
                .when()
                .put("{first}/{second}");

        //do assertion

        Map<String, Object> actualData = response.as(HashMap.class);//De-serialization

        //response ta id eklendigi icin payloadu güncelliyoruz
        //set the expected data
        payload.put("id",198);

        assertEquals(200,response.statusCode());
        assertEquals(payload.get("userId"),actualData.get("userId"));
        assertEquals(payload.get("title"),actualData.get("title"));
        assertEquals(payload.get("completed"),actualData.get("completed"));
        assertEquals(payload.get("id"),actualData.get("id"));

    }
}
