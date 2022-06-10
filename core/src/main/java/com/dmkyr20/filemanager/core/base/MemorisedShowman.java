package com.dmkyr20.filemanager.core.base;

import java.nio.file.Path;
import java.util.List;

public interface MemorisedShowman extends Showman {
    List<Path> showAll();
    List<String> showAllNames();
}
