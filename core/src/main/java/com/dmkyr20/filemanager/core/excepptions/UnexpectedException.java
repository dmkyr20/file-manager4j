package com.dmkyr20.filemanager.core.excepptions;

public class UnexpectedException extends FileManagerException {
    public UnexpectedException(Throwable e) {
        super("This should never happen if you are using safety Window class", e);
    }
}
