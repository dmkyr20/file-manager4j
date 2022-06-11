package com.dmkyr20.filemanager.core.basics;

import com.dmkyr20.filemanager.core.base.Operator;
import com.dmkyr20.filemanager.utils.TestResourceLocator;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedOperatorBasicTest {

    public static final Operator defaultOperator = OperatorBasic.getOperatorYes();

    @Test
    void shouldMove() {
        var initPath = TestResourceLocator.getResourceFile("root");
        try {
            defaultOperator.createFile(initPath, "move.file");
            var operator = ExtendedOperatorBasic.getOperatorYes();

            var result = operator.move(initPath.resolve("move.file"), initPath.resolve("move1.file"));

            assertTrue(result);
            assertTrue(Files.exists(initPath.resolve("move1.file")));
            assertFalse(Files.exists(initPath.resolve("move.file")));
        } finally {
            defaultOperator.delete(initPath.resolve("move.file"));
            defaultOperator.delete(initPath.resolve("move1.file"));
        }
    }

    @Test
    void shouldMoveDirectory() {
        var initPath = TestResourceLocator.getResourceFile("root");
        try {
            defaultOperator.createDir(initPath, "move");
            defaultOperator.createFile(initPath.resolve("move"), "test1.txt");
            defaultOperator.createFile(initPath.resolve("move"), "test2.txt");
            var operator = ExtendedOperatorBasic.getOperatorYes();

            var result = operator.move(initPath.resolve("move"), initPath.resolve("move1"));

            assertTrue(result);
        } finally {
            defaultOperator.delete(initPath.resolve("move"));
            defaultOperator.delete(initPath.resolve("move1"));
        }

    }

    @Test
    void shouldReplaceDestinationIfYes() {
        var initPath = TestResourceLocator.getResourceFile("root");
        try {
            defaultOperator.createDir(initPath, "move");
            defaultOperator.createFile(initPath.resolve("move"), "test");
            defaultOperator.createFile(initPath, "moveFile");
            var operator = ExtendedOperatorBasic.getOperatorYes();

            var result = operator.move(initPath.resolve("moveFile"), initPath.resolve("move"));

            assertTrue(result);
            assertTrue(Files.exists(initPath.resolve("move")));
            assertTrue(Files.isRegularFile(initPath.resolve("move")));
            assertFalse(Files.exists(initPath.resolve("moveFIle")));
        } finally {
            defaultOperator.delete(initPath.resolve("move"));
            defaultOperator.delete(initPath.resolve("moveFile"));
        }
    }

    @Test
    void shouldCopyFile() {
        var initPath = TestResourceLocator.getResourceFile("root");
        try {
            defaultOperator.createFile(initPath, "copy1.file");
            var operator = ExtendedOperatorBasic.getOperatorYes();

            var result =operator.copy(initPath.resolve("copy1.file"), initPath.resolve("copy2.file"));

            assertTrue(result);
            assertTrue(Files.exists(initPath.resolve("copy1.file")));
            assertTrue(Files.exists(initPath.resolve("copy2.file")));
        } finally {
            defaultOperator.delete(initPath.resolve("copy1.file"));
            defaultOperator.delete(initPath.resolve("copy2.file"));
        }
    }
}