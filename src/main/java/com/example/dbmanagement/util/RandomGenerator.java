package com.example.dbmanagement.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomGenerator {

    private final Random random = new Random();
    private static final int range = 26;
    private static final char first = 'a';

    public int getInt(int bound) {
        return random.nextInt(bound);
    }

    public String getRandomString(int length) {
        return getRandomString(length, length);
    }

    public String getRandomString(int minLength, int maxLength) {
        assert minLength > 0;
        assert maxLength > 0;
        assert maxLength >= minLength;

        StringBuilder sb = new StringBuilder();
        int size = random.nextInt(maxLength - minLength) + minLength;
        while (sb.length() < size) {
            char nextChar = (char) (random.nextInt(range) + first);
            sb.append(nextChar);
        }
        return sb.toString();
    }
}
