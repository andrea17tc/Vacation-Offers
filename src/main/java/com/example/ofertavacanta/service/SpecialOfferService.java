package com.example.ofertavacanta.service;

import com.example.ofertavacanta.domain.SpecialOffer;
import com.example.ofertavacanta.repository.SpecialOfferDBRepository;
import com.example.ofertavacanta.utils.Observable;
import com.example.ofertavacanta.utils.Observer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

public class SpecialOfferService implements Observable {

    private SpecialOfferDBRepository specialOfferDBRepository;
    private List<Observer> observers = new ArrayList<>();

    public SpecialOfferService(SpecialOfferDBRepository specialOfferDBRepository) {
        this.specialOfferDBRepository = specialOfferDBRepository;
    }

    public Iterable<SpecialOffer> findAll(){
        return specialOfferDBRepository.findAll();
    }

    public Iterable<SpecialOffer> findForHotel(Double hotelID){
        return StreamSupport.stream(findAll().spliterator(), false).
                filter(s-> Objects.equals(s.getHotelID(),hotelID)).toList();
    }
    public Iterable<SpecialOffer> findDateOffers(LocalDate startDate, LocalDate endDate, Double hotelID){
        notifyObservers();
        return StreamSupport.stream(findForHotel(hotelID).spliterator(), false).
                filter(s->!s.getStartDate().isAfter(startDate) && !s.getEndDate().isBefore(endDate) && s.getStartDate().isAfter(LocalDate.now())).toList();
    }

    public Iterable<SpecialOffer> findPercentsOffers(Integer nr){
        return StreamSupport.stream(findAll().spliterator(), false).filter(s->s.getPercents()<nr).toList();
    }
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);

    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
