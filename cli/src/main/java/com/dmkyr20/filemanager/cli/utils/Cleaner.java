package com.dmkyr20.filemanager.cli.utils;

public class Cleaner {
    private static void cleanConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
