package com.spbsu.a1arick.homework7.task2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface for object serialization
 */
public interface MySerializable {

    /**
     * Serializes object to {@link OutputStream}
     * @param out output stream
     * @throws IOException if {@link OutputStream} can't be written
     */
    void serialize(OutputStream out) throws IOException;

    /**
     * Deserializes object from {@link InputStream}
     * @param in input stream
     * @throws IOException if {@link InputStream} can't be read
     */
    void deserialize(InputStream in) throws IOException;
}
