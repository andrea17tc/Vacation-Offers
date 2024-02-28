package com.example.ofertavacanta.controller;

import com.example.ofertavacanta.HelloApplication;
import com.example.ofertavacanta.domain.Client;
import com.example.ofertavacanta.domain.Hotel;
import com.example.ofertavacanta.service.HotelService;
import com.example.ofertavacanta.service.ReservationService;
import com.example.ofertavacanta.service.SpecialOfferService;
import com.example.ofertavacanta.utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.stream.StreamSupport;

public class HotelsController implements Observer {

    private HotelService hotelService;

    private SpecialOfferService specialOfferService;

    private ReservationService reservationService;

    @FXML
    private ComboBox<String> locationComboBox;

    @FXML
    private TableView<Hotel> hotelTableView;

    @FXML
    private TableColumn<Hotel, String> hotelNameColumn;

    @FXML
    private TableColumn<Hotel, Integer> noRoomsColumn;

    @FXML
    private TableColumn<Hotel, Integer> pricePerNightColumn;

    private String location;

    private Client client;

    ObservableList<Hotel> model = FXCollections.observableArrayList();


    public void setService(HotelService hotelService, SpecialOfferService specialOfferService, ReservationService reservationService){
        this.hotelService = hotelService;
        this.specialOfferService = specialOfferService;
        this.reservationService = reservationService;
        initData();
    }
    public void setClient(Client c)
    {
        this.client=c;
    }

    public void initialize()
    {
        hotelNameColumn.setCellValueFactory(new PropertyValueFactory<Hotel,String>("hotelName"));
        noRoomsColumn.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("noRooms"));
        pricePerNightColumn.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("pricePerNight"));
        hotelTableView.setItems(model);

    }
    @Override
    public void update() {
        initData();
    }

    private void initData() {
        locationComboBox.setPromptText("Hotel Location");
        ObservableList<String> locations = FXCollections.observableArrayList();
        locations.addAll(StreamSupport.stream(hotelService.getLocations().spliterator(),false).toList());
        //locations.addAll(StreamSupport.stream(locationService.findAll().spliterator(), false).toList());
        locationComboBox.getItems().addAll(locations);
        model.setAll(StreamSupport.stream(hotelService.findAll().spliterator(), false).toList());
    }

    public void searchButtonHandler() {
        location = locationComboBox.getSelectionModel().getSelectedItem();
        model.setAll(StreamSupport.stream(hotelService.getLocationHotels(location).spliterator(),false).toList());
    }

    public void selectedHotel() throws IOException {
        Hotel selectedHotel = hotelTableView.getSelectionModel().getSelectedItem();
        if(selectedHotel!=null)
        {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("special-offers-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Special Offers");
            SpecialOffersController specialOffersController = fxmlLoader.getController();
            specialOffersController.setHotel(selectedHotel);
            specialOffersController.setService(specialOfferService, reservationService);
            specialOffersController.setClient(client);
            stage.setScene(scene);
            stage.show();
        }
    }
}
