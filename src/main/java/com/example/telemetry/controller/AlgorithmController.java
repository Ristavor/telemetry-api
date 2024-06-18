// AlgorithmController.java
package com.example.telemetry.controller;

import com.example.telemetry.model.Algorithm;
import com.example.telemetry.model.AlgorithmRequest;
import com.example.telemetry.model.IAlgorithm;
import com.example.telemetry.registry.AlgorithmRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/algorithms")
public class AlgorithmController {

    private List<Algorithm> algorithms = new ArrayList<>();

    public AlgorithmController() {
        // Пример алгоритмов
        Algorithm algorithm1 = new Algorithm();
        algorithm1.setName("Algorithm1");
        Map<String, String> params1 = new HashMap<>();
        params1.put("param1", "description1");
        params1.put("param2", "description2");
        algorithm1.setParameters(params1);

        Algorithm algorithm2 = new Algorithm();
        algorithm2.setName("Algorithm2");
        Map<String, String> params2 = new HashMap<>();
        params2.put("paramA", "descriptionA");
        params2.put("paramB", "descriptionB");
        algorithm2.setParameters(params2);

        algorithms.add(algorithm1);
        algorithms.add(algorithm2);
    }

    @GetMapping
    public List<Algorithm> getAllAlgorithms() {
        return algorithms;
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
