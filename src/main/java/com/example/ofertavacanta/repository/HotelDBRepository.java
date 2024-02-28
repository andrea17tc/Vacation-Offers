package com.example.ofertavacanta.repository;

import com.example.ofertavacanta.domain.Hotel;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class HotelDBRepository implements Repository<Double, Hotel>{
    private String url;
    private String username;
    private String password;

    public HotelDBRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Hotel> findOne(Double aDouble) {
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from hotel " +
                    "where hotelid = ?");

        ) {
            statement.setInt(1, (int) Math.round(aDouble));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String locationName= resultSet.getString("locationname");
                String hotelName=resultSet.getString("hotelname");
                Integer noRooms =resultSet.getInt("noRooms");
                Double pricePerNight = resultSet.getDouble("pricepernight");
                Hotel h = new Hotel(locationName,hotelName,noRooms,pricePerNight);
                h.setId(aDouble);
                return Optional.ofNullable(h);
            }
        } catch (SQLException | RuntimeException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Hotel> findAll() {
        Set<Hotel> hotels = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from hotel");
             ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next())
            {
                Double hotelID= resultSet.getDouble("hotelid");
                String locationName= resultSet.getString("locationname");
                String hotelName=resultSet.getString("hotelname");
                Integer noRooms =resultSet.getInt("noRooms");
                Double pricePerNight = resultSet.getDouble("pricepernight");
                Hotel h = new Hotel(locationName,hotelName,noRooms,pricePerNight);
                h.setId(hotelID);
                hotels.add(h);

            }
            return hotels;

        } catch (SQLException | RuntimeException e) {
            return null;
        }
    }

    @Override
    public Optional<Hotel> save(Hotel entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Hotel> delete(Hotel entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Hotel> update(Hotel entity) {
        return Optional.empty();
    }
}
