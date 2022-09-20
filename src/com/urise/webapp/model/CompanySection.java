package com.urise.webapp.model;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Company> content;

    public CompanySection(List<Company> content) {
        Objects.requireNonNull(content, "Поле content не должно равняеться null");
        this.content = content;
    }

    public String getContent() {
        return String.valueOf(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
