package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Резюме c uuid : " + uuid + " уже существует в хранилище!", uuid);
    }
}
