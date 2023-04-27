package tests.jdbc_tests.day2;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jdbc3_ListOfMapExample {

    String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    String dbUserName = "postgres";
    String dbPassword = "1234";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select firstName, lastName, salary, jobId from employees");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();
        row1.put("firstName", "Eren");
        row1.put("lastName", "Çengel");
        row1.put("salary", 100000);
        row1.put("jobId", "QA");
        System.out.println("row1 = " + row1);

        Map<String,Object> row2 = new HashMap<>();
        row2.put("firstName", "Alperen");
        row2.put("lastName", "Çengel");
        row2.put("salary", 120000);
        row2.put("jobId", "Dev");
        System.out.println("row2 = " + row2);

        queryData.add(row1);
        queryData.add(row2);

        //get the Eren's lastname directly from the list
        System.out.println("queryData.get(0).get(\"lastName\") = " + queryData.get(0).get("lastName"));

        //get the Alperen's salary
        System.out.println("queryData.get(1).get(\"salary\") = " + queryData.get(1).get("salary"));

        //how to fill out a list of map with information that comes from database
        List<Map<String,Object>> queryData2 = new ArrayList<>();
        resultSet.next();
        Map<String,Object> newRow1 = new HashMap<>();
        newRow1.put(resultSetMetaData.getColumnName(1),resultSet.getString("firstName"));
        newRow1.put(resultSetMetaData.getColumnName(2),resultSet.getString("lastName"));
        newRow1.put(resultSetMetaData.getColumnName(3),resultSet.getString("salary"));
        newRow1.put(resultSetMetaData.getColumnName(4),resultSet.getString("jobId"));

        System.out.println("newRow1 = " + newRow1);

        resultSet.next();
        Map<String,Object> newRow2 = new HashMap<>();
        newRow2.put(resultSetMetaData.getColumnName(1), resultSet.getString("firstName"));
        newRow2.put(resultSetMetaData.getColumnName(2), resultSet.getString("lastName"));
        newRow2.put(resultSetMetaData.getColumnName(3), resultSet.getString("salary"));
        newRow2.put(resultSetMetaData.getColumnName(4), resultSet.getString("jobId"));

        System.out.println("newRow2 = " + newRow2);

        queryData2.add(newRow1);
        queryData2.add(newRow2);

        System.out.println("queryData2 = " + queryData2);

        resultSet.close();
        statement.close();
        connection.close();
    }

}
