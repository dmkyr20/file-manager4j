package com.dmkyr20.filemanager.core.base;


public interface MemorisedOperator extends Operator {
    boolean delete(String filename);
    boolean createFile(String filename);
    boolean createDirectory(String filename);
}
