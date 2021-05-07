package com.urise.webapp;

import java.util.*;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        int[] array = {9, 8};
        System.out.println(minValue(array));
        List<Integer> list = Arrays.asList(1, 3, 4, 4, 2, 2);
        System.out.println(oddOrEven(list));
    }


    static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (result, element) -> 10 * result + element);

    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        Integer sum = integers.stream().reduce(0, Integer::sum);
        boolean takeEven = sum % 2 == 0;
        return integers.stream().filter((x) -> {
            boolean isEven = x % 2 == 0;
            return takeEven == isEven;
        }).collect(Collectors.toList());
    }
}

