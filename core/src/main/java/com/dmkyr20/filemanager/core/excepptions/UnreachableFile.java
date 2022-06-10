package com.dmkyr20.filemanager.core.excepptions;

import java.nio.file.Path;

public class UnreachableFile extends FileManagerException {

    private static final String MESSAGE = "File (%s) unreachable!";

    public UnreachableFile(Path file) {
        super(String.format(MESSAGE, file.toString()));
    }

    public UnreachableFile(Path file, Throwable cause) {
        super(String.format(MESSAGE, file.toString()), cause);
    }
}
