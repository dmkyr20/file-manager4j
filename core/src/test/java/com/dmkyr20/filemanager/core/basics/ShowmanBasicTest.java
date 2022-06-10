package com.dmkyr20.filemanager.core.basics;

import com.dmkyr20.filemanager.core.base.Showman;
import com.dmkyr20.filemanager.utils.TestResourceLocator;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShowmanBasicTest {

    @Test
    void shouldShowAll() {
        var initPath = TestResourceLocator.getResourceFile("root");
        Showman showmanBasic = new ShowmanBasic();

        var expected = Arrays.asList(
                initPath.resolve("file1.txt"),
                initPath.resolve("subdir")
        );

        assertArrayEquals(expected.toArray(), showmanBasic.showAll(initPath).toArray());
    }
}