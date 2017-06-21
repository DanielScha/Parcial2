package Parcial2.Controllers;

import Parcial2.App;
import Parcial2.Entities.Usuario;
import Parcial2.Util.SessionData;
import junit.framework.TestCase;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * Created by yo on 20/6/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
@WebAppConfiguration
public class LoginControllerTest extends TestCase {
    @Autowired
    private SessionData sessionData;

    @Autowired
    private WebApplicationContext webAppConfiguration;

    private MockMvc mockMvc;
    private String sessionid;
    private Usuario u;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppConfiguration).build();
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
    public void testLoginOk()throws Exception{
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity
                        (asList(new BasicNameValuePair("nombreUsuario", "danielScha"),
                                new BasicNameValuePair("contra", "lacontra1234"))))))
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginForbbiden()throws Exception{
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity
                        (asList(new BasicNameValuePair("nombreUsuario", "algoMal"),
                                new BasicNameValuePair("contra", "otroMal"))))))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testLogoutOk() throws Exception {
        mockMvc.perform(get("/logout")
                .header("sessionid", sessionid))
                .andExpect(status().isAccepted());
    }


}
