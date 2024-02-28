package com.example.ofertavacanta.domain;

import java.util.Objects;

public class Hotel extends Entity<Double> {
    private String locationName;
    private String hotelName;
    private Integer noRooms;
    private Double pricePerNight;


    public Hotel(String locationName, String hotelName, Integer noRooms, Double pricePerNight) {
        this.locationName = locationName;
        this.hotelName = hotelName;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
    }


    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(Integer noRooms) {
        this.noRooms = noRooms;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(locationName, hotel.locationName) && Objects.equals(hotelName, hotel.hotelName) && Objects.equals(noRooms, hotel.noRooms) && Objects.equals(pricePerNight, hotel.pricePerNight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), locationName, hotelName, noRooms, pricePerNight);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "locationName=" + locationName +
                ", hotelName='" + hotelName + '\'' +
                ", noRooms=" + noRooms +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}
