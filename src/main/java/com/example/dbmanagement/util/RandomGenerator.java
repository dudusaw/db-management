package com.example.dbmanagement.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class RandomGenerator {

    private final Random random = new SecureRandom();
    private final StringBuilder sb = new StringBuilder();

    private static final char default_first = 'a';
    private static final int default_range = 26;

    private static final char password_first = '!';
    private static final int password_range = 126 - password_first;

    public int getInt(int bound) {
        return random.nextInt(bound);
    }

    public String getRandomString(int length) {
        return getRandomString(length, length, default_first, default_range);
    }

    public String generatePassword(int minLength, int maxLength) {
        return getRandomString(minLength, maxLength, password_first, password_range);
    }

    public String getRandomString(int minLength, int maxLength) {
        return getRandomString(minLength, maxLength, default_first, default_range);
    }

    private String getRandomString(int minLength, int maxLength, int first, int range) {
        assert minLength > 0;
        assert maxLength > 0;
        assert maxLength >= minLength;

        sb.delete(0, sb.length());
        int size = random.nextInt(maxLength - minLength) + minLength;

        while (sb.length() < size) {
            char nextChar = (char) (random.nextInt(range) + first);
            sb.append(nextChar);
        }
        return sb.toString();
    }
}
