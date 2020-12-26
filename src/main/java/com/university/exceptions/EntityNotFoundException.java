package com.university.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> clazz, String name) {
        super(String.format("%s with name %s is not found", clazz.getSimpleName(), name));
    }
}
