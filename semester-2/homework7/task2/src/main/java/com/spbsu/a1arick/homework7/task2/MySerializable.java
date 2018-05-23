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
     * @throws IOException
     */
    void serialize(OutputStream out) throws IOException;

    /**
     * Deserializes object from {@link InputStream}
     * @param in input stream
     * @throws IOException
     */
    void deserialize(InputStream in) throws IOException;
}
