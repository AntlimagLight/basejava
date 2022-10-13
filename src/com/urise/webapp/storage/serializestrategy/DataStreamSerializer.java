package com.urise.webapp.storage.serializestrategy;

import com.urise.webapp.model.*;
import com.urise.webapp.util.LocalDateWithoutDay;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {

    @Override
    public void write(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeCollection(dos, r.getSections().entrySet(), entry -> {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getContent());
                    case ACHIEVEMENT, QUALIFICATIONS ->
                            writeCollection(dos, ((ListSection) section).getContent(), dos::writeUTF);
                    case EXPERIENCE, EDUCATION ->
                            writeCollection(dos, ((CompanySection) section).getCompanies(), com -> {
                                dos.writeUTF(com.getName());
                                writeCollection(dos, com.getPeriods(), period -> {
                                    writeLocalDate(dos, period.getStartDate());
                                    writeLocalDate(dos, period.getEndDate());
                                    dos.writeUTF(period.getTitle());
                                    dos.writeUTF(period.getDescription());
                                });
                            });
                }
            });
        }
    }

    @Override
    public Resume read(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer)
            throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private void readItems(DataInputStream dis, ElementProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new CompanySection(
                    readList(dis, () -> new Company(
                            dis.readUTF(),
                            readList(dis, () -> new Period(
                                    LocalDateWithoutDay.of(dis.readInt(), dis.readInt()),
                                    LocalDateWithoutDay.of(dis.readInt(), dis.readInt()),
                                    dis.readUTF(),
                                    dis.readUTF()
                            ))
                    )));
        };
    }
}
