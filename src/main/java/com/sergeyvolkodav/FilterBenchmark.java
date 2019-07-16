package com.sergeyvolkodav;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Warmup(iterations = 10)
@Measurement(iterations = 10, time = 1, timeUnit = SECONDS)
public class FilterBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jacksonStreamRead(ExecutionPlan plan, Blackhole blackhole) {

        //TODO: blackhole.consume();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jacksonStreamWrite(ExecutionPlan plan, Blackhole blackhole) {

        //TODO: blackhole.consume();
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jsonWrite(ExecutionPlan plan, Blackhole blackhole) throws JsonProcessingException {

        for (int i = 0; i < plan.getJsons().size(); i++) {
            Event event = plan.getEvents().get(i);
            String json = plan.getMapper().writeValueAsString(event);

            blackhole.consume(json);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jsonRead(ExecutionPlan plan, Blackhole blackhole) throws IOException {

        for (int i = 0; i < plan.getJsons().size(); i++) {
            String json = plan.getJsons().get(i);
            Event event = plan.getMapper().readValue(json, Event.class);

            blackhole.consume(event);
        }
    }
}
