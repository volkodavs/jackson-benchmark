package com.sergeyvolkodav;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ExecutionPlan {

    //Raw input is preferable over String
    private List<byte[]> jsons;
    private List<Sport> sports;
    private ObjectMapper mapper;
    private JsonFactory factory;


    @Param({"10", "100", "1000", "10000", "100000", "1000000"})
    int arraySize;

    @Setup(Level.Iteration)
    public void setUp() throws JsonProcessingException {
        jsons = new ArrayList<>();
        sports = new ArrayList<>();
        mapper = new ObjectMapper();
        factory = new JsonFactory();

        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            Sport sport = new Sport();
            sport.setId(random.nextLong());
            sport.setName(java.util.UUID.randomUUID().toString());

            sports.add(sport);
            jsons.add(mapper.writeValueAsBytes(sport));
        }
    }

    public List<byte[]> getJsons() {
        return jsons;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public JsonFactory getFactory() {
        return factory;
    }
}
