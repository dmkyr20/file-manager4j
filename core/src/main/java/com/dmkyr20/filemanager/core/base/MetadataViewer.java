package com.dmkyr20.filemanager.core.base;

import com.dmkyr20.filemanager.commander.Command;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public interface MetadataViewer {
    BasicFileAttributes getBasicData(Path file);

    enum MetadataViewCommand implements Command {
        SHOW_BASIC_DATA
    }
}
