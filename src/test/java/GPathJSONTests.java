import config.TestConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJSONTests extends TestConfig {

    @Test
    public void extractMapOfElementsWithFind() {
        Response response = get("competitions/426/teams");
        Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'Leicester City FC' }");

        System.out.println(allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWithFind() {
        Response response = get("teams/66/players");
        String singlePlayer = response.path("players.find { it.jerseyNumber == 20 }.name");
        System.out.println(singlePlayer);
    }

    @Test
    public void extractListOfValuesWithFindAll() {
        Response response = get("teams/66/players");
        List<String> allPlayers = response.path("players.findAll { it.jerseyNumber > 10 }.name");
        System.out.println(allPlayers);
    }

    @Test
    public void extractMultiplePlayers() {
        String position = "Centre-Back";
        String nationality = "England";

        Response response = get("teams/66/players");
        List<Map<String, ?>> allPlayersCertainNation = response.path("players.findAll {it.position == '%s'}.findAll {it.nationality == '%s'}", position, nationality);

        System.out.println(allPlayersCertainNation);
    }
}
