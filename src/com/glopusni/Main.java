package com.glopusni;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Integer> calories = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        if (args.length == 0 || !(new File(args[0]).isFile())) {
            System.out.println("No parameters provided.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String value;
            int tempMax = 0;

            while ((value = reader.readLine()) != null) {
                try {
                    tempMax += Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    max = Math.max(tempMax, max);
                    calories.add(tempMax);
                    tempMax = 0;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Max calories among the elves: " + max);
        System.out.println("Calories sum for the first three elves:" + calculateTheGreatestThreeSum(calories));
    }

    private static int calculateTheGreatestThreeSum(List<Integer> calories) {
        calories = calories.stream().sorted().collect(Collectors.toList());
        return calories.get(calories.size() - 1) + calories.get(calories.size() - 2) + calories.get(calories.size() - 3);
    }
}
