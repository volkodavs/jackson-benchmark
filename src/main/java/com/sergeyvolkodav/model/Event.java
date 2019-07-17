package com.sergeyvolkodav.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Event {

    private Long id;
    private Long sportId;
    private Long[] categoryIds;
    private String name;
    private Instant startTime;
    private boolean inRunning;
    private boolean allowLiveBetting;
    private List<Market> markets;
    private List<EventParticipant> eventParticipants;

    public Event() {
        markets = new ArrayList<>();
        eventParticipants = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public Long[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Long[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public boolean isInRunning() {
        return inRunning;
    }

    public void setInRunning(boolean inRunning) {
        this.inRunning = inRunning;
    }

    public boolean isAllowLiveBetting() {
        return allowLiveBetting;
    }

    public void setAllowLiveBetting(boolean allowLiveBetting) {
        this.allowLiveBetting = allowLiveBetting;
    }

    public List<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    public List<EventParticipant> getEventParticipants() {
        return eventParticipants;
    }

    public void setEventParticipants(List<EventParticipant> eventParticipants) {
        this.eventParticipants = eventParticipants;
    }

    @Override
    public String toString() {
        return Event.class.getSimpleName() + " {" +
                "id=" + id +
                ", sportId=" + sportId +
                ", categoryIds=" + Arrays.toString(categoryIds) +
                ", name=" + name +
                ", startTime=" + startTime +
                ", inRunning=" + inRunning +
                ", allowLiveBetting=" + allowLiveBetting +
                ", markets=" + markets +
                ", eventParticipants=" + eventParticipants +
                "}";
    }
}
