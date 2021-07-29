package com.avangarde.parkinglot.resources;

import java.util.Random;
import java.util.stream.Stream;

public class InputGenerator {
    public static void main(String[] args) {
        System.out.println(createRandomPlate());
    }
    public static String createRandomPlate() {
            String generatedString2 = generate(2, 48, 57);
            String generatedString = generate(2, 65, 90);
            String generatedString3 = generate(3, 65, 90);

            return generatedString + generatedString2 + generatedString3;
    }


    private static String generate(int targetStringLength, int leftRange, int rightRange) {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftRange + (int)
                    (random.nextFloat() * (rightRange - leftRange + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

}
