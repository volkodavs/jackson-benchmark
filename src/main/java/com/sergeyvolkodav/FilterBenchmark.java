package com.sergeyvolkodav;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
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
    public void jacksonStreamRead(ExecutionPlan plan, Blackhole blackhole) throws IOException {

        for (int i = 0; i < plan.getJsons().size(); i++) {
            String json = plan.getJsons().get(i);
            Event event = new Event();
            try (JsonParser parser = new JsonFactory()
                    .createParser(json)) {
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldname = parser.getCurrentName();

                    if (Objects.isNull(fieldname)) {
                        continue;
                    }
                    if (fieldname.equals("id")) {
                        parser.nextToken();
                        event.setId(parser.getLongValue());
                        continue;
                    }
                    if (fieldname.equals("name")) {
                        parser.nextToken();
                        event.setName(parser.getText());
                    }
                }
            }
            blackhole.consume(event);
        }
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jacksonStreamWrite(ExecutionPlan plan, Blackhole blackhole) throws IOException {
        for (int i = 0; i < plan.getJsons().size(); i++) {
            Event event = plan.getEvents().get(i);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try (JsonGenerator generator =
                    plan.getMapper().getFactory().createGenerator(
                            outputStream, JsonEncoding.UTF8)) {
                generator.writeStartObject();

                generator.writeNumberField("id", event.getId());
                generator.writeStringField("name", event.getName());

                generator.writeEndObject();
            }
            outputStream.close();

            blackhole.consume(outputStream.toString());
        }
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
