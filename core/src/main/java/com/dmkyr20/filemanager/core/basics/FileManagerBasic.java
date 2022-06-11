package com.dmkyr20.filemanager.core.basics;

import com.dmkyr20.filemanager.core.base.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

class FileManagerBasic implements FileManager {
    private final Navigator navigator;
    private final Operator operator;
    private final Showman showman;

    public FileManagerBasic(Path initPath) throws IOException {
        this(initPath, Verifier.DEFAULT_VERIFIER);
    }

    public FileManagerBasic(Path initPath, Verifier verifier) throws IOException {
        this.navigator = new NavigatorBasic(initPath);
        this.operator = new OperatorBasic(verifier);
        this.showman = new ShowmanBasic();
    }

    @Override
    public boolean go(String filename) {
        return navigator.go(filename);
    }

    @Override
    public boolean back() {
        return navigator.back();
    }

    @Override
    public Path current() {
        return navigator.current();
    }

    @Override
    public boolean delete(Path path) {
        return operator.delete(path);
    }

    @Override
    public boolean createDir(Path parent, String filename) {
        return operator.createDir(parent, filename);
    }

    @Override
    public boolean createFile(Path parent, String filename) {
        return operator.createFile(parent, filename);
    }

    @Override
    public List<Path> showAll(Path path) {
        return showman.showAll(path);
    }
}
