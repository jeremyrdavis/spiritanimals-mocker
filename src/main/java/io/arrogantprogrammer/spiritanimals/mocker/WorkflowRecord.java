package io.arrogantprogrammer.spiritanimals.mocker;

import java.util.Optional;

public record WorkflowRecord(
        Long id,
        String name,
        String spiritAnimal,
        boolean liked,
        Optional<String> whatIs,
        Optional<String> poem,
        Optional<String> updatedPoem,
        Optional<String> feedback){}
