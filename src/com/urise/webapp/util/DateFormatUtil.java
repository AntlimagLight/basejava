package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DateFormatUtil {

    public static final LocalDate CURRENT_MONTH = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),1);
    public static final LocalDate DEFAULT_DATE = LocalDate.of(3000,1,1);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static String format(LocalDate date) {
        if (date == null) return "";
        return date.equals(DEFAULT_DATE) ? "" : date.format(DATE_FORMATTER);
    }

    public static String formatForView(LocalDate date) {
        if (date == null) return "";
        if (date.equals(DEFAULT_DATE)) return "";
        if (date.isAfter(CURRENT_MONTH) || date.equals(CURRENT_MONTH)) return "Сейчас";
        return date.format(DATE_FORMATTER);
    }

    public static LocalDate parse(String date) {
        if (date == null || date.trim().length() == 0 || "NoDate".equals(date)) return DEFAULT_DATE;
        YearMonth yearMonth = YearMonth.parse(date, DATE_FORMATTER);
        return LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1);
    }
}
