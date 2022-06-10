package com.dmkyr20.filemanager.utils;

import java.nio.file.Path;
import java.util.Objects;

public class TestResourceLocator {
    private static final String RESOURCES_TEST = "src/test/resources";
    private static final String RESOURCES_MAIN = "src/main/resources";

    public static ClassLoader getClassLoader() {
        return TestResourceLocator.class.getClassLoader();
    }

    public static Path getResourcesDir() {
        try {
            return Path.of(Objects
                            .requireNonNull(getClassLoader()
                                    .getResource(""))
                            .toURI())
                    .resolve("../..")
                    .resolve(RESOURCES_TEST)
                    .toRealPath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Path getResourceFile(String filepath) {
        return getResourcesDir().resolve(filepath);
    }

    public static Path getResourceFile(Path path) {
        return getResourcesDir().resolve(path);
    }
}
