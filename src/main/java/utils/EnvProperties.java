package utils;

import java.io.InputStream;
import java.util.Properties;

public class EnvProperties {

    private static final Properties properties = new Properties();

    private static void setProperties() {
        InputStream inputStream = EnvProperties.class.getResourceAsStream("/api.properties");
        System.out.println("PATH "+inputStream);
        try {
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error during loading properties", e);
        }
    }

    public static String getApiUrl() {
        setProperties();
        return properties.getProperty("apiUrl");
    }

}
