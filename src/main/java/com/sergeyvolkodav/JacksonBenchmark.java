package com.sergeyvolkodav;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@Measurement(iterations = 10, time = 1, timeUnit = MILLISECONDS)
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
            byte[] bytes = plan.getBigJsons().get(i);
            Event event = new Event();

            try (JsonParser parser = plan.getFactory()
                    .createParser(bytes)) {
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String eventFieldName = parser.getCurrentName();

                    if (Objects.isNull(eventFieldName)) {
                        //do nothing
                    } else if (eventFieldName.equals("id")) {
                        parser.nextToken();
                        event.setId(parser.getLongValue());
                    } else if (eventFieldName.equals("name")) {
                        parser.nextToken();
                        event.setName(parser.getText());
                    } else if (eventFieldName.equals("sportId")) {
                        parser.nextToken();
                        event.setSportId(parser.getLongValue());
                    } else if (eventFieldName.equals("inRunning")) {
                        parser.nextToken();
                        event.setInRunning(parser.getBooleanValue());
                    } else if (eventFieldName.equals("allowLiveBetting")) {
                        parser.nextToken();
                        event.setInRunning(parser.getBooleanValue());
                    } else if (eventFieldName.equals("startTime")) {
                        parser.nextToken();
                        event.setStartTime(new Date(parser.getLongValue()));
                    } else if (eventFieldName.equals("markets")) {
                        event.setMarkets(parseMarkets(parser));
                    } else if (eventFieldName.equals("eventParticipants")) {
                        event.setEventParticipants(parseEventParticipant(parser));
                    }
                }
            }
            blackhole.consume(event);
        }
    }

    private static List<Market> parseMarkets(JsonParser parser) throws IOException {
        List<Market> markets = new ArrayList<>();
        if (parser.nextToken() == JsonToken.START_ARRAY) {
            while (parser.nextToken() != JsonToken.END_ARRAY) {
                Market market = new Market();
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String marketFieldName = parser.getCurrentName();

                    if (marketFieldName.equals("id")) {
                        parser.nextToken();
                        market.setId(parser.getLongValue());
                    } else if (marketFieldName.equals("eventId")) {
                        parser.nextToken();
                        market.setEventId(parser.getLongValue());
                    } else if (marketFieldName.equals("name")) {
                        parser.nextToken();
                        market.setName(parser.getText());
                    } else if (marketFieldName.equals("inRunning")) {
                        parser.nextToken();
                        market.setInRunning(parser.getBooleanValue());
                    } else if (marketFieldName.equals("allowLiveBetting")) {
                        parser.nextToken();
                        market.setAllowLiveBetting(parser.getBooleanValue());
                    } else if (marketFieldName.equals("winners")) {
                        parser.nextToken();
                        market.setWinners(parser.getIntValue());
                    } else if (marketFieldName.equals("value")) {
                        parser.nextToken();
                        market.setValue(parser.getDoubleValue());
                    } else if (marketFieldName.equals("runners")) {
                        market.setRunners(parseRunner(parser));
                    }
                }
                markets.add(market);
            }
        }
        return markets;
    }

    private static List<EventParticipant> parseEventParticipant(JsonParser parser) throws IOException {
        List<EventParticipant> eventParticipants = new ArrayList<>();

        if (parser.nextToken() == JsonToken.START_ARRAY) {

            while (parser.nextToken() != JsonToken.END_ARRAY) {
                EventParticipant eventParticipant = new EventParticipant();
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String participantFieldName = parser.getCurrentName();

                    if (participantFieldName.equals("id")) {
                        parser.nextToken();
                        eventParticipant.setId(parser.getLongValue());
                    } else if (participantFieldName.equals("eventId")) {
                        parser.nextToken();
                        eventParticipant.setEventId(parser.getLongValue());
                    } else if (participantFieldName.equals("participantName")) {
                        parser.nextToken();
                        eventParticipant.setParticipantName(parser.getText());
                    } else if (participantFieldName.equals("jockeyName")) {
                        parser.nextToken();
                        eventParticipant.setJockeyName(parser.getText());
                    } else if (participantFieldName.equals("trainerName")) {
                        parser.nextToken();
                        eventParticipant.setTrainerName(parser.getText());
                    } else if (participantFieldName.equals("number")) {
                        parser.nextToken();
                        eventParticipant.setNumber(parser.getIntValue());
                    }
                }
                eventParticipants.add(eventParticipant);
            }
        }
        return eventParticipants;
    }

    private static List<Runner> parseRunner(JsonParser parser) throws IOException {
        List<Runner> runners = new ArrayList<>();
        if (parser.nextToken() == JsonToken.START_ARRAY) {

            while (parser.nextToken() != JsonToken.END_ARRAY) {
                Runner runner = new Runner();
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String currentName = parser.getCurrentName();

                    if (currentName.equals("id")) {
                        parser.nextToken();
                        runner.setId(parser.getLongValue());
                    } else if (currentName.equals("eventId")) {
                        parser.nextToken();
                        runner.setEventId(parser.getLongValue());
                    } else if (currentName.equals("marketId")) {
                        parser.nextToken();
                        runner.setMarketId(parser.getLongValue());
                    } else if (currentName.equals("eventParticipantId")) {
                        parser.nextToken();
                        runner.setEventParticipantId(parser.getLongValue());
                    } else if (currentName.equals("name")) {
                        parser.nextToken();
                        runner.setName(parser.getText());
                    } else if (currentName.equals("withdrawn")) {
                        parser.nextToken();
                        runner.setWithdrawn(parser.getBooleanValue());
                    } else if (currentName.equals("value")) {
                        parser.nextToken();
                        runner.setValue(parser.getDoubleValue());
                    }
                }
                runners.add(runner);
            }
        }
        return runners;
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
