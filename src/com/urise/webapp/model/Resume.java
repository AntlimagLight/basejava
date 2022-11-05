package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * Initial resume class
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final Resume NEW = new Resume("new","");

    static {
        NEW.addSection(SectionType.OBJECTIVE, TextSection.EMPTY);
        NEW.addSection(SectionType.PERSONAL, TextSection.EMPTY);
        NEW.addSection(SectionType.ACHIEVEMENT, ListSection.EMPTY);
        NEW.addSection(SectionType.QUALIFICATIONS, ListSection.EMPTY);
        List<Company> emptyList = new ArrayList<>();
        emptyList.add(Company.EMPTY);
        NEW.addSection(SectionType.EXPERIENCE, new CompanySection(emptyList));
        NEW.addSection(SectionType.EDUCATION, new CompanySection(emptyList));
    }

    private final String uuid;

    private String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public Resume() {
        this("");
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "Поле uuid не должно равняеться null");
        Objects.requireNonNull(fullName, "Поле fullName не должно равняеться null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
    }

    public void addSection(SectionType type, AbstractSection value) {
        sections.put(type, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return uuid;
    }


}
