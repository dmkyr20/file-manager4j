package com.dmkyr20.filemanager.core.basics;

import com.dmkyr20.filemanager.core.base.Operator;
import com.dmkyr20.filemanager.core.base.Verifier;
import com.dmkyr20.filemanager.core.excepptions.FileCantBeDeletedException;
import com.dmkyr20.filemanager.core.excepptions.UnexpectedException;
import com.dmkyr20.filemanager.utils.BiThrowableAction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

record OperatorBasic(Verifier verifier) implements Operator {

    public static Operator getOperatorYes() {
        return new OperatorBasic(s -> true);
    }

    public static Operator getOperatorNot() {
        return new OperatorBasic(s -> false);
    }

    @Override
    public boolean delete(Path path) {
        return delete(path, true);
    }

    public boolean delete(Path path, boolean ask) {
        try {
            if(!ask || verifier.verify("Are you sure that you want to delete - " + path.getFileName() + "?")) {
                if (Files.isDirectory(path)) {
                    Files.walk(path, 1)
                            .skip(1)
                            .forEach(p -> {
                                if (!delete(p, false)) {
                                    throw new FileCantBeDeletedException();
                                }
                            });
                }
                Files.deleteIfExists(path);
                return true;
            }
            return false;

        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public boolean createDir(Path parent, String filename) {
       return create(parent, filename, (p, f) -> {
           Files.createDirectory(p.resolve(filename));
           return true;
       });
    }

    @Override
    public boolean createFile(Path parent, String filename) {
        return create(parent, filename, (p, f) -> {
            Files.createFile(p.resolve(filename));
            return true;
        });
    }

    private boolean create(Path parent, String filename,
                           BiThrowableAction<Path, String, Boolean, IOException> action) {
        var path = parent.resolve(filename);
        try {
            if(Files.exists(path)) {
                if(verifier.verify("File/Directory - " + path.getFileName() +
                        " already exists, do you want to overwrite it?")) {
                    if( ! delete(path, false)) {
                        throw new FileCantBeDeletedException();
                    }
                } else {
                    return false;
                }
            }
            Boolean result = action.perform(parent, filename);
            return result == null || result;
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
    }
}
