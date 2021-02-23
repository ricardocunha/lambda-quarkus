package com.github.ricardocunha.dynamodb.mapper;

import lombok.Data;

import java.util.function.BiConsumer;
import java.util.function.Function;

@Data
public class Property<R, T> {

    private final String name;
    private final Function<R, T> getter;
    private final BiConsumer<R, T> setter;

}
