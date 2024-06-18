// Algorithm2.java
package com.example.telemetry.algorithms;

import com.example.telemetry.annotations.AlgorithmName;
import com.example.telemetry.model.IAlgorithm;

import java.util.Map;

@AlgorithmName("Algorithm2")
public class Algorithm2 implements IAlgorithm {
    private Map<String, String> parameters;
    private String inputData;

    @Override
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void setInput(String inputData) {
        this.inputData = inputData;
    }

    @Override
    public String solve() {
        // Логика решения для Algorithm2
        return "Output from Algorithm2 with input: " + inputData;
    }
}
