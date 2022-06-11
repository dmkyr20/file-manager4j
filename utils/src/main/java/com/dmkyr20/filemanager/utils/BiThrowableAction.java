package com.dmkyr20.filemanager.utils;

@FunctionalInterface
public interface BiThrowableAction<T, P, O, E extends Throwable> {
    O perform(T t, P p) throws E;
}
