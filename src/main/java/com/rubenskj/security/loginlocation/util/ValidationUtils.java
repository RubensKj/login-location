package com.rubenskj.security.loginlocation.util;

import org.springframework.util.StringUtils;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public static void validateString(String text, String messageError) {
        if (StringUtils.isEmpty(text)) {
            throw new IllegalArgumentException(messageError);
        }
    }
}
