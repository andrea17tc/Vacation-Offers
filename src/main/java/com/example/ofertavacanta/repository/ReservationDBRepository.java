package com.example.ofertavacanta.repository;

import com.example.ofertavacanta.domain.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class ReservationDBRepository implements Repository<Double, Reservation>{
    private String  url;
    private String username;
    private String password;

    public ReservationDBRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Reservation> findOne(Double aDouble) {
        return Optional.empty();
    }

    @Override
    public Iterable<Reservation> findAll() {   return null;}

    @Override
    public Optional<Reservation> save(Reservation entity) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("insert into reservation(clientid, hotelid, startdate, nonights) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ){

            statement.setLong(1, entity.getClientID());
            statement.setLong(2, (long) entity.getHotelID());
            statement.setDate(3, Date.valueOf(entity.getStartDate().toString()));
            statement.setInt(4, entity.getNoNights());
            if(statement.executeUpdate()<=0) {

                throw new RuntimeException("Nu s-a salvat!");
            }
            return Optional.empty();

        } catch (SQLException | RuntimeException e) {
            return Optional.of(entity);
        }
    }

    @Override
    public Optional<Reservation> delete(Reservation entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> update(Reservation entity) {
        return Optional.empty();
    }
}
