package com.dmkyr20.filemanager.core.base;

import java.nio.file.Path;
import java.util.List;

public interface MemorisedShowman extends Showman, Navigator {
    default List<Path> showAll() {
        return showAll(current());
    }

    default List<String> showAllNames() {
        return showAll(current())
                .stream()
                .map(Path::getFileName)
                .map(Path::toString)
                .toList();
    }
}
