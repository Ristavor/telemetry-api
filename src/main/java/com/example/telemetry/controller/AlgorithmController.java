package com.example.telemetry.controller;

import com.example.common.model.IAlgorithm;
import com.example.telemetry.registry.AlgorithmRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
