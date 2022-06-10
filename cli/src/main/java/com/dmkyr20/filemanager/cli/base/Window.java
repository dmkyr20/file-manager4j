package com.dmkyr20.filemanager.cli.base;

public interface Window {
    void showFiles();
    void open(String filename);
    void back();
    void createFile(String filename);
    void delete(String filename);
}
