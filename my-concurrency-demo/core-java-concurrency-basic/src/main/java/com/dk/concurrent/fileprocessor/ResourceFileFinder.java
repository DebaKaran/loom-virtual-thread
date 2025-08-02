package com.dk.concurrent.fileprocessor;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceFileFinder {

    public static List<String> findFiles(final String folderName) {
        List<String> files = new ArrayList<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(folderName);

        if(url == null) {
            throw new IllegalArgumentException("Directory not found: "+folderName);
        }

        try {
            Path path = Paths.get(url.toURI());
            try (Stream<Path> paths = Files.list(path)){
                files = paths
                        .filter(Files::isRegularFile)
                        .map(p -> folderName+"/"+p.getFileName().toString())
                        .collect(Collectors.toUnmodifiableList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
