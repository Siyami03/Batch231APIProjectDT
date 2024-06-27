package post_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.JsonPlaceHolderPayloadPojo;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Post05 extends JsonPlaceHolderBaseUrl {

  /*Given       1) https://jsonplaceholder.typicode.com/todos
                2)  {
                     "userId": 55,
                     "title": "Tidy your room",
                     "completed": false
                     }
    When        I send POST Request to the Url
    Then        Status code is 201
    And         response body is like
                    {
                        "userId": 55,
                        "title": "Tidy your room",
                        "completed": false,
                        "id": 201
                    }                */

    @Test
    public void test01() {
        //set the url
        spec.pathParam("first","todos");

        //set the expected data / payload
        JsonPlaceHolderPayloadPojo payload = new JsonPlaceHolderPayloadPojo(55,"Tidy your room",false);

        //send request get response
        Response response = given(spec).body(payload).when().post("{first}");//Serialization

        //do assertion
        JsonPlaceHolderPayloadPojo actualData =  response.as(JsonPlaceHolderPayloadPojo.class);//De-Serialization
        assertEquals(201,response.statusCode());
        assertEquals(payload.getUserId(),actualData.getUserId());
        assertEquals(payload.getTitle(),actualData.getTitle());
        assertEquals(payload.getCompleted(),actualData.getCompleted());
    }

}
