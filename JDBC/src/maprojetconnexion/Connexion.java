package maprojetconnexion;

import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;

public  class Connexion  {

    private static final String URL = "jdbc:postgresql://localhost:5432/demoJDBC";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find PostgreSQL driver: " + e.getMessage());
            throw new SQLException("Could not find PostgreSQL driver");
        }

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demoJDBC", "postgres", "dorra");
        System.out.println("Connected to PostgreSQL database!");
        return connection;
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Connection to PostgreSQL database closed.");
        }
    }


}
