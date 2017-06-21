package Parcial2.Controllers;

import Parcial2.App;
import Parcial2.Entities.Mensaje;
import Parcial2.Entities.Usuario;
import Parcial2.Util.SessionData;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by yo on 20/6/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
@WebAppConfiguration
public class MensajesControllerTest extends TestCase{

    @Autowired
    private SessionData sessionData;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String sessionid;
    private Usuario u;
    private Usuario u2;
    private Mensaje m;
    private Timestamp fecha;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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

        u2 = new Usuario();
        u2.setId(2);
        u2.setNombreUsuario("nombreUsuario2");
        u2.setNombre("nombre");
        u2.setApellido("apellido");
        u2.setDireccion("direccion");
        u2.setTelefono("telefono");
        u2.setCiudad("ciudad");
        u2.setProvincia("provincia");
        u2.setPais("pais");
        u2.setContraseña("contraseña");
        u2.setEmail("email2");
        sessionid = sessionData.addSession(u);

        m = new Mensaje(u, u2, "asunto1", "mensaje1" , fecha);

    }

    @After
    public void tearDown() {
        this.sessionData.removeSession(this.sessionid);
    }

    @Test
    public void testSaveCreated() throws Exception{
        URL url = Resources.getResource("Mensaje.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        mockMvc.perform(post("/api/mensajes").header("sessionid", sessionid)
        .contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isCreated());
    }

    @Test
    public void testGetAllOk() throws Exception{
        mockMvc.perform(get("/api/mensajes").header("sessionid", sessionid))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
    }

    @Test
    public void testGetAllSendedOk() throws Exception{
        mockMvc.perform(get("/api/mensajes/").header("sessionid", sessionid))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
    }
    @Test
    public void testGetAllDeletedOk() throws Exception{
        mockMvc.perform(get("/api/mensajes/d").header("sessionid", sessionid))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
    }
    @Test
    public void testDeleteOk() throws Exception{
        mockMvc.perform(delete("/api/mensajes").header("sessionid", sessionid).header("id", m.getId()))
                .andExpect(status().isOk());
    }
    @Test
    public void testDeleteBadRequest()throws Exception{
        mockMvc.perform(delete("/api/mensajes").header("sessionid", sessionid).header("id", "cualquier cosa"))
                .andExpect(status().isBadRequest());
    }
}
