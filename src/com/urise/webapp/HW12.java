package com.urise.webapp;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HW12 {

    public static void main(String[] args) {
        int[] array = {1,3,4,4,2,3};
        System.out.println(minValue(array));
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(2);
        list.add(2);
        System.out.println(oddOrEven(list));
    }


    static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (result, element) -> 10 * result + element);

    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();
        Integer sum = integers.stream().reduce(0, (result, i) -> {
            (i % 2 == 0 ? evens : odds).add(i);
            return result + i;
        });
        return (sum % 2 == 0) ? evens : odds;
    }
}

