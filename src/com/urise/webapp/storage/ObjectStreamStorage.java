package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializestrategy.Serializer;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {

    protected ObjectStreamStorage(File directory, Serializer serializer) {
        super(directory, serializer);
    }

    @Override
    protected Resume doRead(File file) {
        try {
            return serializer.read(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Ошибка при чтении", file.getName(), e);
        }
    }

    @Override
    protected void doWrite(Resume r, File file) {
        try {
            serializer.write(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Ошибка при записи", r.getUuid(), e);
        }

    }
}
