package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Company> companies;

    public CompanySection(List<Company> companies) {
        Objects.requireNonNull(companies, "Поле content не должно равняеться null");
        this.companies = companies;
    }

    public CompanySection() {
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return companies.equals(that.companies);
    }

    @Override
    public int hashCode() {
        return companies.hashCode();
    }

    @Override
    public String toString() {
        return companies.toString();
    }
}
