package com.example.ofertavacanta.service;

import com.example.ofertavacanta.domain.Reservation;
import com.example.ofertavacanta.repository.ReservationDBRepository;
import com.example.ofertavacanta.utils.Observable;
import com.example.ofertavacanta.utils.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationService {

    private ReservationDBRepository reservationDBRepository;

    public ReservationService(ReservationDBRepository reservationDBRepository) {
        this.reservationDBRepository = reservationDBRepository;
    }

    public Optional<Reservation> save(Reservation entity){
        return reservationDBRepository.save(entity);
    }

}
