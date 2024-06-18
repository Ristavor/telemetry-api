// Algorithm.java
package com.example.telemetry.model;

import java.util.Map;

public class Algorithm {
    private String name;
    private Map<String, String> parameters;

    // Getters
    public String getName() {
        return name;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
