package Parcial2.Entities;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;


/**
 * Created by yo on 8/6/2017.
 */
public class UsuarioTest extends TestCase {

    private Usuario u;
    private ArrayList<Mensaje> m;

    @Before
    public void setUp() throws ParseException {
        m = new ArrayList<Mensaje>();
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
        u.setMensajes(m);
    }

    @After
    public void tearDown() throws Exception{
        u=null;
    }

    @Test
    public void testId(){assertEquals(u.getId(),1);}

    @Test
    public void testNombreUsuario(){
        assertEquals(u.getNombreUsuario(), "nombreUsuario");
    }

    @Test
    public void testNombre() {assertEquals(u.getNombre(), "nombre");}

    @Test
    public void testApellido() {assertEquals(u.getApellido(), "apellido");}

    @Test
    public void testDireccion() {assertEquals(u.getDireccion(), "direccion");}

    @Test
    public void testTelefono() {assertEquals(u.getTelefono(), "telefono");}

    @Test
    public void testCiudad() {assertEquals(u.getCiudad(), "ciudad");}

    @Test
    public void testProvincia() {assertEquals(u.getProvincia(), "provincia");}

    @Test
    public void testPais() {assertEquals(u.getPais(), "pais");}

    @Test
    public void testContraseña() {assertEquals(u.getContraseña(), "contraseña");}

    @Test
    public void testEmail() {assertEquals(u.getEmail(), "email");}

    @Test
    public void testMensajes() {assertEquals(u.getMensajes(),m);}
}