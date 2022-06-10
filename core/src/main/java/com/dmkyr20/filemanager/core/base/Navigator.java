package com.dmkyr20.filemanager.core.base;

import java.nio.file.Path;

public interface Navigator {
    boolean go(String filename);
    boolean back();
    Path current();
}
