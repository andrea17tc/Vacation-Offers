package com.example.ofertavacanta.service;

import com.example.ofertavacanta.domain.Hotel;
import com.example.ofertavacanta.repository.HotelDBRepository;
import com.example.ofertavacanta.utils.Observable;
import com.example.ofertavacanta.utils.Observer;

import java.util.*;
import java.util.stream.StreamSupport;

public class HotelService{
    private HotelDBRepository hotelDBRepository;


    public HotelService(HotelDBRepository hotelDBRepository) {
        this.hotelDBRepository = hotelDBRepository;
    }

    public Optional<Hotel> findOne(Double hotelID){
        return hotelDBRepository.findOne(hotelID);
    }

    public Iterable<Hotel> findAll(){
        return hotelDBRepository.findAll();
    }

    public Iterable<Hotel> getLocationHotels(String locationName){

        return StreamSupport.stream(findAll().spliterator(), false).
                filter(h->(Objects.equals(h.getLocationName(),locationName))).toList();
    }

    public Iterable<String> getLocations(){
        List<String> locations = new ArrayList<>();
        for (Hotel h: findAll()) {
            locations.add(h.getLocationName());
        }
        return StreamSupport.stream(locations.spliterator(),false).sorted().distinct().toList();
    }

}
