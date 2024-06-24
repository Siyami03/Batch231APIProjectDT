package baseUrl;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import utilities.AuthenticateContactList;

public class JsonPlaceHolderBaseUrl {


      /*
    Bu classin amaci:
    ==> Her testten önce calisarak baseUrl ve content type ... gibi ortak request yapilandirmalarini yapmak
    ==> Testlerin daha bakimi kolay yapilabilir hale getirmektir
     */


    protected RequestSpecification spec;

    @BeforeEach
    public void setUp() {
        spec  = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")//Base Url i ayarladik
                .setContentType(ContentType.JSON)
                .build();
    }
}
