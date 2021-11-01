package org.dzmitry.common.utils;

import lombok.SneakyThrows;
import org.dzmitry.common.utils.JacksonDeserializer;
import org.dzmitry.common.utils.JacksonSerializer;

public class PayloadTransformation<T> {

    private final JacksonSerializer<T> serializer;
    private final JacksonDeserializer<T> deserializer;

    public PayloadTransformation(Class<T> clazz) {
        serializer = new JacksonSerializer<>();
        deserializer = new JacksonDeserializer<>(clazz);
    }

    public T transformByteArrayToData(byte[] data) {
        return deserializer.deserializeFromByteArray(data);
    }

    @SneakyThrows
    public byte[] transformDataToByteArray(T data) {
        return serializer.serializeToByteArray(data);
    }

}
