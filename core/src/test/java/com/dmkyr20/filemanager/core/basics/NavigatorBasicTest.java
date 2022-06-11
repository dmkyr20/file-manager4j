package com.dmkyr20.filemanager.core.basics;

import com.dmkyr20.filemanager.core.base.Navigator;
import com.dmkyr20.filemanager.core.exceptions.UnreachableFileException;
import com.dmkyr20.filemanager.utils.TestResourceLocator;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class NavigatorBasicTest {

    @Test
    void shouldGoToDirectory() throws IOException {
        Path initPath = TestResourceLocator.getResourceFile("root");
        Navigator navigator = new NavigatorBasic(initPath);

        var goResult = navigator.go("subdir");
        var resultedPath = navigator.current();


        assertTrue(Files.isSameFile(initPath.resolve("subdir"), resultedPath));
        assertTrue(goResult);
    }

    @Test
    void shouldBack() throws IOException {
        Path initPath = TestResourceLocator.getResourceFile("root/subdir");
        Navigator navigator = new NavigatorBasic(initPath);

        var goResult = navigator.back();
        var resultedPath = navigator.current();

        assertTrue(Files.isSameFile(initPath.resolve(".."), resultedPath));
        assertTrue(goResult);
    }

    @Test
    void shouldThrowExceptionWhenFileUnreachable() throws IOException {
        Path initPath = TestResourceLocator.getResourceFile("root");
        Navigator navigator = new NavigatorBasic(initPath);

        assertThrows(UnreachableFileException.class, () -> navigator.go("absence"));
    }

    @Test
    void shouldReturnFalseIfTryingGoToRegularFile() throws IOException {
        Path initPath = TestResourceLocator.getResourceFile("root");
        Navigator navigator = new NavigatorBasic(initPath);

        var result = navigator.go("file1.txt");

        assertFalse(result);
    }
}