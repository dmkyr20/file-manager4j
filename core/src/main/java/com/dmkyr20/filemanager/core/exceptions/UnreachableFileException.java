package com.dmkyr20.filemanager.core.exceptions;

import java.nio.file.Path;

public class UnreachableFileException extends FileManagerException {

    private static final String MESSAGE = "File (%s) unreachable!";

    public UnreachableFileException(Path file) {
        super(String.format(MESSAGE, file.toString()));
    }

    public UnreachableFileException(Path file, Throwable cause) {
        super(String.format(MESSAGE, file.toString()), cause);
    }
}
