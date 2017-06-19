package Parcial2.Services;

import Parcial2.EntitiesTest.Mensaje;
import Parcial2.Persistence.MensajeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yo on 18/6/2017.
 */
@Service
public class MensajeService {
    private MensajeDao mDao;

    @Autowired
    public MensajeService(MensajeDao m){ mDao = m;}
    public boolean save(Mensaje message) { return mDao.save(message);}
    public List<Mensaje> getAll(){ return mDao.getAll();}
    public List<Mensaje> getAllSended(){ return mDao.getAllSended();}
    public List<Mensaje> getAllDeleted(){ return mDao.getAllDeleted();}
    public boolean delete(int id){ return mDao.delete(id);}
}
