package Parcial2.Controllers;

import Parcial2.Entities.Usuario;
import Parcial2.Services.UsuarioService;
import Parcial2.Util.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by yo on 7/6/2017.
 */
@Controller
@RequestMapping(
        value = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UsuarioController {
    @Autowired
    UsuarioService uService;

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Usuario>> getAll(){
        if(uService.getAll().size() > 0){
            return new ResponseEntity<List<Usuario>>(uService.getAll(), HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    /*@RequestMapping(value = "/usuarios/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Usuario> getById(@RequestParam("id") int id){
        if(uService.getById(id) != null) {
            return new ResponseEntity<Usuario>(uService.getById(id), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }*/
    @RequestMapping(value = "/usuarios/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Usuario> getByNombre(@RequestParam("nombreUsuario") String nombreUsuario){
        if(uService.getByNombre(nombreUsuario) != null) {
            return new ResponseEntity<Usuario>(uService.getByNombre(nombreUsuario), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody Usuario u){
        if(uService.save(u)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestHeader int id){
        if(uService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT); // NO PUDE RESISTIRME
        }
    }
}
