package maprojetservice;

import maprojetconnexion.Connexion;
import maprojetdao.IDao;
import maprojetsbeans.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements IDao<Client> {

    @Override
    public boolean create(Client client) {
        String query = "INSERT INTO clients (nom, prenom) VALUES (?, ?)";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Client client) {
        String query = "DELETE FROM clients WHERE id = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, client.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client client) {
        String query = "UPDATE clients SET nom = ?, prenom = ? WHERE id = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setInt(3, client.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Client findById(int id) {
        String query = "SELECT * FROM clients WHERE id = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                return new Client(id, nom, prenom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";
        try (Connection connection = Connexion.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                clients.add(new Client(id, nom, prenom));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public List<Client> findByNom(String nom) {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients WHERE nom = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nom);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String prenom = resultSet.getString("prenom");
                clients.add(new Client(id, nom, prenom));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Client findByEmail(String email) {
        String query = "SELECT * FROM clients WHERE email = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                return new Client(id, nom, prenom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
