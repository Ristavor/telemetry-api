// AlgorithmFactory.java
package com.example.telemetry.factory;

import com.example.telemetry.algorithms.Algorithm1;
import com.example.telemetry.algorithms.Algorithm2;
import com.example.common.model.IAlgorithm;

public class AlgorithmFactory {
    public static IAlgorithm getAlgorithm(String algorithmName) {
        switch (algorithmName) {
            case "Algorithm1":
                return new Algorithm1();
            case "Algorithm2":
                return new Algorithm2();
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithmName);
        }
    }
}
