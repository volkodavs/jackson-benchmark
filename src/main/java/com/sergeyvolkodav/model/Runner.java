package com.sergeyvolkodav.model;

public class Runner {

    private Long id;
    private Long eventId;
    private Long marketId;
    private Long eventParticipantId;
    private String name;
    private boolean withdrawn;
    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getEventParticipantId() {
        return eventParticipantId;
    }

    public void setEventParticipantId(Long eventParticipantId) {
        this.eventParticipantId = eventParticipantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(boolean withdrawn) {
        this.withdrawn = withdrawn;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Runner.class.getSimpleName() + " {" +
                "id=" + id +
                ", eventId=" + eventId +
                ", marketId=" + marketId +
                ", eventParticipantId=" + eventParticipantId +
                ", name=" + name +
                ", withdrawn=" + withdrawn +
                ", value=" + value +
                "}";
    }
}
