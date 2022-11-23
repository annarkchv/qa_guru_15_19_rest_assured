import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class SingleUserTest extends TestBase {

    @Test
    void singleUserInfo() {
        get(singleUser)
                .then()
                .log().body()
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void createUser() {
        String data = "{ \"name\": \"Jane Doe\", \"job\": \"QA Engineer\" }";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post(createUser)
                .then()
                .log().status()
                .log().body()
                .body("name", is("Jane Doe"))
                .body("job", is("QA Engineer"))
                .statusCode(201);
    }

    @Test
    void updateUser() {
        String data = "{ \"name\": \"Jane Doe\", \"job\": \"QA Engineer\" }";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .put(singleUser)
                .then()
                .log().status()
                .log().body()
                .body("name", is("Jane Doe"))
                .body("job", is("QA Engineer"))
                .statusCode(200);
    }

    @Test
    void deleteUser() {
        delete(singleUser)
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
