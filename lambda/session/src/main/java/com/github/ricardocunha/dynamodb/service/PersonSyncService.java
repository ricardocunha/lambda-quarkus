package com.github.ricardocunha.dynamodb.service;

import com.github.ricardocunha.dynamodb.model.Person;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class PersonSyncService extends AbstractSyncService<Person> {

    public Optional<Person> get(String name) {
        Objects.requireNonNull(name, "name should de passed");
        return get(Person.TABLE_NAME, Person.mapper(), "name", AttributeValue.builder().s(name).build());
    }

    public List<Person> findAll() {
        return scan(Person.TABLE_NAME, Person.mapper()).collect(Collectors.toList());
    }

    public Person save(Person person) {
        Objects.requireNonNull(person, "Person should de passed");
        return save(Person.TABLE_NAME, Person.mapper(), person);
    }

}
