package home.utils.assertions;

import java.util.Calendar;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class Assert {

    public static void assertNotBlank(String text, String parameter, Class clazz) {
        if (isBlank(text)) {
            throw new IllegalArgumentException("Field '" + parameter + "' can't be blank in '" + clazz.getSimpleName() +"' class");
        }
    }

    public static void assertNotNull(Object value, String parameter, Class clazz) {
        if (value == null) {
            throw new IllegalArgumentException("Field '" + parameter + "' can't be null in '" + clazz.getSimpleName() +"' class");
        }
    }
}
