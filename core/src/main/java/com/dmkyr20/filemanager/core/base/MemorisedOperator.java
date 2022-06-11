package com.dmkyr20.filemanager.core.base;


public interface MemorisedOperator extends Operator, Navigator {
    default boolean delete(String filename) {
        return delete(current().resolve(filename));
    }

    default boolean createFile(String filename) {
        return createFile(current(), filename);
    }

    default boolean createDirectory(String filename) {
        return createDir(current(), filename);
    }
}
