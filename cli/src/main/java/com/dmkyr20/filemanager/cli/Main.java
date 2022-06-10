package com.dmkyr20.filemanager.cli;

import com.dmkyr20.filemanager.cli.base.WindowImpl;

public class Main {
    public static void main(String[] args) {
        var wind = new WindowImpl();
        wind.showFiles();
    }
}
