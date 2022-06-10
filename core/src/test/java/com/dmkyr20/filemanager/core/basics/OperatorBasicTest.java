package com.dmkyr20.filemanager.core.basics;

import com.dmkyr20.filemanager.core.base.Operator;
import com.dmkyr20.filemanager.utils.TestResourceLocator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class OperatorBasicTest {

    @Test
    void shouldCreateDir() throws IOException {
        var intiPath = TestResourceLocator.getResourceFile("root");
        Operator operator = OperatorBasic.getOperatorYes();

        var result = operator.createDir(intiPath, "testDir");

        assertTrue(result);
        assertTrue(Files.exists(intiPath.resolve("testDir")));
        Files.deleteIfExists(intiPath.resolve("testDir"));
    }

    @Test
    void shouldCreateFile() throws IOException {
        var intiPath = TestResourceLocator.getResourceFile("root");
        Operator operator = OperatorBasic.getOperatorYes();

        var result = operator.createFile(intiPath, "testFile");

        assertTrue(result);
        assertTrue(Files.exists(intiPath.resolve("testFile")));
        Files.deleteIfExists(intiPath.resolve("testFile"));
    }

    @Test
    void shouldBeAbleToOverwriteFile() throws IOException {
        var intiPath = TestResourceLocator.getResourceFile("root");
        Operator operator = OperatorBasic.getOperatorYes();
        Files.createDirectory(intiPath.resolve("temp"));
        Files.createFile(intiPath.resolve("temp").resolve("temp.txt"));
        try {

            var output = operator.createFile(intiPath.resolve("temp"), "temp.txt");

            assertTrue(output);
        } finally {
            Files.deleteIfExists(intiPath.resolve("temp").resolve("temp.txt"));
            Files.deleteIfExists(intiPath.resolve("temp"));
        }
    }

    @Test
    void shouldBeAbleToOverwriteDir() throws IOException {
        var intiPath = TestResourceLocator.getResourceFile("root");
        Operator operator = OperatorBasic.getOperatorYes();
        Files.createDirectory(intiPath.resolve("temp"));
        Files.createFile(intiPath.resolve("temp").resolve("temp.txt"));
        try {

            var output = operator.createDir(intiPath, "temp");

            assertTrue(output);
        } finally {
            Files.deleteIfExists(intiPath.resolve("temp").resolve("temp.txt"));
            Files.deleteIfExists(intiPath.resolve("temp"));
        }
    }

    @Test
    void shouldAskBeforeOverwrite() throws IOException{
        var intiPath = TestResourceLocator.getResourceFile("root");
        var isAsked = new AtomicBoolean(false);
        Operator operator = new OperatorBasic(s -> {
            isAsked.set(true);
            return true;
        });
        Files.createDirectory(intiPath.resolve("temp"));
        Files.createFile(intiPath.resolve("temp").resolve("temp.txt"));
        try {

            operator.createDir(intiPath, "temp");

            assertTrue(isAsked.get());
        } finally {
            Files.deleteIfExists(intiPath.resolve("temp").resolve("temp.txt"));
            Files.deleteIfExists(intiPath.resolve("temp"));
        }
    }

    @Test
    void shouldNotOverwriteIfAnswerNot() throws IOException {
        var intiPath = TestResourceLocator.getResourceFile("root");
        Operator operator = OperatorBasic.getOperatorNot();
        var file = Files.createFile(intiPath.resolve("temp.txt"));
        Files.writeString(file, "Something");
        try {

            var result = operator.createFile(intiPath, "temp.txt");

            assertFalse(result);
            assertEquals("Something", Files.readString(file));
        } finally {
            Files.deleteIfExists(intiPath.resolve("temp.txt"));
        }
    }
}