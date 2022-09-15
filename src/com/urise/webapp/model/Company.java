package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {

    private final List<TimePeriod> periods;
    private final String companyName;

    public Company(String companyName, List<TimePeriod> periods) {
        Objects.requireNonNull(companyName, "Поле companyName не должно равняеться null");
        Objects.requireNonNull(periods, "Поле periods не должно равняеться null");
        this.periods = periods;
        this.companyName = companyName;
    }

    public List<TimePeriod> getPeriods() {
        return periods;
    }

    public String getCompanyName() {
        return companyName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company that = (Company) o;

        if (!periods.equals(that.periods)) return false;
        return companyName.equals(that.companyName);
    }

    @Override
    public int hashCode() {
        int result = periods.hashCode();
        result = 31 * result + companyName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\ncompanyName: " + companyName +
                "\nperiods: " + periods + "\n";
    }
}
