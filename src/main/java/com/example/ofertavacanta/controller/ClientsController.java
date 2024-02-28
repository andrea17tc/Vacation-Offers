package com.example.ofertavacanta.controller;

import com.example.ofertavacanta.HelloApplication;
import com.example.ofertavacanta.domain.*;
import com.example.ofertavacanta.service.*;
import com.example.ofertavacanta.utils.Observer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class ClientsController implements Observer{

    private HotelService hotelService;
    private SpecialOfferService specialOfferService;

    private ReservationService reservationService;

    private Client client;

    @FXML
    private TableView<BindEntity> offersTableView;
    @FXML
    private TableColumn<BindEntity,String> hotelNameColumn;
    @FXML
    private TableColumn<BindEntity,String> locationNameColumn;
    @FXML
    private TableColumn<BindEntity, LocalDate> startDateColumn;
    @FXML
    private TableColumn<BindEntity,LocalDate> endDateColumn;
    ObservableList<BindEntity> model = FXCollections.observableArrayList();



    public void setService( HotelService hotelService,SpecialOfferService specialOfferService, ReservationService reservationService)
    {
        this.specialOfferService=specialOfferService;
        this.hotelService = hotelService;
        this.reservationService = reservationService;
        initData();
    }

    public void initialize() {
        hotelNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHotelName()));
        locationNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocationName()));
        startDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));
        endDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEndDate()));

        offersTableView.setItems(model);
    }


    private void initData() {
        int percents = client.getFidelityGrade();
        List<SpecialOffer> specialOfferList =  new ArrayList<>(StreamSupport.stream(specialOfferService.findPercentsOffers(percents).spliterator(), false).toList());
        //System.out.println(specialOfferList);
        ObservableList<String> hotelNames = FXCollections.observableArrayList();
        ObservableList<String> locationNames = FXCollections.observableArrayList();
        ObservableList<LocalDate> startDateList = FXCollections.observableArrayList();
        ObservableList<LocalDate> endDateList = FXCollections.observableArrayList();

        for (SpecialOffer s: specialOfferList) {
            Hotel h = hotelService.findOne(s.getHotelID()).get();
            String hotelName = h.getHotelName();
            hotelNames.add(hotelName);
            String locationName = h.getLocationName();
            locationNames.add(locationName);
            startDateList.add(s.getStartDate());
            endDateList.add(s.getEndDate());
        }

        for(int i=0;i<hotelNames.size();i++)
        {
            BindEntity b = new BindEntity(hotelNames.get(i),locationNames.get(i),startDateList.get(i),endDateList.get(i));
            model.add(b);

        }

        offersTableView.setItems(model);
    }

    public void setClient(Client client){
        this.client = client;
    }


    public void reserveButtonClicked() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hotels-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HotelsController hotelsController = fxmlLoader.getController();
        hotelsController.setService(hotelService,specialOfferService, reservationService);
        hotelsController.setClient(client);
        stage.setTitle("Hotels");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void update() {
        updateClients();
    }

    public void updateClients() {
        // Show alert using JavaFX Alert or any other mechanism
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("Inca un utilizator cu hobby-ul tau a rezervat!");
        alert.showAndWait();
    }

}
