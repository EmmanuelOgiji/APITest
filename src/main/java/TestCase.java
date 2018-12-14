import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestCase {
    static Properties prop = new Properties();
    private static Logger log = LogManager.getLogger(TestCase.class.getName());

    public static void getEnvironment(){

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
}
