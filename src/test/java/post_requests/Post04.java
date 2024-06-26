package post_requests;
import baseUrl.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Post04 extends JsonPlaceHolderBaseUrl {

      /*Given            1) https://jsonplaceholder.typicode.com/todos
                         2) {"userId": 55,"title": "Tidy your room","completed": false}
        Whe              I send POST Request to the Url
        Then             Status code is 201
        And              response body is like
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }           */

    @Test
    public void test01() throws JsonProcessingException {
        //set the url
        spec.pathParam("first","todos");

        //set the expected data
        /*ObjectMapper kullanarak reeadvalue methodu birinci parametreyi String olarak alir ve ikinci parametrede bizim
        belirttigimiz data type (HashMap) ine dönüstürür.==> Böylece datalari manual olarak eklemek zorunda kalmayiz  */

        String json ="{\"userId\": 55,\"title\": \"Tidy your room\",\"completed\": false}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> payload = objectMapper.readValue(json, HashMap.class);
        System.out.println("payload = " + payload);

        //send request get response
        Response response = given(spec)
                .body(payload)
                .when()
                .post("{first}");

        //do assertion
        Map<String,Object> actualData = response.as(HashMap.class);

        //ikinci yol uzun yol
        // Map<String,Object> actualData2 =   objectMapper.readValue( response.asString(), HashMap.class);

        assertEquals(payload.get("userId"),actualData.get("userId"));
        assertEquals(payload.get("title"),actualData.get("title"));
        assertEquals(payload.get("completed"),actualData.get("completed"));

    }
}
