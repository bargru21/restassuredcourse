import config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class FootballTests extends TestConfig {

    @Test
    public void getAllCompetitionsOneSeason() {
        given().spec(footballReqSpec).queryParam("season", 2016).
                when().get("competitions/").
                then().spec(resSpec).log().all();
    }

    @Test
    public void getTeamCountOneCompetition() {
        given().spec(footballReqSpec).
                when().get("competitions/426/teams").
                then().spec(resSpec).body("count", equalTo(20)).log().all();
    }

    @Test
    public void getFirstTeamName() {
        given().spec(footballReqSpec).
                when().get("competitions/426/teams").
                then().spec(resSpec).body("teams.name[0]", equalTo("Hull City FC"));
    }

    @Test
    public void getAllTeamsNames() {
        Response res = given().spec(footballReqSpec).
                when().get("competitions/426/teams").
                then().contentType(ContentType.JSON).extract().response();

        List<String> allTeamsNames = res.path("teams.name");

        for (String name : allTeamsNames) {
            System.out.println(name);
        }
    }
}
