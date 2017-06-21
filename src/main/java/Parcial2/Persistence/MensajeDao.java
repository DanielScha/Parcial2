package Parcial2.Persistence;

import Parcial2.Entities.Mensaje;
import Parcial2.Entities.Usuario;
import Parcial2.Util.AuthenticationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yo on 18/6/2017.
 */
@Repository
public class MensajeDao extends AbstractDao<Mensaje> {

    @Autowired
    public MensajeDao(@Value("${db.host}") String host, @Value("${db.port}") String port, @Value("${db.name}") String name,
                      @Value("${db.username}") String user, @Value("${db.password}") String pass){
        super(host,port,name,user,pass);
    }

    @Autowired
    private AuthenticationData aD;

    public boolean save(Mensaje message) {
        try {
            PreparedStatement ps = cn.prepareStatement("INSERT INTO mensajes (id_remitente, id_recipiente, asunto, mensaje, fecha, deleted) VALUES(?,?,?,?,?,?);");
            ps.setInt(1, message.getRemitente().getId());
            ps.setInt(2, message.getRecipiente().getId());
            ps.setString(3, message.getAsunto());
            ps.setString(4, message.getMensaje());
            ps.setTimestamp(5, message.getFecha());
            ps.setBoolean(6, false);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Mensaje> getAll(){
        try{
            PreparedStatement ps = cn.prepareStatement("SELECT m.id, m.asunto, m.mensaje, m.fecha, u.email FROM mensajes m INNER JOIN usuarios u " +
                    "ON m.id_remitente = u.id WHERE m.id_recipiente = ? AND m.deleted = false;");
            ps.setInt(1, aD.getUsuario().getId());
            ResultSet rs = ps.executeQuery();
            ArrayList<Mensaje> messages = new ArrayList<Mensaje>();
            while (rs.next()) {
                Usuario remitente = new Usuario(rs.getString("u.email"));
                Mensaje m = new Mensaje(remitente, aD.getUsuario(), rs.getString("m.asunto"), rs.getString("m.mensaje"), rs.getTimestamp("m.fecha"));
                m.setId(rs.getInt("m.id"));
                messages.add(m);
            }
            return messages;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Mensaje> getAllSended(){
        try{
            PreparedStatement ps = cn.prepareStatement("SELECT m.id, m.asunto, m.mensaje, m.fecha, u.email FROM mensajes m INNER JOIN usuarios u " +
                    "ON m.id_recipiente = u.id WHERE m.id_remitente = ?;");
            ps.setInt(1, aD.getUsuario().getId());
            ResultSet rs = ps.executeQuery();
            ArrayList<Mensaje> messages = new ArrayList<Mensaje>();
            while (rs.next()) {
                Usuario recipiente = new Usuario(rs.getString("u.email"));
                Mensaje m = new Mensaje(aD.getUsuario(), recipiente, rs.getString("m.asunto"), rs.getString("m.mensaje"), rs.getTimestamp("m.fecha"));
                m.setId(rs.getInt("m.id"));
                messages.add(m);
            }
            return messages;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Mensaje> getAllDeleted(){
        try{
            PreparedStatement ps = cn.prepareStatement("SELECT m.id, m.asunto, m.mensaje, m.fecha, u.email FROM mensajes m INNER JOIN usuarios u " +
                                                               "ON m.id_remitente = u.id WHERE m.id_recipiente = ? AND m.deleted = true;");
            ps.setInt(1, aD.getUsuario().getId());
            ResultSet rs = ps.executeQuery();
            ArrayList<Mensaje> messages = new ArrayList<Mensaje>();
            while (rs.next()) {
                Usuario remitente = new Usuario(rs.getString("u.email"));
                Mensaje m = new Mensaje(remitente, aD.getUsuario(), rs.getString("m.asunto"), rs.getString("m.mensaje"), rs.getTimestamp("m.fecha"));
                m.setId(rs.getInt("m.id"));
                messages.add(m);
            }
            return messages;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean delete(int id){
        try{
            PreparedStatement ps = cn.prepareStatement("UPDATE mensajes SET deleted = true WHERE id = ?;");
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
