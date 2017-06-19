package Parcial2.Controllers;

import Parcial2.EntitiesTest.Mensaje;
import Parcial2.Services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by yo on 19/6/2017.
 */
@Controller
@RequestMapping(
        value = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class MensajesController {
    @Autowired
    MensajeService mService;

    @RequestMapping (value = "/mensajes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody Mensaje m){
        if(mService.save(m)){
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping (value = "/mensajes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Mensaje>> getInbox(){
        List<Mensaje> m = mService.getAll();
        if(m!=null){
            return new ResponseEntity<List<Mensaje>>(mService.getAll(), HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping (value = "/mensajes/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Mensaje>> getSended(){
        List<Mensaje> m = mService.getAll();
        if(m!=null){
            return new ResponseEntity<List<Mensaje>>(mService.getAll(), HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping (value = "/mensajes/d", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Mensaje>> getDeleted(){
        List<Mensaje> m = mService.getAll();
        if(m!=null){
            return new ResponseEntity<List<Mensaje>>(mService.getAll(), HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping (value = "/mensajes", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestHeader int id){
        if(mService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
