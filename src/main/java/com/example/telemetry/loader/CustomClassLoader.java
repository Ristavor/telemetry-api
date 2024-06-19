package com.example.telemetry.loader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassLoader extends URLClassLoader {

    public CustomClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public static CustomClassLoader fromDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("The provided file must be a directory");
        }

        File[] jarFiles = directory.listFiles((dir, name) -> name.endsWith(".jar"));
        URL[] urls = new URL[jarFiles.length];
        for (int i = 0; i < jarFiles.length; i++) {
            urls[i] = jarFiles[i].toURI().toURL();
        }
        return new CustomClassLoader(urls, Thread.currentThread().getContextClassLoader());
    }
}
