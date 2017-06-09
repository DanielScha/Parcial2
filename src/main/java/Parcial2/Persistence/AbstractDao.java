package Parcial2.Persistence;

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
    protected Connection cn;

    public AbstractDao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parcial2", "root", "mysql0426");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public abstract boolean save(T t);
    public abstract List<T> getAll();
    public abstract T getById(int id);
    public abstract boolean delete(int id);

}
