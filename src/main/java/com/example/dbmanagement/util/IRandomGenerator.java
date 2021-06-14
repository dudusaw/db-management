package com.example.dbmanagement.util;

public interface IRandomGenerator {
    int getInt(int bound);

    String getRandomString(int length);

    String generatePassword(int minLength, int maxLength);

    String getRandomString(int minLength, int maxLength);
}
