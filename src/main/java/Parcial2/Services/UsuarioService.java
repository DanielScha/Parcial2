package Parcial2.Services;

import Parcial2.Entities.Usuario;
import Parcial2.Persistence.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yo on 7/6/2017.
 */
@Service
public class UsuarioService {
    private UsuarioDao uDao;

    @Autowired
    public UsuarioService(UsuarioDao u){
        uDao = u;
    }
    public boolean save(Usuario u){
       return uDao.save(u);
    }
    public List<Usuario> getAll(){
        return uDao.getAll();
    }
    public Usuario getById(int id){
        return uDao.getById(id);
    }
    public boolean delete(int id){
        return uDao.delete(id);
    }
}
