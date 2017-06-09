package Parcial2.Util;

import Parcial2.Entities.Usuario;
import org.joda.time.DateTime;
/**
 * Created by yo on 8/6/2017.
 */
public class AuthenticationData {
    private Usuario usuario;
    private DateTime lastAction;

    public DateTime getLastAction() {
        return lastAction;
    }

    public void setLastAction(DateTime lastAction) {
        this.lastAction = lastAction;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
