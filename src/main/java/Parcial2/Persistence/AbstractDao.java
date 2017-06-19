package Parcial2.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yo on 8/6/2017.
 */
@Repository
public abstract class AbstractDao<T> {
    @Autowired
    protected Connection cn;

    public AbstractDao(){

    }

    public AbstractDao(String host, String port, String name, String user, String pass){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            cn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public abstract boolean save(T t);
    public abstract List<T> getAll();
    public abstract boolean delete(int id);
}
