package Parcial2.Controllers;

import Parcial2.Entities.Usuario;
import Parcial2.Services.UsuarioService;
import Parcial2.Util.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yo on 8/6/2017.
 */
@RestController
public class LoginController {

    @Autowired
    UsuarioService uService;

    @Autowired
    SessionData sessionData;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> login(@RequestParam("nombreUsuario") String nombreUsuario,
                                                      @RequestParam("contra") String contra){
        if(uService.login(nombreUsuario,contra) != null){
            Usuario u = uService.login(nombreUsuario,contra);
            String idSession = sessionData.addSession(u);
            return new ResponseEntity<String>(idSession, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/logout")
    public @ResponseBody ResponseEntity logout(@RequestHeader("sessionid") String sessionid){
        sessionData.removeSession(sessionid);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
