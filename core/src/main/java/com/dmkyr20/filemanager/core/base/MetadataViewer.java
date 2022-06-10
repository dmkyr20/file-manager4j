package com.dmkyr20.filemanager.core.base;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public interface MetadataViewer {
    BasicFileAttributes getBasicData(Path file);
}
