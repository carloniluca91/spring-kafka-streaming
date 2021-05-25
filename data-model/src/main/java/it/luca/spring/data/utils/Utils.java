package it.luca.spring.data.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {

    /**
     * Convert input date from a pattern to another
     * @param date input date
     * @param inputPattern input date pattern
     * @param outputPattern output pattern to be converted input date to
     * @return input date with output patterm
     */

    public static String changeDatePattern(String date, String inputPattern, String outputPattern) {

        return LocalDate.parse(date, DateTimeFormatter.ofPattern(inputPattern))
                .format(DateTimeFormatter.ofPattern(outputPattern));
    }

    /**
     * Filter input array using given predicate
     * @param tArray input array
     * @param predicate predicate
     * @param <T> type of input array
     * @return filtered array
     */

    public static <T> List<T> filter(T[] tArray, Predicate<T> predicate) {

        return filter(Arrays.asList(tArray), predicate);
    }

    /**
     * Filter input list using given predicate
     * @param tList input list
     * @param predicate predicate
     * @param <T> type of input list
     * @return filtered array
     */

    public static <T> List<T> filter(List<T> tList, Predicate<T> predicate) {

        return tList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /**
     * Checks wheter given input is not null
     * @param input input object
     * @param <T> type of input object
     * @return Returns true if given input is not-null, false otherwise
     */

    public static <T> boolean isPresent(T input) {

        return Optional.ofNullable(input).isPresent();
    }

    /**
     * Applies given function to each element of given array
     * @param tArray input array
     * @param function function to be applied
     * @param <T> type of input array
     * @param <R> type of output array
     * @return a list obtained by applying given function to each element of given array
     */

    public static <T, R> List<R> map(T[] tArray, Function<T, R> function) {

        return map(Arrays.asList(tArray), function);
    }

    /**
     * Applies given function to each element of given list
     * @param tList input list
     * @param function function to be applied
     * @param <T> type of input array
     * @param <R> type of output array
     * @return a list obtained by applying given function to each element of given list
     */

    public static <T, R> List<R> map(List<T> tList, Function<T, R> function) {

        return tList.stream()
                .map(function)
                .collect(Collectors.toList());
    }

    public static LocalDateTime now() {

        return LocalDateTime.now();
    }

    public static String now(String pattern) {

        return now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Applies given function if input is not null
     * @param t input
     * @param function function to be applied on input
     * @param elseValue alternative value if input is null
     * @param <T> type of input
     * @param <R> type of return value
     * @return function(input) if input is not null, alternative value otherwise
     */

    public static <T, R> R orElse(T t, Function<T, R> function, R elseValue) {

        return Optional.ofNullable(t)
                .map(function)
                .orElse(elseValue);
    }

    /**
     * Applies given function if input is not null
     * @param t input
     * @param function function to be applied on input
     * @param <T> type of input
     * @param <R> type of return value
     * @return function(input) if input is not null, null otherwise
     */

    public static <T, R> R orNull(T t, Function<T, R> function) {

        return orElse(t, function, null);
    }
}
