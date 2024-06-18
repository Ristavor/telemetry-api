// AlgorithmRegistry.java
package com.example.telemetry.registry;

import com.example.telemetry.annotations.AlgorithmName;
import com.example.telemetry.model.IAlgorithm;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AlgorithmRegistry {

    private static Map<String, Class<? extends IAlgorithm>> algorithms = new HashMap<>();

    static {
        registerAlgorithms("com.example.telemetry.algorithms");
    }

    public static void registerAlgorithms(String packageName) {
        Reflections reflections = new Reflections(packageName);
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
}
