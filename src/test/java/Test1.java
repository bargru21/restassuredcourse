import config.EndPoints;
import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Test1 extends TestConfig {

    @Test
    public void getVideoGame() {
        given().spec(videoGameReqSpec).
                when().get("videogames/1").
                then().spec(resSpec).log().all();
    }

    @Test
    public void getTeam() {
        given().spec(footballReqSpec).
                when().get("teams/66").
                then().spec(resSpec).log().all();
    }

    @Test
    public void getAllVideoGames() {
        given().spec(videoGameReqSpec).
                when().get(EndPoints.VIDEOGAMES).
                then().spec(resSpec).log().all();
    }
}
