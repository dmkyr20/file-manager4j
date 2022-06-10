package com.dmkyr20.filemanager.core.basics;

import com.dmkyr20.filemanager.core.base.Navigator;
import com.dmkyr20.filemanager.core.excepptions.UnreachableFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class NavigatorBasic implements Navigator {

    private Path currentFile;

    protected NavigatorBasic(Path initFolder) throws IOException {
        currentFile = initFolder.toRealPath();
    }

    @Override
    public boolean go(String filename) {
        try {
            var path = currentFile.resolve(filename).toRealPath();
            if(Files.isDirectory(path)) {
                currentFile = path;
                return true;
            }
            return false;
        } catch (IOException e) {
            throw new UnreachableFile(currentFile, e);
        }
    }

    @Override
    public boolean back() {
        currentFile = currentFile.getParent();
        return true;
    }

    @Override
    public Path current() {
        return Path.of(currentFile.toString());
    }
}
