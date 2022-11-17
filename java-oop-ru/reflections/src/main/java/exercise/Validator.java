package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

// BEGIN
class Validator {
    public static void main(String[] args) {
        var res = validate(new Address(null, "", "", "", ""));
        System.out.println(res);
    }
    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();

        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (isNotNonNullValidField(NotNull.class, field, address)) {
                result.add(field.getName());
            }
        }

        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> result = new HashMap<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            List<String> values = result.getOrDefault(key, new ArrayList<>());
            boolean isNotValid = false;
            if (isNotNonNullValidField(NotNull.class, field, address)) {
                isNotValid = true;
                values.add("can not be null");
            }
            if (isNotMinLengthValidField(MinLength.class, field, address)) {
                isNotValid = true;
                MinLength minLength = field.getAnnotation(MinLength.class);
                int min = minLength.minLength();
                values.add("length less than " + min);
            }
            if (isNotValid) {
                result.put(key, values);
            }
        }
        return result;
    }

    private static boolean isNotMinLengthValidField(Class<MinLength> anno, Field field, Address address) {
        MinLength minLength = field.getAnnotation(anno);
        if (minLength != null) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(address);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (value == null) {
                return false;
            }

            int min = minLength.minLength();
            boolean isValid = ((String) value).length() >= min;
            if (!isValid) {
                return true;
            }

        }
        return false;
    }

    private static boolean isNotNonNullValidField(Class<NotNull> anno, Field field, Address address) {
        NotNull notNull = field.getAnnotation(anno);
        if (notNull != null) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(address);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (!Objects.nonNull(value)) {
                return true;
            }
        }
        return false;
    }
}
// END
