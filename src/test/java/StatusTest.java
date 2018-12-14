import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.apache.logging.log4j.*;

import static io.restassured.RestAssured.given;

public class StatusTest extends TestCase {

    private static Logger log = LogManager.getLogger(StatusTest.class.getName());

    @BeforeSuite
    @BeforeTest
    public void setup(){
        TestCase.getEnvironment();
    }


    @Test
    public void getServer(){
        log.info("getServer is running");
        RestAssured.baseURI = prop.getProperty("HOST");
        log.info("HOST is " + RestAssured.baseURI);
        Response res = given().auth().basic(prop.getProperty("Username"),prop.getProperty("Password")).
                when().
                get(Resources.getServer()).
                then().
                assertThat().statusCode(200).extract().response();

        String output = res.asString();
        log.info(output);


    }

    @Test
    public void getProjects(){
        log.info("getProjects is running");
        RestAssured.baseURI = prop.getProperty("HOST");
        log.info(RestAssured.baseURI);
        Response res = given().auth().basic(prop.getProperty("Username"),prop.getProperty("Password")).
                when().
                get(Resources.getProject()).
                then().
                assertThat().statusCode(200).extract().response();

        String output = res.asString();
        log.info(output);

    }
}
