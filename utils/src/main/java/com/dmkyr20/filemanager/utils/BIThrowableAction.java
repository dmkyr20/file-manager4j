package com.dmkyr20.filemanager.utils;

public interface BIThrowableAction<T, P, O, E extends Throwable> {
    O perform(T t, P p) throws E;
}
