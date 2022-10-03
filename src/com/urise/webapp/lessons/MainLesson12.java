package com.urise.webapp.lessons;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainLesson12 {
    public static void main(String[] args) {

        int[] arr = {3,5,5,4,8,8,2};
        System.out.println("___");
        System.out.println(minValue(arr));

        List<Integer> list = Arrays.stream(arr).boxed().toList();
        System.out.println(oddOrEven(list));
    }

    static int minValue(int[] values) {
        if (values.length > 9) {
            throw new IllegalArgumentException("Размер массива не больше 9");
        }
        if (IntStream.of(values).anyMatch(n -> n < 1 | n > 9)) {
            throw new NumberFormatException("Элементы массива должны быть цифрами от 1 до 9");
        }
        int[] filteredValues = IntStream
                .of(values)
                .distinct()
                .toArray();
        int result = 0;
        for (int i = filteredValues.length; i > 0 ; i--) {
            int min = IntStream
                    .of(filteredValues)
                    .min()
                    .getAsInt();
            result += Math.pow(10, i - 1) * min;
            filteredValues = IntStream.of(filteredValues).filter(n -> n > min).toArray();
        }
        return result;
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        int mod = integers
                .stream()
                .mapToInt(Integer::intValue)
                .sum() % 2;

        return integers
                .stream()
                .filter(n -> n % 2 != mod)
                .collect(Collectors.toList());
    }
}
