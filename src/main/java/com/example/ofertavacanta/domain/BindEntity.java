package com.example.ofertavacanta.domain;

import java.time.LocalDate;

public class BindEntity {
    private String hotelName;
    private String locationName;
    private LocalDate startDate;
    private LocalDate endDate;

    public BindEntity(String hotelName, String locationName, LocalDate startDate, LocalDate endDate) {
        this.hotelName = hotelName;
        this.locationName = locationName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "BindEntity{" +
                "hotelName='" + hotelName + '\'' +
                ", locationName='" + locationName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getLocationName() {
        return locationName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
