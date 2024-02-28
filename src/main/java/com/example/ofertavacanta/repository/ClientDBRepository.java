package com.example.ofertavacanta.repository;

import com.example.ofertavacanta.domain.*;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientDBRepository implements Repository<Long, Client>{

    private String url;
    private String username;
    private String password;

    public ClientDBRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Client> findOne(Long aLong) {
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from client " +
                    "where clientid = ?");

        ) {
            statement.setInt(1, Math.toIntExact(aLong));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString("name");
                Integer fidelityGrade = resultSet.getInt("fidelitygrade");
                Integer age = resultSet.getInt("age");
                Hobby hobby = Hobby.valueOf(resultSet.getString("hobby"));
                Client c = new Client(name,fidelityGrade,age,hobby);
                c.setId(aLong);
                return Optional.ofNullable(c);
            }
        } catch (SQLException | RuntimeException e) {

        }

        return Optional.empty();
    }

    @Override
    public Iterable<Client> findAll() {
        Set<Client> clients = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from client");
             ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next())
            {
                Long clientID= resultSet.getLong("clientid");
                String name= resultSet.getString("name");
                Integer fidelitygrade=resultSet.getInt("fidelitygrade");
                Integer age =resultSet.getInt("age");
                Hobby hobby = Hobby.valueOf(resultSet.getString("hobby"));
                Client c = new Client(name,fidelitygrade,age,hobby);
                c.setId(clientID);
                clients.add(c);

            }
            return clients;

        } catch (SQLException | RuntimeException e) {
            return null;
        }
    }

    @Override
    public Optional<Client> save(Client entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> delete(Client entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client entity) {
        return Optional.empty();
    }
}
