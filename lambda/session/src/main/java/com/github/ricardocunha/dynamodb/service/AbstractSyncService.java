package com.github.ricardocunha.dynamodb.service;


import com.github.ricardocunha.dynamodb.mapper.AttributesMapper;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class AbstractSyncService<T> {

    @Inject
    DynamoDbClient client;

    protected  <T> Stream<T> scan(String tableName, AttributesMapper<T> mapper) {
        return scan(tableName, mapper, builder -> {});
    }

    protected <T> Stream<T> scan(String tableName,
                              AttributesMapper<T> mapper,
                              Consumer<ScanRequest.Builder> requestBuilder) {

        ScanResponse response = client.scan(requestBuilder
                .andThen(builder -> builder.tableName(tableName)));

        return response.items().stream().map(mapper::map);
    }

    protected <T> Stream<T> scan(ScanRequest scanRequest,
                                 AttributesMapper<T> mapper) {

        ScanResponse response = client.scan(scanRequest);

        return response.items().stream().map(mapper::map);
    }

    protected <T> Optional<T> get(String tableName, AttributesMapper<T> mapper, String key, AttributeValue value) {
        GetItemResponse response = client.getItem(builder -> builder
                .tableName(tableName)
                .key(Map.of(key, value)));

        return response.hasItem() ? Optional.of(mapper.map(response.item())) : Optional.empty();
    }

    protected <T> T save(String tableName, AttributesMapper<T> mapper, T value) {
        client.putItem(builder -> {
            builder.tableName(tableName).item(mapper.map(value));
        });
        return value;
    }
}