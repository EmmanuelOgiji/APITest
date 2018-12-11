import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class APITest {
    @Test
    public static void Test(){

        RestAssured.baseURI = "https://food2fork.com";

        given().
                param("key","e69f7e431a76291c119787efbe106e6d").
                param("q","pasta").
                header("Content-type","application/json").
                header("Accept","application/json").
                when().
                get("/api/search").
                then().
                assertThat().statusCode(200).contentType(ContentType.HTML);

    }

//    Properties prop = new Properties();
//
//    @BeforeTest
//    public void getEnvironment(){
//        try{
//            FileInputStream fis = new FileInputStream("src/Files/env.properties");
//            prop.load(fis);
//        }
//        catch(FileNotFoundException e){
//            System.out.println("File not found");
//        }
//        catch(IOException e) {
//            System.out.println("IO Exception");
//        }
//
//    }
//
//    @Test
//    public void getServer(){
//
//        RestAssured.baseURI = prop.getProperty("HOST");
//
//        Response res = given().auth().basic(prop.getProperty("Username"),prop.getProperty("Password")).
//                when().
//                get("/app/rest/server").
//                then().
//                assertThat().statusCode(200).extract().response();
//
//        String output = res.asString();
//        System.out.println(output);
//
//    }
//
//    @Test
//    public void getProjects(){
//
//        RestAssured.baseURI = prop.getProperty("HOST");
//
//        Response res = given().auth().basic(prop.getProperty("Username"),prop.getProperty("Password")).
//                when().
//                get("/app/rest/projects").
//                then().
//                assertThat().statusCode(200).extract().response();
//
//        String output = res.asString();
//        System.out.println(output);
//
//    }
}
