package com.urise.webapp;

import java.util.*;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        int[] array = {1,3,4,4,2,3};
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
        return integers.stream().filter((x) ->
                //sum % 2 == 0 ? x % 2 == 0 : x % 2 != 0) - было
                (sum % 2 == 0) == (x % 2 == 0)) // - идея предложила
                .collect(Collectors.toList());
    }
}

