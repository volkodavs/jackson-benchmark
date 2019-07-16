package com.sergeyvolkodav;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ExecutionPlan {

    private List<String> jsons = new ArrayList<>();
    private List<Event> events = new ArrayList<>();

    private ObjectMapper mapper = new ObjectMapper();


    @Param({"10", "100", "1000", "10000", "100000", "1000000"})
    int arraySize;

    @Setup(Level.Iteration)
    public void setUp() throws JsonProcessingException {
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            Event event = new Event();
            event.setId(random.nextLong());
            event.setName(java.util.UUID.randomUUID().toString());

            events.add(event);
            jsons.add(mapper.writeValueAsString(event));
        }
    }

    public List<String> getJsons() {
        return jsons;
    }

    public List<Event> getEvents() {
        return events;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
