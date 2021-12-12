package by.hardziyevich.task4.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static by.hardziyevich.task4.reader.PropertiesUtil.PropertyFill.*;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static Property getProperty(String carNumber) {
        String type = get(carNumber + PropertyFill.TYPE);
        String weight = get(carNumber + PropertyFill.WEIGHT);
        String size = get(carNumber + PropertyFill.SIZE);
        return new Property.Builder()
                .typeCar(type)
                .weight(Integer.parseInt(weight))
                .size(Integer.parseInt(size))
                .build();
    }

    public static Property getAllProperty() {
        return new Property.Builder().mapping(Map.of(
                CAR_1, getProperty(CAR_1),
                CAR_2, getProperty(CAR_2),
                CAR_3, getProperty(CAR_3),
                CAR_4, getProperty(CAR_4))).build();
    }

    private static void loadProperties() {
        try (InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(FILE_PATH)) {
            PROPERTIES.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class PropertyFill {

        public static final String FILE_PATH = "application.properties";

        public static final String CAR_1 = "car1";
        public static final String CAR_2 = "car2";
        public static final String CAR_3 = "car3";
        public static final String CAR_4 = "car4";

        private static final String TYPE = ".type";
        private static final String WEIGHT = ".weight";
        private static final String SIZE = ".size";
    }
}
