package com.example.ofertavacanta;

import com.example.ofertavacanta.controller.ClientsController;
import com.example.ofertavacanta.controller.HotelsController;
import com.example.ofertavacanta.domain.Client;
import com.example.ofertavacanta.repository.*;
import com.example.ofertavacanta.service.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelloApplication extends Application {

    private HotelDBRepository hotelDBRepository;
    private SpecialOfferDBRepository specialOfferDBRepository;

    private ClientDBRepository clientDBRepository;

    private ReservationDBRepository reservationDBRepository;
    private HotelService hotelService;

    private SpecialOfferService specialOfferService;

    private ClientService clientService;

    private ReservationService reservationService;

    @Override
    public void start(Stage stage) throws IOException {
        hotelDBRepository = new HotelDBRepository(
                "jdbc:postgresql://localhost:5432/Vacanta",
                "postgres",
                "America17"
        );
        specialOfferDBRepository = new SpecialOfferDBRepository(
                "jdbc:postgresql://localhost:5432/Vacanta",
                "postgres",
                "America17"
        );

        clientDBRepository = new ClientDBRepository(
                "jdbc:postgresql://localhost:5432/Vacanta",
                "postgres",
                "America17"
        );
        reservationDBRepository= new ReservationDBRepository(
                "jdbc:postgresql://localhost:5432/Vacanta",
                "postgres",
                "America17"
        );

        hotelService = new HotelService(hotelDBRepository);
        specialOfferService = new SpecialOfferService(specialOfferDBRepository);
        clientService = new ClientService(clientDBRepository);
        reservationService = new ReservationService(reservationDBRepository);

        Scanner in = new Scanner(System.in);
        List<Long> idList = new ArrayList<>();
        long idClient = in.nextLong();
        while(idClient!=0)
        {
            idList.add(idClient);
            idClient = in.nextLong();

        }

        for(Long id: idList)
        {
            Stage stage1 = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("clients-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Client client =  clientService.findOne(id).get();
            ClientsController clientsController = fxmlLoader.getController();
            clientsController.setClient(client);
            clientsController.setService(hotelService,specialOfferService, reservationService);
            stage1.setTitle(client.getName());
            stage1.setScene(scene);
            stage1.show();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}