package get_requests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class RequestResponse {

    /*1) Manual API testleri icin Postman kullanacağız
      2) Otomasyon testleri icin Rest Assured Library kullanacagız
      3) Rest Assured,  Gherkin dilinden faydalanarak hazir methodlar olusturmustur
        a) Given    : PreConditions (ön kosullar)
        b) When     : Actions get(),post()...
        c) Then     : Assertions (doğrulamalar)
        d) And      : Coklu durumlari baglarken okunurluk acisindan kullanilabilir

      4) API Otomasyon testlerini yazarken asagidaki adimlari izleyebilirsiniz
        a) Set the url  : API'in urlini ayarlayin
        b) Set the expected data:   Beklenen datayi ayarla
        c) Send the request and get the response : Request gönderilir ve response alinir
        d) Do assertion : Responstan dogrulamalar yapariz               */

    /**
     * Bu bir test methodur.(Test methodunun üzerinde Slaj çift yıldız enter yaptığında yazdığın açıklama test methodu
     * üzerine geldiğin zaman görünür)
     */

    @Test
    public void test01() {

        //1) Set the url
        String url = "https://petstore.swagger.io/v2/pet/5";


        //2) Set the expected data / payload
        //3) Send request get response


        Response response =  given().when().get(url);
        response.print();
        response.prettyPrint();

         /*1) print(); methodu response ta bulunan body kısmını console a yazdirmaya yarar
           2) prettyPrint(); methodu response ta bulunan body kısmını console a daha okunakli bir formatta yazdirmaya yarar   */

        //Response icindeki status code nasil yazdirilr?
        System.out.println("Statust code ==> " + response.statusCode());//200

        //Response icindeki Content-Type nasil yazdirilr?
        System.out.println("Content Type ==>" + response.contentType());//application/json

        // Response icindeki status line nasil yazdirilr?
        System.out.println("Status line ==> " + response.statusLine());//HTTP/1.1 200 OK



        //4) Do assertion

    }
}
