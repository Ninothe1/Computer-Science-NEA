package loginAndStart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import loginAndStart.LoginScreen;

public class ConnectionDB {
    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:GameDatabase.db"); //connect to database
            //if this works it will print connected so i know if it works
        } catch (ClassNotFoundException | SQLException e) {
            //Auto generated catch block
            System.out.println(e + "");
            System.out.println("Not connected");


        }
        return con;

    }
}

