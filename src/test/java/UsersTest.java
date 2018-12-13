import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
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
    String id;

    @BeforeSuite
    public void getEnvironment(){
        try{
            FileInputStream fis = new FileInputStream("src/Files/env.properties");
            prop.load(fis);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e) {
            System.out.println("IO Exception");
        }

    }

    @Test
    public void createNewUser(){
        System.out.println("createNewUser is running");
        RestAssured.baseURI = prop.getProperty("HOST");
        System.out.println(RestAssured.baseURI);
        Response res = given().auth().basic(prop.getProperty("Username"),prop.getProperty("Password")).
                body(Resources.postNewUserBody()).contentType(ContentType.JSON).header("Origin",prop.getProperty("HOST")).
                when().
                post(Resources.postNewUser()).
                then().
                extract().response();

        String output = res.asString();
        System.out.println(output);
        XmlPath xml =  new XmlPath(output);
        id = xml.get("user.@id").toString();
        System.out.println("id= "+id);

    }

    @Test
    public void deleteNewUser(){
        System.out.println("deleteNewUser is running");
        RestAssured.baseURI = prop.getProperty("HOST");
        System.out.println(RestAssured.baseURI);
        Response res1 = given().auth().basic(prop.getProperty("Username"),prop.getProperty("Password")).
                header("Origin",prop.getProperty("HOST")).
                when().
                delete(Resources.postNewUser()+"/id:"+id).
                then().
                extract().response();

        String output1 = res1.asString();
        System.out.println(output1);
    }
}
