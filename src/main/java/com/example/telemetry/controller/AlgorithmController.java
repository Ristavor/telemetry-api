package com.example.telemetry.controller;

import com.example.common.model.IAlgorithm;
import com.example.telemetry.model.AlgorithmRequest;
import com.example.telemetry.registry.AlgorithmRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/algorithms")
public class AlgorithmController {

    @GetMapping
    public Map<String, Map<String, String>> getAllAlgorithms() {
        Map<String, Map<String, String>> response = new HashMap<>();
        Set<String> algorithmNames = AlgorithmRegistry.getRegisteredAlgorithms();

        for (String name : algorithmNames) {
            IAlgorithm algorithm = AlgorithmRegistry.getAlgorithm(name);
            response.put(name, algorithm.getParameters());
        }
        return response;
    }

    @PostMapping
    public String runAlgorithm(@RequestBody AlgorithmRequest request) {
        try {
            IAlgorithm algorithm = AlgorithmRegistry.getAlgorithm(request.getAlgorithmName());
            algorithm.setParameters(request.getParameters());
            algorithm.setInput(request.getInputData());
            return algorithm.solve();
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }
}
