package com.sergeyvolkodav;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonEncoding;
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
            Sport sport = new Sport();
            try (JsonParser parser = plan.getFactory()
                    .createParser(json)) {
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldname = parser.getCurrentName();

                    if (Objects.isNull(fieldname)) {
                    } else if (fieldname.equals("id")) {
                        parser.nextToken();
                        sport.setId(parser.getLongValue());
                    } else if (fieldname.equals("name")) {
                        parser.nextToken();
                        sport.setName(parser.getText());
                    }
                }
            }
            blackhole.consume(sport);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jacksonStreamWrite(ExecutionPlan plan, Blackhole blackhole) throws IOException {
        for (int i = 0; i < plan.getJsons().size(); i++) {
            Sport sport = plan.getSports().get(i);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try (JsonGenerator generator =
                    plan.getFactory().createGenerator(
                            outputStream, JsonEncoding.UTF8)) {
                generator.writeStartObject();

                generator.writeNumberField("id", sport.getId());
                generator.writeStringField("name", sport.getName());

                generator.writeEndObject();
            } finally {
                outputStream.close();
            }

            blackhole.consume(outputStream.toString());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jsonWrite(ExecutionPlan plan, Blackhole blackhole) throws JsonProcessingException {

        for (int i = 0; i < plan.getJsons().size(); i++) {
            Sport sport = plan.getSports().get(i);
            String json = plan.getMapper().writeValueAsString(sport);

            blackhole.consume(json);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jsonRead(ExecutionPlan plan, Blackhole blackhole) throws IOException {

        for (int i = 0; i < plan.getJsons().size(); i++) {
            String json = plan.getJsons().get(i);
            Sport sport = plan.getMapper().readValue(json, Sport.class);

            blackhole.consume(sport);
        }
    }
}
