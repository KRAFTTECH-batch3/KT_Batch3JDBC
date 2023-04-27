package tests.jdbc_tests.day2;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class Jdbc5_DbUtilsPractice {

    @Test
    public void listOfMap(){
        //create connection
        DBUtils.createConnection();

        //get the information in list of map format
        //get all employees information
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("select * from employees");
        for (Map<String, Object> map : queryResultMap) {
            System.out.println("map = " + map);
        }

        //close connection
        DBUtils.destroy();
    }

    @Test
    public void oneSingleRow(){
        //create connection
        DBUtils.createConnection();

        //get one row employee information
        Map<String, Object> rowMap = DBUtils.getRowMap("select * from employees where firstName = 'Alperen'");
        System.out.println("rowMap = " + rowMap);

        //close connection
        DBUtils.destroy();
    }
}
