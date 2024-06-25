package get_requests;
import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Get09 extends JsonPlaceHolderBaseUrl {

  /*Given       https://jsonplaceholder.typicode.com/todos
    When        Kullanıcı URL'e bir GET request gönderir
    Then        Status code 200 olmalı
        "Id"leri 190 dan büyük olanları konsola yazdırın
        "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
        "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
        "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
        "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
        "delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın    */

    @Test
    public void test01() {
        //set the url
        spec.pathParam("first","todos");

        //set the expected data
        //send request and get response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        System.out.println("=================================");

        JsonPath jsonPath = response.jsonPath();

        /*List<Object> id = jsonPath.getList("id");
        System.out.println("id = " + id);
        System.out.println("=================================");

        List<Object> title = jsonPath.getList("title");
        System.out.println("title = " + title);
        System.out.println("=================================");

        List<Object> completed = jsonPath.getList("completed");
        System.out.println("completed = " + completed);*/

        //do assertion

        // Status code 200 olmalı
        assertEquals(200,response.statusCode());

        // "Id"leri 190 dan büyük olanları konsola yazdırın
        List<Object> jsonList = jsonPath.getList("findAll{it.id > 190}");
        System.out.println("jsonList = " + jsonList);

        // "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
        assertEquals(10,jsonList.size());

        // "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
        List<Object> userIdList = jsonPath.getList("findAll{it.id < 5}.userId");
        System.out.println("userIdList = " + userIdList);

        // "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
        assertEquals(4,userIdList.size());

        // "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
        List<Object> titleList = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("titleList = " + titleList);

        // "delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
        assertTrue(titleList.contains("delectus aut autem"));

    }
}
