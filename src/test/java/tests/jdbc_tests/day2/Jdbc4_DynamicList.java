package tests.jdbc_tests.day2;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jdbc4_DynamicList {

    String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    String dbUserName = "postgres";
    String dbPassword = "1234";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select firstName, lastName, salary, jobId from employees");

        //get the resul set object metadata
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        //list for keeping all rows in a list
        List<Map<String,Object>> queryData = new ArrayList<>();

        int colCount = resultSetMetaData.getColumnCount();

        while (resultSet.next()){
        Map<String,Object> row = new HashMap<>();

        //fill out the map
        for(int i = 1; i<=colCount; i++){
            row.put(resultSetMetaData.getColumnName(i), resultSet.getString(i));
        }


        //add your map to list
        queryData.add(row);
        }

        System.out.println("queryData = " + queryData);

        System.out.println("queryData.get(3).get(\"firstName\") = " + queryData.get(3).get("firstname"));
        System.out.println("queryData.get(3).get(\"lastName\") = " + queryData.get(3).get("lastname"));
        System.out.println("queryData.get(3).get(\"salary\") = " + queryData.get(3).get("salary"));
        System.out.println("queryData.get(3).get(\"jobId\") = " + queryData.get(3).get("jobid"));


        resultSet.close();
        statement.close();
        connection.close();
    }

}
