// AlgorithmController.java
package com.example.telemetry.controller;

import com.example.telemetry.model.Algorithm;
import com.example.telemetry.model.AlgorithmRequest;
import com.example.common.model.IAlgorithm;
import com.example.telemetry.registry.AlgorithmRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/algorithms")
public class AlgorithmController {

    @GetMapping
    public Set<String> getAllAlgorithms() {
        return AlgorithmRegistry.getRegisteredAlgorithms();
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
