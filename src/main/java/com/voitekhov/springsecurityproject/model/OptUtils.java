package com.voitekhov.springsecurityproject.model;

import java.util.Random;

public class OptUtils {

    public static String generateOpt() {
        return String.valueOf(new Random().nextInt() * 100);
    }
}
