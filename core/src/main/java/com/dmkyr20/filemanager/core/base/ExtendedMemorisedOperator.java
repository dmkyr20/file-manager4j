package com.dmkyr20.filemanager.core.base;

public interface ExtendedMemorisedOperator extends ExtendedOperator, MemorisedOperator {
    default boolean move(String filename, String directory) {
        return move(
                current().resolve(filename),
                current().resolve(directory).resolve(filename));
    }

    void copy(String filename);
    void cut(String filename);
    boolean paste(String newFilename);
    boolean paste();
}
