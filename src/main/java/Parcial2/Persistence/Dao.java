package Parcial2.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yo on 8/6/2017.
 */
public class Dao {
    protected Connection cn;
    protected static Dao instance = null;

    public Dao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parcial2", "root", "mysql0426");
        } catch (SQLException e) {
            getInstance();
            e.printStackTrace();
        }
    }
    private Dao getInstance(){
        if(instance == null){
            return instance = new Dao();
        }
        return instance;
    }
}
