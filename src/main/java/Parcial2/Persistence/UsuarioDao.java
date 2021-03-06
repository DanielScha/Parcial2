package Parcial2.Persistence;

import Parcial2.Entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yo on 6/6/2017.
 */
@Repository
public class UsuarioDao extends AbstractDao<Usuario> {

    @Autowired
    public UsuarioDao(@Value("${db.host}") String host, @Value("${db.port}") String port, @Value("${db.name}") String name,
                      @Value("${db.username}") String user, @Value("${db.password}") String pass){
        super(host,port,name,user,pass);
    }

    public boolean save(Usuario user){
        try{
            PreparedStatement ps = cn.prepareStatement("INSERT INTO usuarios(nombre_usuario, nombre, apellido, direccion, telefono, ciudad, provincia, pais, contraseña, email)" +
                    "                                       VALUES(?,?,?,?,?,?,?,?,?,?);");
            ps.setString(1,user.getNombreUsuario());
            ps.setString(2,user.getNombre());
            ps.setString(3,user.getApellido());
            ps.setString(4,user.getDireccion());
            ps.setString(5,user.getTelefono());
            ps.setString(6,user.getCiudad());
            ps.setString(7,user.getProvincia());
            ps.setString(8,user.getPais());
            ps.setString(9,user.getContraseña());
            ps.setString(10, user.getEmail());
            ps.execute();
            return true;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Usuario> getAll(){
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios;");
            ArrayList<Usuario> users = new ArrayList<Usuario>();
            while (rs.next()) {
                Usuario u = new Usuario(rs.getString("nombre_usuario"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("ciudad"), rs.getString("provincia"),
                        rs.getString("pais"), rs.getString("contraseña"), rs.getString("email"), null);
                u.setId(rs.getInt("id"));
                users.add(u);
            }
            return users;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*public Usuario getById(int id){
        try{
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Usuario u = new Usuario(rs.getString("nombre_usuario"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("ciudad"), rs.getString("provincia"),
                        rs.getString("pais"), rs.getString("contraseña"), rs.getString("email"), null);
                u.setId(rs.getInt("id"));
                return u;
            }
            return null;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/
    public Usuario getByNombre(String nombre){
        try{
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM usuarios WHERE nombre_usuario = ?");
            ps.setString(1,nombre);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Usuario u = new Usuario(rs.getString("nombre_usuario"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("ciudad"), rs.getString("provincia"),
                        rs.getString("pais"), rs.getString("contraseña"), rs.getString("email"), null);
                u.setId(rs.getInt("id"));
                return u;
            }
            return null;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(int id){
        try{
            PreparedStatement ps = cn.prepareStatement("DELETE FROM usuarios WHERE id = ?;");
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public Usuario login(String nombreUsuario, String contra){
        try{
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM usuarios WHERE nombre_usuario = ? AND contraseña = ?");
            ps.setString(1,nombreUsuario);
            ps.setString(2,contra);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Usuario u = new Usuario(rs.getString("nombre_usuario"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("ciudad"), rs.getString("provincia"),
                        rs.getString("pais"), rs.getString("contraseña"), rs.getString("email"), null);
                u.setId(rs.getInt("id"));
                return u;
            }
            return null;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
