import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class APITest {

    Properties prop = new Properties();

    @BeforeTest
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
    public void getServer(){

        RestAssured.baseURI = prop.getProperty("HOST");
        System.out.println(RestAssured.baseURI);
        Response res = given().auth().basic(prop.getProperty("Username"),prop.getProperty("Password")).
                when().
                get(Resources.getServer()).
                then().
                assertThat().statusCode(200).extract().response();

        String output = res.asString();
        System.out.println(output);


    }

    @Test
    public void getProjects(){

        RestAssured.baseURI = prop.getProperty("HOST");
        System.out.println(RestAssured.baseURI);
        Response res = given().auth().basic(prop.getProperty("Username"),prop.getProperty("Password")).
                when().
                get(Resources.getProject()).
                then().
                assertThat().statusCode(200).extract().response();

        String output = res.asString();
        System.out.println(output);

    }

    @Test
    public void postThenDeleteNewUser(){

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
        String id = xml.get("user.@id").toString();
        System.out.println("id= "+id);

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
