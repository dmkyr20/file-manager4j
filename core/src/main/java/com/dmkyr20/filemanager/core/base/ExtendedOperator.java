package com.dmkyr20.filemanager.core.base;

import java.nio.file.Path;

public interface ExtendedOperator extends Operator {
    boolean move(Path p1, Path p2);
    boolean copy(Path p1, Path p2);
}
