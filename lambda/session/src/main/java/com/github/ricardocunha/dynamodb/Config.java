package com.github.ricardocunha.dynamodb;

import com.github.ricardocunha.dynamodb.model.Person;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class Config {

    private static final Logger LOG = Logger.getLogger(Config.class);

    @Inject
    DynamoDbClient client;

    void startup(@Observes StartupEvent event) {
        LOG.info("Check table "+ Person.TABLE_NAME);
        if (!isTableCreated(Person.TABLE_NAME)) {
            createTable(Person.TABLE_NAME, Person.PRIMARY_KEY);
        }
    }

    public boolean isTableCreated(String tableName) {
        try {
            DescribeTableRequest request = DescribeTableRequest.builder()
                    .tableName(tableName)
                    .build();
            client.describeTable(request);
            return true;
        } catch (DynamoDbException e) {
            return false;
        }
    }

    private void createTable(String tableName, String key) {
        try {
            CreateTableRequest request = CreateTableRequest.builder()
                    .attributeDefinitions(AttributeDefinition.builder()
                            .attributeName(key)
                            .attributeType(ScalarAttributeType.S)
                            .build())
                    .keySchema(KeySchemaElement.builder()
                            .attributeName(key)
                            .keyType(KeyType.HASH)
                            .build())
                    .provisionedThroughput(ProvisionedThroughput.builder()
                            .readCapacityUnits(10L)
                            .writeCapacityUnits(10L)
                            .build())
                    .tableName(tableName)
                    .build();
            CreateTableResponse response = client.createTable(request);
            DescribeTableRequest tableRequest = DescribeTableRequest.builder()
                    .tableName(tableName)
                    .build();

            // Wait until the Amazon DynamoDB table is created
            DynamoDbWaiter dbWaiter = client.waiter();
            WaiterResponse<DescribeTableResponse> waiterResponse =  dbWaiter.waitUntilTableExists(tableRequest);
            waiterResponse.matched().response().ifPresent(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}