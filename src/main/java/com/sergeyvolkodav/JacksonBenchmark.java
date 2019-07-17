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
import com.sergeyvolkodav.model.Event;
import com.sergeyvolkodav.model.EventParticipant;
import com.sergeyvolkodav.model.Market;
import com.sergeyvolkodav.model.Price;
import com.sergeyvolkodav.model.Runner;
import com.sergeyvolkodav.model.Sport;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Warmup(iterations = 10)
@Measurement(iterations = 10, time = 1, timeUnit = SECONDS)
public class JacksonBenchmark {

    //*****************************************************************************************************************
    //***************************************    SMALL OBJECT    ******************************************************
    //*****************************************************************************************************************

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void tinyJacksonStreamRead(ExecutionPlan plan, Blackhole blackhole) throws IOException {

        for (int i = 0; i < plan.getTinyJsons().size(); i++) {
            byte[] bytes = plan.getTinyJsons().get(i);
            Sport sport = new Sport();
            try (JsonParser parser = plan.getFactory()
                    .createParser(bytes)) {
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
    public void tinyJacksonStreamWrite(ExecutionPlan plan, Blackhole blackhole) throws IOException {

        for (int i = 0; i < plan.getTinyJsons().size(); i++) {
            Sport sport = plan.getTinyObjects().get(i);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try (JsonGenerator generator =
                    plan.getFactory().createGenerator(
                            outputStream, JsonEncoding.UTF8)) {
                generator.writeStartObject();

                generator.writeNumberField("id", sport.getId());
                generator.writeStringField("name", sport.getName());

                generator.writeEndObject();
            }

            blackhole.consume(outputStream);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void tinyJsonWrite(ExecutionPlan plan, Blackhole blackhole) throws JsonProcessingException {

        for (int i = 0; i < plan.getTinyObjects().size(); i++) {
            Sport sport = plan.getTinyObjects().get(i);
            String json = plan.getMapper().writeValueAsString(sport);

            blackhole.consume(json);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void tinyJsonRead(ExecutionPlan plan, Blackhole blackhole) throws IOException {

        for (int i = 0; i < plan.getTinyJsons().size(); i++) {
            byte[] bytes = plan.getTinyJsons().get(i);
            Sport sport = plan.getMapper().readValue(bytes, Sport.class);

            blackhole.consume(sport);
        }
    }

    //*****************************************************************************************************************
    //***************************************      BIG OBJECT    ******************************************************
    //*****************************************************************************************************************


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void bigJacksonStreamRead(ExecutionPlan plan, Blackhole blackhole) throws IOException {

        for (int i = 0; i < plan.getBigJsons().size(); i++) {
            byte[] bytes = plan.getTinyJsons().get(i);
            Event event = new Event();

            try (JsonParser parser = plan.getFactory()
                    .createParser(bytes)) {
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldname = parser.getCurrentName();

                }
            }

            blackhole.consume(event);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void bigJacksonStreamWrite(ExecutionPlan plan, Blackhole blackhole) throws IOException {

        for (int i = 0; i < plan.getBigJsons().size(); i++) {
            Event event = plan.getBigObjects().get(i);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try (JsonGenerator generator =
                    plan.getFactory().createGenerator(
                            outputStream, JsonEncoding.UTF8)) {

                generator.writeStartObject();

                generator.writeNumberField("id", event.getId());
                generator.writeNumberField("sportId", event.getSportId());
                generator.writeArrayFieldStart("categoryIds");
                generator.writeArray(event.getCategoryIds(), 0, event.getCategoryIds().length);
                generator.writeEndArray();

                generator.writeStringField("name", event.getName());
                generator.writeStringField("inRunning", String.valueOf(event.isInRunning()));
                generator.writeStringField("allowLiveBetting", String.valueOf(event.isAllowLiveBetting()));
                generator.writeStringField("startTime", event.getStartTime().toString());

                buildEventParticipant(event, generator);
                buildMarkets(event, generator);

                generator.writeEndObject();
            }
            blackhole.consume(outputStream);
        }
    }

    private static void buildMarkets(Event event, JsonGenerator generator) throws IOException {
        generator.writeFieldName("markets");
        generator.writeStartArray();

        for (int i = 0; i < event.getMarkets().size(); i++) {
            Market market = event.getMarkets().get(i);

            generator.writeStartObject();
            generator.writeNumberField("id", market.getId());
            generator.writeNumberField("eventId", market.getEventId());
            generator.writeStringField("name", market.getName());
            generator.writeStringField("inRunning", String.valueOf(market.isInRunning()));
            generator.writeStringField("allowLiveBetting", String.valueOf(market.isAllowLiveBetting()));
            generator.writeNumberField("value", market.getValue());
            generator.writeNumberField("winners", market.getWinners());

            buildRunners(generator, market);

            generator.writeEndObject();
        }
        generator.writeEndArray();
    }

    private static void buildRunners(JsonGenerator generator, Market market) throws IOException {
        generator.writeFieldName("runners");
        generator.writeStartArray();
        for (int j = 0; j < market.getRunners().size(); j++) {
            Runner runner = market.getRunners().get(j);

            generator.writeStartObject();
            generator.writeNumberField("id", runner.getId());
            generator.writeNumberField("eventId", runner.getEventId());
            generator.writeNumberField("marketId", runner.getMarketId());
            generator.writeNumberField("eventParticipantId", runner.getEventParticipantId());
            generator.writeStringField("name", runner.getName());
            generator.writeStringField("withdrawn", String.valueOf(runner.isWithdrawn()));
            generator.writeNumberField("value", runner.getValue());

            buildPrices(generator, runner);

            generator.writeEndObject();
        }
        generator.writeEndArray();
    }

    private static void buildPrices(JsonGenerator generator, Runner runner) throws IOException {
        generator.writeFieldName("prices");
        generator.writeStartArray();
        for (int k = 0; k < runner.getPrices().size(); k++) {
            Price price = runner.getPrices().get(k);

            generator.writeStartObject();
            generator.writeStringField("amount", price.getAmount().toString());
            generator.writeStringField("odds", price.getAmount().toString());
            generator.writeEndObject();
        }
        generator.writeEndArray();
    }

    private static void buildEventParticipant(Event event, JsonGenerator generator) throws IOException {
        generator.writeFieldName("eventParticipants");
        generator.writeStartArray();
        for (int i = 0; i < event.getEventParticipants().size(); i++) {
            EventParticipant eventParticipant = event.getEventParticipants().get(i);

            generator.writeStartObject();
            generator.writeNumberField("id", eventParticipant.getId());
            generator.writeNumberField("eventId", eventParticipant.getEventId());
            generator.writeStringField("participantName", eventParticipant.getParticipantName());
            generator.writeStringField("jockeyName", eventParticipant.getJockeyName());
            generator.writeStringField("trainerName", eventParticipant.getTrainerName());
            generator.writeNumberField("number", eventParticipant.getNumber());
            generator.writeEndObject();
        }
        generator.writeEndArray();
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void bigJsonWrite(ExecutionPlan plan, Blackhole blackhole) throws JsonProcessingException {

        for (int i = 0; i < plan.getBigObjects().size(); i++) {
            Event event = plan.getBigObjects().get(i);
            String json = plan.getMapper().writeValueAsString(event);

            blackhole.consume(json);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void bigJsonRead(ExecutionPlan plan, Blackhole blackhole) throws IOException {

        for (int i = 0; i < plan.getBigJsons().size(); i++) {
            byte[] bytes = plan.getBigJsons().get(i);
            Event event = plan.getMapper().readValue(bytes, Event.class);

            blackhole.consume(event);
        }
    }
}
