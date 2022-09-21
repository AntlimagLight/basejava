package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializestrategy.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;
    protected Serializer serializer;

    protected AbstractFileStorage(File directory, Serializer serializer) {
        Objects.requireNonNull(directory, "Директория не должна быть null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " не является директорией");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " недоступен для чтения/записи");
        }
        this.directory = directory;
        this.serializer = serializer;
    }

    @Override
    public void clear() {
        for (File file : getCheckedFileArray()) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        return getCheckedFileArray().length;
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO ошибка", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO ошибка", file.getName(), e);
        }
        doUpdate(r,file);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("Ошибка при чтении файла", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Ошибка при удалении файла", file.getName());
        }
    }

    @Override
    protected List<Resume> getCopyAll() {
        List<Resume> resumes = new ArrayList<>();
        for (File file : getCheckedFileArray()) {
            resumes.add(doGet(file));
        }
        return resumes;
    }

    protected File[] getCheckedFileArray() {
        File[] list = directory.listFiles();
        if (list == null) {
            throw new StorageException("Ошибка при чтении файла", null);
        }
        return list;
    }

    protected abstract Resume doRead(File file) throws IOException;

    protected abstract void doWrite(Resume r, File file) throws IOException;
}
