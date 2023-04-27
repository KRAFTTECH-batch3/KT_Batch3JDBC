package tests.jdbc_tests.day2;

import org.testng.annotations.Test;

import java.sql.*;

public class Jdbc1_MovingCursorOnTheTable {

    String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    String dbUserName = "postgres";
    String dbPassword = "1234";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //how to find how many rows we have for the query
        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        System.out.println("");

        //how to go fist line
        resultSet.first();
        System.out.println("resultSet.getRow() = " + resultSet.getRow());

        System.out.println("");

        //how to get "Ahmet" firstname directly?
        resultSet.absolute(7);
        System.out.println("resultSet.getString(\"firstName\") = " + resultSet.getString("firstName"));

        System.out.println("");

        //how to go 6th line?
        resultSet.previous();
        System.out.println("resultSet.getRow() = " + resultSet.getRow());

        resultSet.close();
        statement.close();
        connection.close();
    }

}
