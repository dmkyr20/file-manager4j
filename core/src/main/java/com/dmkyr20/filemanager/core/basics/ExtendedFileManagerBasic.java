package com.dmkyr20.filemanager.core.basics;

import com.dmkyr20.filemanager.core.base.ExtendedFileManager;
import com.dmkyr20.filemanager.core.base.ExtendedOperator;
import com.dmkyr20.filemanager.core.base.Verifier;

import java.io.IOException;
import java.nio.file.Path;

class ExtendedFileManagerBasic extends FileManagerBasic implements ExtendedFileManager {
    private enum Operation {
        COPY, CUT
    }

    private Path bufferedFile;
    private Operation operation;
    private final ExtendedOperator extendedOperator;

    public ExtendedFileManagerBasic(Path initPath) throws IOException {
        this(initPath, Verifier.DEFAULT_VERIFIER);
    }

    public ExtendedFileManagerBasic(Path initPath, Verifier verifier) throws IOException {
        super(initPath, verifier);
        this.extendedOperator = new ExtendedOperatorBasic(verifier);
    }

    @Override
    public void copy(String filename) {
        bufferedFile = current().resolve(filename);
        operation = Operation.COPY;
    }

    @Override
    public void cut(String filename) {
        bufferedFile = current().resolve(filename);
        operation = Operation.CUT;
    }

    @Override
    public boolean past(String newFilename) {
        switch (operation) {
            case COPY:
                return copy(bufferedFile, current().resolve(newFilename));
            case CUT:
                return move(bufferedFile, current().resolve(newFilename));
            default:
                return false;
        }
    }

    @Override
    public boolean past() {
        return past(bufferedFile.getFileName().toString());
    }

    @Override
    public boolean move(Path p1, Path p2) {
        return extendedOperator.move(p1, p2);
    }

    @Override
    public boolean copy(Path p1, Path p2) {
        return extendedOperator.copy(p1, p2);
    }
}
