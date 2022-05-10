package com.example.covoitonsapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RidesDto {

    private String itinary;
    private LocalDateTime departureTime;
    private LocalDate departureDay;

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(LocalDate departureDay) {
        this.departureDay = departureDay;
    }

    private Integer places;

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public String getItinary() {
        return itinary;
    }

    public void setItinary(String itinary) {
        this.itinary = itinary;
    }

/*    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }*/

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }
}
