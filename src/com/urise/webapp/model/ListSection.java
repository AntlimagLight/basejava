package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final AbstractSection EMPTY = new ListSection();

    private List<String> content;

    public ListSection(List<String> content) {
        Objects.requireNonNull(content, "Поле content не должно равняеться null");
        this.content = content;
    }

    public ListSection() {
        this.content = new ArrayList<>();
    }

    public List<String> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String string : content) {
            result.append(string).append("\n");
        }
        return result.toString();
    }
}
