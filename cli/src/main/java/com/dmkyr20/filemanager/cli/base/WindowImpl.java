package com.dmkyr20.filemanager.cli.base;

import com.dmkyr20.filemanager.core.base.FileManager;
import com.dmkyr20.filemanager.core.basics.FileManagerBasic;

import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

public class WindowImpl implements Window {
    private final FileManager fileManager;

    public WindowImpl() {
        this(getRootDir());
    }

    private static Path getRootDir() {
        return FileSystemView.getFileSystemView().getRoots()[0].toPath();
    }

    public WindowImpl(Path initDir) {
        try {
            this.fileManager = new FileManagerBasic(initDir);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void showFiles() {
        fileManager.showAllNames()
                .forEach(System.out::println);
    }


    @Override
    public void open(String filename) {

    }

    @Override
    public void back() {

    }

    @Override
    public void createFile(String filename) {

    }

    @Override
    public void delete(String filename) {

    }
}
