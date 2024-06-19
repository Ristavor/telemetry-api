package com.example.telemetry.algorithms;

import com.example.common.annotations.AlgorithmName;
import com.example.common.model.IAlgorithm;

import java.util.HashMap;
import java.util.Map;

@AlgorithmName("Algorithm1")
public class Algorithm1 implements IAlgorithm {
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
        // Логика решения для Algorithm1
        return "Output from Algorithm1 with input: " + inputData;
    }

    @Override
    public Map<String, String> getParameters() {
        Map<String, String> params = new HashMap<>();
        params.put("param1", "Description of param1 for Algorithm1");
        params.put("param2", "Description of param2 for Algorithm1");
        return params;
    }
}
