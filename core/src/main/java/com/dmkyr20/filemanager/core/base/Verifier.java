package com.dmkyr20.filemanager.core.base;

import com.dmkyr20.filemanager.core.excepptions.UnexpectedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
public interface Verifier {
    List<String> YES_ANSWERS = Arrays.asList("y", "yes", "true");

    boolean verify(String question);

    Verifier DEFAULT_VERIFIER = s -> {
        System.out.println(s);
        try(var reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            var value = reader.readLine();
            return YES_ANSWERS.stream().anyMatch(value::equalsIgnoreCase);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
    };
}
