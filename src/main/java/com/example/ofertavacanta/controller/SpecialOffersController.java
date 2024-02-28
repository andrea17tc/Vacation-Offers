package com.example.ofertavacanta.controller;

import com.example.ofertavacanta.domain.Client;
import com.example.ofertavacanta.domain.Hotel;
import com.example.ofertavacanta.domain.Reservation;
import com.example.ofertavacanta.domain.SpecialOffer;
import com.example.ofertavacanta.service.ReservationService;
import com.example.ofertavacanta.service.SpecialOfferService;
import com.example.ofertavacanta.utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.StreamSupport;

public class SpecialOffersController implements Observer {
    private SpecialOfferService specialOfferService;

    private ReservationService reservationService;
    private Hotel hotel;
    @FXML
    private ListView<SpecialOffer> specialOfferListView;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    private Client client;

    private LocalDate startDate;
    private LocalDate endDate;

    public void setHotel(Hotel selectedHotel) {
        hotel = selectedHotel;
    }

    public void setService(SpecialOfferService specialOfferService, ReservationService reservationService) {
        this.specialOfferService = specialOfferService;
        this.reservationService = reservationService;
        specialOfferService.addObserver(this);
    }

    public void initialize(){
    }


    public void searchDate() {
        startDate = startDatePicker.getValue();
        endDate = endDatePicker.getValue();
        if(startDate!=null && endDate!=null) {

            specialOfferListView.setItems(
                    FXCollections.observableArrayList( new ArrayList<>(StreamSupport.stream(specialOfferService.findDateOffers(startDate, endDate, hotel.getId()).spliterator(), false).toList()))
            );
        }
    }

    public void setClient(Client client) {
        this.client=client;
    }

    public void bookButtonHandler() {
        SpecialOffer s = specialOfferListView.getSelectionModel().getSelectedItem();
        if(s!=null)
        {
            int noNights = endDate.getDayOfMonth() - startDate.getDayOfMonth();
            Reservation r = new Reservation(client.getId(), hotel.getId(), startDate, noNights);
            reservationService.save(r);

        }

    }

    @Override
    public void update() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("Inca un utilizator cu hobby-ul tau a cautat!");
        alert.showAndWait();
    }
}







