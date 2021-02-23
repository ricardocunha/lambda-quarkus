package com.github.ricardocunha.api;

import com.github.ricardocunha.dynamodb.model.Person;
import com.github.ricardocunha.service.PersonService;
import io.quarkus.vertx.web.Route;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE;
import static io.vertx.core.http.HttpMethod.GET;
import static io.vertx.core.http.HttpMethod.POST;

public class PersonResource {

    @Inject
    PersonService service;

    @Route(path = "/person", methods = POST)
    void save(RoutingContext context) {
        JsonObject jsonObject = context.getBodyAsJson();
        Person person = jsonObject.mapTo(Person.class);
        service.save(person);
        context.response().setStatusCode(201);
    }

    /**
     * This was created just to provide a visualization on tests
     * @param context
     */
    @Route(path = "/person", methods = GET)
    void findAll(RoutingContext context) {
        context.response().headers().set(CONTENT_TYPE, "application/json");
        context.response().setStatusCode(200).end(Json.encodePrettily(service.findAll()));
    }
}




