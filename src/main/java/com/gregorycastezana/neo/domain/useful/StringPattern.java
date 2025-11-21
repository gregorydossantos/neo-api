package com.gregorycastezana.neo.domain.useful;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringPattern {

    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z_]+$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isInvalidSizeName(String name) {
        return name.length() < 4 || name.length() > 15;
    }
}
