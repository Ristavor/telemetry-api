// AlgorithmRequest.java
package com.example.telemetry.model;

import java.util.Map;

public class AlgorithmRequest {
    private String algorithmName;
    private Map<String, String> parameters;
    private String inputData;

    // Getters
    public String getAlgorithmName() {
        return algorithmName;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getInputData() {
        return inputData;
    }

    // Setters
    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }
}
