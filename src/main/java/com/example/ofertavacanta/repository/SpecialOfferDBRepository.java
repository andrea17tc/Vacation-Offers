package com.example.ofertavacanta.repository;

import com.example.ofertavacanta.domain.SpecialOffer;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SpecialOfferDBRepository implements Repository<Double, SpecialOffer>{

    private String  url;
    private String username;
    private String password;

    public SpecialOfferDBRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<SpecialOffer> findOne(Double aDouble) {
        return Optional.empty();
    }

    @Override
    public Iterable<SpecialOffer> findAll() {
        Set<SpecialOffer> soffers = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from specialoffer");
             ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next())
            {
                Double specialOfferID= resultSet.getDouble("specialofferid");
                Double hotelID= resultSet.getDouble("hotelid");
                LocalDate startdate= resultSet.getDate("startdate").toLocalDate();
                LocalDate enddate= resultSet.getDate("enddate").toLocalDate();
                Integer percents =resultSet.getInt("percents");
                SpecialOffer s = new SpecialOffer(hotelID,startdate,enddate,percents);
                s.setId(specialOfferID);
                soffers.add(s);

            }
            return soffers;

        } catch (SQLException | RuntimeException e) {
            return null;
        }
    }

    @Override
    public Optional<SpecialOffer> save(SpecialOffer entity) {
        return Optional.empty();
    }

    @Override
    public Optional<SpecialOffer> delete(SpecialOffer entity) {
        return Optional.empty();
    }

    @Override
    public Optional<SpecialOffer> update(SpecialOffer entity) {
        return Optional.empty();
    }
}
