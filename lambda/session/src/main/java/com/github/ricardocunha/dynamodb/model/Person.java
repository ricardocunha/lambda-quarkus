package com.github.ricardocunha.dynamodb.model;

import com.github.ricardocunha.dynamodb.mapper.AttributeMappers;
import com.github.ricardocunha.dynamodb.mapper.AttributesMapper;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.util.Objects;

/**
 * A Person represented by a rich-domain object.
 * Persisted to DynamoDB as the primary table key.
 */
@RegisterForReflection
@Data
public class Person {

    private String id;
    private String name;

    public static final String TABLE_NAME = "Person";
    public static final String PRIMARY_KEY = "id";

    private static final AttributesMapper<Person> PERSON_MAPPER = AttributeMappers.builder(Person::new)
            .attribute("id", Person::getId, Person::setId, AttributeMappers.STRING)
            .attribute("name", Person::getName, Person::setName, AttributeMappers.STRING)
            .build();


    public static AttributesMapper<Person> mapper() {
        return PERSON_MAPPER;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }

        Person other = (Person) obj;

        return Objects.equals(other.id, this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
