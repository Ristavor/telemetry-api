package com.example.telemetry.registry;

import com.example.common.annotations.AlgorithmName;
import com.example.common.model.IAlgorithm;
import com.example.telemetry.loader.CustomClassLoader;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AlgorithmRegistry {

    private static Map<String, Class<? extends IAlgorithm>> algorithms = new HashMap<>();

    static {
        try {
            // Загрузка алгоритмов из основного classpath
            registerAlgorithms("com.example.telemetry.algorithms");

            // Загрузка алгоритмов из JAR-файлов
            File algorithmsDir = new File("algorithms"); // Папка algorithms в корне проекта
            if (!algorithmsDir.exists()) {
                algorithmsDir.mkdirs();
            }
            CustomClassLoader customClassLoader = CustomClassLoader.fromDirectory(algorithmsDir);
            registerAlgorithmsFromJars(customClassLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void registerAlgorithms(String packageName) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(new TypeAnnotationsScanner(), new SubTypesScanner()));

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(AlgorithmName.class);

        for (Class<?> clazz : annotated) {
            AlgorithmName annotation = clazz.getAnnotation(AlgorithmName.class);
            algorithms.put(annotation.value(), (Class<? extends IAlgorithm>) clazz);
        }
    }

    private static void registerAlgorithmsFromJars(ClassLoader classLoader) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forClassLoader(classLoader))
                .setScanners(new TypeAnnotationsScanner(), new SubTypesScanner())
                .addClassLoader(classLoader));

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(AlgorithmName.class);

        for (Class<?> clazz : annotated) {
            AlgorithmName annotation = clazz.getAnnotation(AlgorithmName.class);
            algorithms.put(annotation.value(), (Class<? extends IAlgorithm>) clazz);
        }
    }

    public static IAlgorithm getAlgorithm(String name) {
        Class<? extends IAlgorithm> clazz = algorithms.get(name);
        if (clazz != null) {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create algorithm instance", e);
            }
        }
        throw new IllegalArgumentException("No such algorithm registered: " + name);
    }

    public static Set<String> getRegisteredAlgorithms() {
        return algorithms.keySet();
    }
}
