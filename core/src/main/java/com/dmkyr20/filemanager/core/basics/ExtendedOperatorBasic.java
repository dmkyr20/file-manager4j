package com.dmkyr20.filemanager.core.basics;

import com.dmkyr20.filemanager.core.base.ExtendedOperator;
import com.dmkyr20.filemanager.core.base.Verifier;
import com.dmkyr20.filemanager.core.excepptions.UnreachableFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

class ExtendedOperatorBasic extends OperatorBasic implements ExtendedOperator {

    private final Predicate<Path> verifyOutputOverwriting = p ->
        verifier.verify("File (" + p.getFileName() +
                ") exists, do you want to overwrite it?");

    public ExtendedOperatorBasic(Verifier verifier) {
        super(verifier);
    }

    public static ExtendedOperator getOperatorYes() {
        return new ExtendedOperatorBasic(s -> true);
    }

    public static ExtendedOperator getOperatorNot() {
        return new ExtendedOperatorBasic(s -> false);
    }

    @Override
    public boolean move(Path p1, Path p2) {
        if (!Files.exists(p1)) {
            throw new UnreachableFileException(p1);
        }

        if (Files.exists(p2)) {
            if (! verifyOutputOverwriting.test(p2)) {
                return false;
            }
            delete(p2);
        }

        try {
            Files.move(p1, p2);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean copy(Path p1, Path p2) {
        if (!Files.exists(p1)) {
            throw new UnreachableFileException(p1);
        }
        if (Files.exists(p2)) {
            if (! verifyOutputOverwriting.test(p2)) {
                return false;
            }
            delete(p2);
        }

        try {
            Files.copy(p1, p2);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
