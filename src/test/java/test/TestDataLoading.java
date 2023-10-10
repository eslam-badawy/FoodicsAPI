package test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataLoading {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/java/resources/test-config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseURI() {
        return properties.getProperty("baseURI");
    }

    public static String getEmail() {
        return properties.getProperty("email");
    }

    public static String getWrongEmail() {
        return properties.getProperty("wrongemail");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getToken() {
        return properties.getProperty("token");
    }
}
