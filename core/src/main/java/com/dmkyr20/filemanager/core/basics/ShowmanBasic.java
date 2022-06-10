package com.dmkyr20.filemanager.core.basics;

import com.dmkyr20.filemanager.core.base.Showman;
import com.dmkyr20.filemanager.core.excepptions.UnexpectedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

class ShowmanBasic implements Showman {

    @Override
    public List<Path> showAll(Path path) {
        try(var walker = Files.walk(path, 1)) {
            return walker
                    .skip(1)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
    }
}
