package post_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Post03 extends JsonPlaceHolderBaseUrl {

    /*  Given       1) https://jsonplaceholder.typicode.com/todos
                    2) {
                       "userId": 55,
                       "title": "Tidy your room",
                       "completed": false           }
        When           Kullanıcı URL'e bir POST request gönderir
        Then           Status code 201 olmalı
        And            Response şu şekilde olmalı:
                       {
                       "userId": 55,
                       "title": "Tidy your room",
                       "completed": false,
                       "id": 201            }     */

    @Test
    public void test01() {

        //set the url
        spec.pathParam("first","todos");

        //set the expected data / payload
        //String kullanmak bir yöntemdir
        //ama assertion icin bunu kesinlikle tavsiye etmiyoruz
        //         String payload ="{\n" +
        //                " \"userId\": 55,\n" +
        //                " \"title\": \"Tidy your room\",\n" +
        //                " \"completed\": false\n" +
        //                "}";
        //        System.out.println("payload = " + payload);

        //Java da json formatina en yakin bir object olusturmak icin Map tercih ettik
        Map<String , Object> payload = new HashMap<>();
        payload.put("userId",55);
        payload.put("title","Tidy your room");
        payload.put("completed",false);
        System.out.println("payload = " + payload);

        //send request get response
        Response response = given(spec)
                .body(payload)
                .when()
                .post("{first}");
        response.prettyPrint();

        /*Serialization =>
        Java datalaimizin Json datalarina dönüstürülmesi islemidir.

        De-Serialization=>
        Json datalarinin java datalirana dönüstürülmesi islemidir.

        Serializatin ve De-Serialization islemlerini pom.xml dosyasina ekledigimiz Jackson Databind gibi kutuphaneler
        otomatik olarak gerceklestirir. bizim extra birsey yapmamiza gerek kalmaz         */

        //do assertion
        JsonPath jsonPath = response.jsonPath();

        assertEquals(201, response.statusCode() );

        //Payloadu gönderdikten sonra gelen response tan id testi yapmak icin
        //payloada assertion amaciyla id bilgisini ekledik
        //set the expected data
        payload.put("id",201);

        assertEquals(payload.get("userId"),jsonPath.getInt("userId"));
        assertEquals(payload.get("title"),jsonPath.getString("title"));
        assertEquals(payload.get("completed"),jsonPath.getBoolean("completed"));
        assertEquals(payload.get("id"),jsonPath.getInt("id"));

    }

    @Test
    public void test02() {

        //set the url
        spec.pathParam("first","todos");

        //set the expected data / payload

        //Java da json formatina en yakin bir object olusturmak icin Map tercih ettik
        Map<String , Object> payload = new HashMap<>();
        payload.put("userId",55);
        payload.put("title","Tidy your room");
        payload.put("completed",false);
        System.out.println("payload = " + payload);

        //send request get response
        Response response = given(spec)
                .body(payload)
                .when()
                .post("{first}");
        response.prettyPrint();

        /*
        Serialization =>
        Java datalaimizin Json datalarina dönüstürülmesi islemidir.

        De-Serialization=>
        Json datalarinin java datalirana dönüstürülmesi islemidir.

        Serializatin ve De-Serialization islemlerini pom.xml dosyasina ekledigimiz Jackson Databind
        gibi kutuphaneler otomatik olarak gerceklestirir
        bizim extra birsey yapmamiza gerek kalmaz
         */

        //do assertion

        assertEquals(201, response.statusCode() );

        Map<String,Object> actualData =  response.as(HashMap.class);

        assertEquals(payload.get("userId"),actualData.get("userId"));
        assertEquals(payload.get("title"),actualData.get("title"));
        assertEquals(payload.get("completed"),actualData.get("completed"));

    }
}
