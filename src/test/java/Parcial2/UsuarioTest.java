package Parcial2;

import Parcial2.Entities.Mensaje;
import Parcial2.Entities.Usuario;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;


/**
 * Created by yo on 8/6/2017.
 */
public class UsuarioTest extends TestCase {

    private Usuario u;

    @Before
    public void setUp() throws ParseException {
        u = new Usuario();
        u.setId(1);
        u.setNombreUsuario("nombreUsuario");
        u.setNombre("nombre");
        u.setApellido("apellido");
        u.setDireccion("direccion");
        u.setTelefono("telefono");
        u.setCiudad("ciudad");
        u.setProvincia("provincia");
        u.setPais("pais");
        u.setContraseña("contraseña");
        u.setEmail("email");
        u.setMensajes(new ArrayList<Mensaje>());
    }
    @Test
    public void testId(){
        assertEquals(u.getId(),1);
    }

    public void testNombreUsuario(){
        assertEquals(u.getNombreUsuario(), "nombreUsuario");
    }
}