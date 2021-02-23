package com.github.ricardocunha.dynamodb.mapper;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.function.Function;

public interface AttributeMapper<T> {

    T map(AttributeValue attributeValue);

    AttributeValue map(T value);

    default <R> AttributeMapper<R> map(Function<T, R> reader, Function<R, T> writer) {
        return new SimpleAttributeMapper<>(attributeValue -> reader.apply(map(attributeValue)),
                value -> map(writer.apply(value)));
    }

}
