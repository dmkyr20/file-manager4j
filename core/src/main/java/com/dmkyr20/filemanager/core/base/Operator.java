package com.dmkyr20.filemanager.core.base;

import com.dmkyr20.filemanager.commander.Command;

import java.nio.file.Path;

public interface Operator {
    boolean delete(Path path);
    boolean createDir(Path parent, String filename);
    boolean createFile(Path parent, String filename);
}
