package com.sergeyvolkodav;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergeyvolkodav.model.Event;
import com.sergeyvolkodav.model.EventParticipant;
import com.sergeyvolkodav.model.Market;
import com.sergeyvolkodav.model.Runner;
import com.sergeyvolkodav.model.Sport;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ExecutionPlan {

    //Raw input is preferable over String
    private List<byte[]> tinyJsons;
    private List<Sport> tinyObjects;

    //Raw input is preferable over String
    private List<byte[]> bigJsons;
    private List<Event> bigObjects;

    private ObjectMapper mapper;
    private JsonFactory factory;


    @Param({"1000", "10000", "100000"})
    int arraySize;

    @Setup(Level.Iteration)
    public void setUp() throws JsonProcessingException {
        tinyJsons = new ArrayList<>();
        tinyObjects = new ArrayList<>();
        bigJsons = new ArrayList<>();
        bigObjects = new ArrayList<>();

        mapper = new ObjectMapper();
        factory = new JsonFactory();

        populateTinyObjects();
        populateBigObjects();
    }

    public static void main(String[] args) throws JsonProcessingException {
        ExecutionPlan executionPlan = new ExecutionPlan();
        executionPlan.arraySize = 1;

        executionPlan.setUp();
    }

    private void populateBigObjects() throws JsonProcessingException {
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            Event event = new Event();
            event.setId(random.nextLong());
            event.setName(java.util.UUID.randomUUID().toString());
            event.setStartTime(new Date());
            event.setInRunning(true);
            event.setSportId(random.nextLong());
            event.setAllowLiveBetting(true);
            event.setMarkets(buildMarket(10));
            event.setEventParticipants(buildEventParticipant(20));

            bigObjects.add(event);
            bigJsons.add(mapper.writeValueAsBytes(event));
        }
    }

    private List<Market> buildMarket(int size) {
        Random random = new Random();
        List<Market> markets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Market market = new Market();
            market.setId(random.nextLong());
            market.setAllowLiveBetting(true);
            market.setEventId(random.nextLong());
            market.setInRunning(false);
            market.setName(java.util.UUID.randomUUID().toString());
            market.setValue(random.nextDouble());
            market.setWinners(2);
            market.setRunners(buildRunners(20));

            markets.add(market);
        }
        return markets;
    }

    private List<Runner> buildRunners(int size) {
        Random random = new Random();
        List<Runner> runners = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Runner runner = new Runner();
            runner.setEventId(random.nextLong());
            runner.setEventParticipantId(random.nextLong());
            runner.setId(random.nextLong());
            runner.setMarketId(random.nextLong());
            runner.setName(java.util.UUID.randomUUID().toString());
            runner.setWithdrawn(false);
            runner.setValue(random.nextDouble());

            runners.add(runner);
        }
        return runners;
    }

    private List<EventParticipant> buildEventParticipant(int size) {
        Random random = new Random();
        List<EventParticipant> eventParticipants = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            EventParticipant eventParticipant = new EventParticipant();
            eventParticipant.setEventId(random.nextLong());
            eventParticipant.setId(random.nextLong());
            eventParticipant.setNumber(random.nextInt());
            eventParticipant.setJockeyName(java.util.UUID.randomUUID().toString());
            eventParticipant.setTrainerName(java.util.UUID.randomUUID().toString());
            eventParticipant.setParticipantName(java.util.UUID.randomUUID().toString());

            eventParticipants.add(eventParticipant);
        }
        return eventParticipants;
    }


    private void populateTinyObjects() throws JsonProcessingException {
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            Sport sport = new Sport();
            sport.setId(random.nextLong());
            sport.setName(java.util.UUID.randomUUID().toString());

            tinyObjects.add(sport);
            tinyJsons.add(mapper.writeValueAsBytes(sport));
        }
    }

    public List<byte[]> getTinyJsons() {
        return tinyJsons;
    }

    public List<Sport> getTinyObjects() {
        return tinyObjects;
    }

    public List<byte[]> getBigJsons() {
        return bigJsons;
    }

    public List<Event> getBigObjects() {
        return bigObjects;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public JsonFactory getFactory() {
        return factory;
    }
}
