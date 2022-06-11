package com.dmkyr20.filemanager.core.basics;

import com.dmkyr20.filemanager.core.base.ExtendedFileManager;
import com.dmkyr20.filemanager.core.base.Verifier;

import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.nio.file.Path;

public class FileManagerFactory {

    private Path initPath = getDefaultInitPath();

    public FileManagerFactory setInitPath(Path path) {
        initPath = path;
        return this;
    }

    public FileManagerFactory reset() {
        initPath = getDefaultInitPath();
        return this;
    }

    public static Path getDefaultInitPath() {
        return FileSystemView.getFileSystemView().getRoots()[0].toPath();
    }

    public ExtendedFileManager get(Verifier verifier) throws IOException {
        return new ExtendedFileManagerBasic(initPath, verifier);
    }

    public ExtendedFileManager getDefault() throws IOException {
        return new ExtendedFileManagerBasic(initPath);
    }

    public ExtendedFileManager getAlwaysYes() throws IOException {
        return get(s -> true);
    }

    public ExtendedFileManager getAlwaysNo() throws IOException {
        return get(s -> false);
    }
}
