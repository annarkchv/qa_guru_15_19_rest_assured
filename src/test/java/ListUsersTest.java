import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.*;

public class ListUsersTest extends TestBase {

    @Test
    void listUsersTotal() {
        get(listUsers)
                .then()
                .log().body()
                .body("total", is(12));
    }

    @Test
    void listUsersStatus() {
        get(listUsers)
                .then() // проверка результата
                .log().status()
                .statusCode(200);
    }

    @Test
    void listUsersIdValues() {
        get(listUsers)
                .then()
                .body("data.id", hasItems(7, 8, 9, 10, 11, 12));
    }

    @Test
    void listUsersHasSpecificId() {
        get(listUsers)
                .then()
                .body("data.id", hasItem(12));
    }
}
