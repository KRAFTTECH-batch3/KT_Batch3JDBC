package tests.jdbc_tests.day1;

import java.sql.*;

public class Jdbc2_Practice {

    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        String dbUserName = "postgres";
        String dbPassword = "1234";

        Connection connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from locations");

        //get the locationId, streetAddress, postcode of first row
        resultSet.next();
        System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2) + " - " + resultSet.getString("postcode"));

        System.out.println("");

        //get the locationId, streetAdress, city and region of second row
        resultSet.next();
        System.out.println(resultSet.getString("locationId") + " - " + resultSet.getString("streetAddress")
                + " - " + resultSet.getString("city") + " - " + resultSet.getString("region"));

        System.out.println("");

        //get all information of fifth row
        resultSet.next();
        resultSet.next();
        resultSet.next();
        for (int i = 1; i <= 6; i++) {
            System.out.print(resultSet.getString(i) + " - ");
        };

        System.out.println("");
        System.out.println("");

        //get the city, region and country of sixth row
        resultSet.next();
        System.out.println(resultSet.getString(4) + " - " + resultSet.getString(5) + " - " +
                resultSet.getString(6));

        System.out.println("");

        //get the region of last row
        resultSet.next();
        resultSet.next();
        System.out.println(resultSet.getString("region"));


        resultSet.close();
        statement.close();
        connection.close();
    }

}
