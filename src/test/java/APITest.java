import io.restassured.RestAssured;
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
}
