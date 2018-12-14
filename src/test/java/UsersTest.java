import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class UsersTest extends TestCase {

    private static Logger log = LogManager.getLogger(UsersTest.class.getName());
    String id;

    @BeforeSuite
    @BeforeTest
    public void setup(){
        TestCase.getEnvironment();
    }

    @Test
    public void createNewUser(){
        log.info("createNewUser is running");
        RestAssured.baseURI = prop.getProperty("HOST");
        log.info(RestAssured.baseURI);
        Response res = given().auth().basic(prop.getProperty("Username"),prop.getProperty("Password")).
                body(Resources.postNewUserBody()).contentType(ContentType.JSON).header("Origin",prop.getProperty("HOST")).
                when().
                post(Resources.postNewUser()).
                then().
                extract().response();

        String output = res.asString();
        log.info(output);
        XmlPath xml =  new XmlPath(output);
        id = xml.get("user.@id").toString();
        log.info("id= "+id);

    }

    @Test
    public void deleteNewUser(){
        log.info("deleteNewUser is running");
        RestAssured.baseURI = prop.getProperty("HOST");
        log.info(RestAssured.baseURI);
        Response res1 = given().auth().basic(prop.getProperty("Username"),prop.getProperty("Password")).
                header("Origin",prop.getProperty("HOST")).
                pathParam("id",id).
                when().
                delete(Resources.postNewUser()+"/id:{id}").
                then().
                extract().response();

        String output1 = res1.asString();
        log.info(output1);
    }
}
