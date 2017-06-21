package Parcial2.Controllers;

import Parcial2.App;
import Parcial2.Entities.Mensaje;
import Parcial2.Entities.Usuario;
import Parcial2.Util.SessionData;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.web.WebAppConfiguration;

import org.springframework.web.context.WebApplicationContext;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;



import java.net.URL;
import java.util.ArrayList;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
@WebAppConfiguration
public class UsuarioControllerTest extends TestCase{

    @Autowired
    private SessionData sessionData;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String sessionid;
    private Usuario u;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        u = new Usuario();
        u.setId(1);
        u.setNombreUsuario("danielScha");
        u.setNombre("nombre");
        u.setApellido("apellido");
        u.setDireccion("direccion");
        u.setTelefono("telefono");
        u.setCiudad("ciudad");
        u.setProvincia("provincia");
        u.setPais("pais");
        u.setContraseña("contraseña");
        u.setEmail("email");
        sessionid = sessionData.addSession(u);
    }

    @After
    public void tearDown(){
        sessionData.removeSession(sessionid);
        u = null;
    }

    @Test
    public void testSaveCreated()throws Exception{
        URL url = Resources.getResource("Usuario.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        mockMvc.perform(post("/api/usuarios").header("sessionid", sessionid)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isCreated());

    }

    /*@Test
    public void testSaveInternalServerError()throws Exception{
        URL url = Resources.getResource("Usuario.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        mockMvc.perform(post("/api/usuarios").header("sessionid", sessionid)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isInternalServerError());

    }*/


    @Test
    public void testGetAllOk() throws Exception{
        mockMvc.perform(get("/api/usuarios").header("sessionid", sessionid))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
    }

    @Test
    public void testGetByNombreOk() throws Exception{
        mockMvc.perform(get("/api/usuarios/").header("sessionid", sessionid).param("nombreUsuario", u.getNombreUsuario()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
    }

    @Test
    public void testGetByNombreNotFound() throws Exception{
        mockMvc.perform(get("/api/usuarios/").header("sessionid", sessionid).param("nombreUsuario", u.getCiudad()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteOk() throws Exception{
        mockMvc.perform(delete("/api/usuarios").header("sessionid", sessionid).header("id", u.getId()))
                .andExpect(status().isOk());
    }
    @Test
    public void testDeleteBadRequest()throws Exception{
        mockMvc.perform(delete("/api/usuarios").header("sessionid", sessionid).header("id", u.getNombre()))
                .andExpect(status().isBadRequest());
    }

}
