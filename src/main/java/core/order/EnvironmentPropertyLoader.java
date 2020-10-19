package core.order;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class EnvironmentPropertyLoader {

    private static EnvironmentPropertyLoader env = new EnvironmentPropertyLoader();
    private static Properties properties;
    private static boolean isInitialized;

    private EnvironmentPropertyLoader() {

    }

    private static Logger log = LoggerFactory.getLogger(EnvironmentPropertyLoader.class);

    private static void initIfNotInitialized() {
        if (!isInitialized) {
            properties = new Properties();
            String envName = System.getProperty("env");
            log.info("Properties are used from " + envName.substring(0, 4) + " environment.");
            File propertiesFile = new File(String.format("src/main/resources/properties.environments/%s.properties", envName));

            try (InputStream input = new FileInputStream(propertiesFile.getAbsolutePath())) {
                properties.load(input);
                isInitialized = true;
            } catch (IOException ex) {
                log.debug("Couldn't read file!");
            }
        }
    }

    public static String getProperty(final String propName) {
        initIfNotInitialized();
        return properties.getProperty(propName);
    }
}
