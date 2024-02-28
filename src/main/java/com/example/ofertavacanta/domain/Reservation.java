package com.example.ofertavacanta.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Reservation extends Entity<Double> {
    private Long clientID;
    private Double hotelID;
    private LocalDate startDate;
    private Integer noNights;

    public Reservation(Long clientID, Double hotelID, LocalDate startDate, Integer noNights) {
        this.clientID = clientID;
        this.hotelID = hotelID;
        this.startDate = startDate;
        this.noNights = noNights;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public double getHotelID() {
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

    public Integer getNoNights() {
        return noNights;
    }

    public void setNoNights(Integer noNights) {
        this.noNights = noNights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(clientID, that.clientID) && Objects.equals(hotelID, that.hotelID) && Objects.equals(startDate, that.startDate) && Objects.equals(noNights, that.noNights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientID, hotelID, startDate, noNights);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "clientID=" + clientID +
                ", hotelID=" + hotelID +
                ", startDate=" + startDate +
                ", noNights=" + noNights +
                '}';
    }
}
