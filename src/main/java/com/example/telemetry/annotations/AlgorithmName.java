// AlgorithmName.java
package com.example.telemetry.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AlgorithmName {
    String value();
}
