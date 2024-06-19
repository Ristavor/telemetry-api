package com.example.telemetry.algorithms;

import com.example.common.annotations.AlgorithmName;
import com.example.common.model.IAlgorithm;

import java.util.HashMap;
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

    @Override
    public Map<String, String> getParameters() {
        Map<String, String> params = new HashMap<>();
        params.put("paramA", "Description of paramA for Algorithm2");
        params.put("paramB", "Description of paramB for Algorithm2");
        return params;
    }
}
