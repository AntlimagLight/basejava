package com.urise.webapp.storage.serializestrategy;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Serializer {

    void write(Resume r, OutputStream os) throws IOException;

    Resume read(InputStream is) throws IOException;

}
