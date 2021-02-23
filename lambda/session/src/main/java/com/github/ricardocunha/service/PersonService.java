package com.github.ricardocunha.service;

import com.github.ricardocunha.dynamodb.model.Person;
import com.github.ricardocunha.dynamodb.service.PersonSyncService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PersonService {

    @Inject
    PersonSyncService personSyncService;


    public Person save(Person person) {
        return personSyncService.save(person);
    }

    public List<Person> findAll() {
        return personSyncService.findAll();
    }

}
