package com.urise.webapp.util;

import java.time.LocalDate;

public class LocalDateWithoutDay {
    public static LocalDate of(int year, int month) {
        return LocalDate.of(year, month, 1);
    }
}
