import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UsersTest {

    Properties prop = new Properties();
    private static Logger log = LogManager.getLogger(StatusTest.class.getName());
    String id;

    @BeforeSuite
    @BeforeTest
    public void getEnvironment(){
        try{
            FileInputStream fis = new FileInputStream("src/Files/env.properties");
            prop.load(fis);
        }
        catch(FileNotFoundException e){
            log.error("File not found");
        }
        catch(IOException e) {
            log.error("IO Exception");
        }

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
