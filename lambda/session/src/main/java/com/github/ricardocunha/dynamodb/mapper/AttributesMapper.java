package com.github.ricardocunha.dynamodb.mapper;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public interface AttributesMapper<T> {

    T map(Map<String, AttributeValue> attributes);

    Map<String, AttributeValue> map(T value);

}

