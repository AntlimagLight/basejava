package com.urise.webapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Company implements Serializable {

    private final List<Period> periods;
    private final String name;

    public Company(String name, List<Period> periods) {
        Objects.requireNonNull(name, "Поле companyName не должно равняеться null");
        Objects.requireNonNull(periods, "Поле periods не должно равняеться null");
        this.periods = periods;
        this.name = name;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company that = (Company) o;

        if (!periods.equals(that.periods)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = periods.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nname: " + name +
                "\nperiods: " + periods + "\n";
    }
}
