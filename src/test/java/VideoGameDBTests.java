import config.EndPoints;
import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class VideoGameDBTests extends TestConfig {

    @Test
    public void getAllGames() {
        given().spec(videoGameReqSpec).
                when().get(EndPoints.VIDEOGAMES).
                then().spec(resSpec).log().body();
    }

    @Test
    public void createNewGameByJSON() {
        String newGameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"Pillars of Eternity\",\n" +
                "  \"releaseDate\": \"2018-01-26T09:57:39.290Z\",\n" +
                "  \"reviewScore\": 99,\n" +
                "  \"category\": \"RPG\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given().spec(videoGameReqSpec).body(newGameBodyJson).
                when().post(EndPoints.VIDEOGAMES).
                then().spec(resSpec).log().body();
    }

    @Test
    public void updateGame() {
        String updateGameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"Pillars of Eternity 2\",\n" +
                "  \"releaseDate\": \"2018-01-26T09:57:39.290Z\",\n" +
                "  \"reviewScore\": 79,\n" +
                "  \"category\": \"RPG\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given().spec(videoGameReqSpec).body(updateGameBodyJson).
                when().put("videogames/11").
                then().spec(resSpec).log().body();
    }

    @Test
    public void deleteGame() {
        given().spec(videoGameReqSpec).
                when().delete("videogames/11").
                then().spec(resSpec).log().body();
    }

    @Test
    public void getSingleGame() {
        given().spec(videoGameReqSpec).pathParam("videoGameId", 5).
                when().get(EndPoints.SINGLE_VIDEOGAME).
                then().spec(resSpec);
    }

    @Test
    public void testVideoGameSerializationByJSON() {
        VideoGame videoGame = new VideoGame("15", "FPS", "2013-09-09", "Far Cry 3", "Mature", "99");
        given().spec(videoGameReqSpec).body(videoGame).
                when().post(EndPoints.VIDEOGAMES).
                then().spec(resSpec).log().body();
    }

    @Test
    public void testVideoGameJsonSchema() {
        given().spec(videoGameReqSpec).pathParam("videoGameId", 5).
                when().get(EndPoints.SINGLE_VIDEOGAME).
                then().spec(resSpec).body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json")).log().all();
    }
}
