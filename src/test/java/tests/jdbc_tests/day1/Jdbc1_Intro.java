package tests.jdbc_tests.day1;

import java.sql.*;

public class Jdbc1_Intro {

    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        String dbUserName = "postgres";
        String dbPassword = "1234";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        //create statement object
        Statement statement = connection.createStatement();
        //run query and get the result in result set object
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //move pointer to the first row
        resultSet.next();
        //getting the information with column name
        System.out.println(resultSet.getString("employeeId"));
        System.out.println(resultSet.getString("firstName"));

        //getting the information with index number (starting with 1)
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));

        resultSet.next();
        //get the phone number of second row
        System.out.println(resultSet.getString("phoneNumber"));
        System.out.println(resultSet.getString(5));

        System.out.println("");

        //get all employeeId, firstname and lastname in one shot
        while (resultSet.next()){
            System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2) + " - " + resultSet.getString(3));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

}
