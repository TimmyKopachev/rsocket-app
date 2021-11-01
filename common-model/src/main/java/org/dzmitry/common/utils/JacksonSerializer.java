package org.dzmitry.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.serializer.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JacksonSerializer<T> implements Serializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public void serialize(T data, OutputStream outputStream) throws IOException {
        objectMapper.writeValue(outputStream, data);
    }

    @Override
    public byte[] serializeToByteArray(T data) throws IOException {
        try (ByteArrayOutputStream outputStream = prepareOutputStream(data)) {
            return outputStream.toByteArray();
        } catch (IOException exception) {
            throw new IOException(exception);
        }
    }

    private ByteArrayOutputStream prepareOutputStream(T data) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            objectMapper.writeValue(outputStream, data);
            return outputStream;
        }
    }
}
