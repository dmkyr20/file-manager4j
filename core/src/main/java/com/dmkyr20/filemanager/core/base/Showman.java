package com.dmkyr20.filemanager.core.base;

import com.dmkyr20.filemanager.commander.Command;

import java.nio.file.Path;
import java.util.List;

public interface Showman {
    List<Path> showAll(Path path);
}
