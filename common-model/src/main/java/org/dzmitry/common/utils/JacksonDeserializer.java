package org.dzmitry.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.serializer.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JacksonDeserializer<T> implements Deserializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final Class<T> clazz;

    public JacksonDeserializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T deserialize(InputStream inputStream) throws IOException {
        return objectMapper.readValue(inputStream, clazz);
    }

    @Override
    public T deserializeFromByteArray(byte[] data) {
        try (InputStream inputStream = new ByteArrayInputStream(data)) {
            return objectMapper.readValue(inputStream, clazz);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
