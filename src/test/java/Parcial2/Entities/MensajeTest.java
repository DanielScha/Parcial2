package Parcial2.Entities;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;

/**
 * Created by yo on 19/6/2017.
 */
public class MensajeTest extends TestCase{
    private Mensaje m;
    private Usuario recipiente;
    private Usuario remitente;
    private Timestamp fecha;

    @Before
    public void setUp()throws ParseException{
        recipiente = new Usuario();
        remitente = new Usuario();
        m = new Mensaje();
        m.setId(1);
        m.setRemitente(remitente);
        m.setRecipiente(recipiente);
        m.setAsunto("asunto");
        m.setMensaje("mensaje");
        m.setFecha(fecha);
    }

   @After
    public void tearDown() throws Exception{
        m=null;
    }

    @Test
    public void testId(){
        System.out.println(m);
        assertEquals(m.getId(),1);}

    @Test
    public void testRecipiente(){assertEquals(m.getRecipiente(),recipiente);}

    @Test
    public void testRemitente(){assertEquals(m.getRemitente(),remitente);}

    @Test
    public void testAsunto(){assertEquals(m.getAsunto(),"asunto");}

    @Test
    public void testMensaje(){assertEquals(m.getMensaje(),"mensaje");}

    @Test
    public void testFecha(){assertEquals(m.getFecha(),fecha);}
}
