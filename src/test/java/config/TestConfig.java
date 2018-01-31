package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class TestConfig {

    public static RequestSpecification videoGameReqSpec;
    public static RequestSpecification footballReqSpec;
    public static ResponseSpecification resSpec;

    @BeforeClass
    public static void setUp() {
        //RestAssured.proxy("localhost", 8888);

        videoGameReqSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(8080).
                setBasePath("/app/").
                addHeader("Content-Type", "application/json").
                addHeader("Accept", "application/json").
                build();

        footballReqSpec = new RequestSpecBuilder().
                setBaseUri("http://www.football-data.org").
                setBasePath("/v1/").
                addHeader("X-Auth-Token", "").
                addHeader("X-Response-Control", "minified").
                build();

        resSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectResponseTime(lessThan(3000L)).
                build();

        RestAssured.requestSpecification = footballReqSpec;

        RestAssured.responseSpecification = resSpec;
    }
}
