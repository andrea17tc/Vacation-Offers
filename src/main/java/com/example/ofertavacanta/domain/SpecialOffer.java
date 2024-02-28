package com.example.ofertavacanta.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class SpecialOffer extends Entity<Double> {
    private Double hotelID;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer percents;

    public SpecialOffer(Double hotelID, LocalDate startDate, LocalDate endDate, Integer percents) {
        this.hotelID = hotelID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percents = percents;
    }

    public Double getHotelID() {
        return hotelID;
    }

    public void setHotelID(Double hotelID) {
        this.hotelID = hotelID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getPercents() {
        return percents;
    }

    public void setPercents(Integer percents) {
        this.percents = percents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpecialOffer that = (SpecialOffer) o;
        return Objects.equals(hotelID, that.hotelID) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(percents, that.percents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hotelID, startDate, endDate, percents);
    }

    @Override
    public String toString() {
        return startDate +
                " - " + endDate;
    }
}
