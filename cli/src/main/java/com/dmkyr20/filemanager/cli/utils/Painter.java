package com.dmkyr20.filemanager.cli.utils;

import lombok.AllArgsConstructor;

public class Painter {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static String paint(String line, Color color) {
        return color.value + line + ANSI_RESET;
    }

    @AllArgsConstructor
    enum Color {
        RED(ANSI_RED),
        BLUE(ANSI_BLUE),
        GREEN(ANSI_GREEN)
        ;

        private final String value;
    }
}
