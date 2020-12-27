package com.university.exceptions;

public class EntityNotFoundException extends ServiceRuntimeException {

    public EntityNotFoundException(Class<?> clazz, String name) {
        super(String.format("%s with name %s is not found", clazz.getSimpleName(), name));
    }
}
