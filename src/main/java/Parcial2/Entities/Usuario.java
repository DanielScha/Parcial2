package Parcial2.Entities;

import java.util.List;

/**
 * Created by yo on 6/6/2017.
 */
public class Usuario {
    private  int id;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String ciudad;
    private String provincia;
    private String pais;
    private String contraseña;
    private String email;
    private List<Mensaje> mensajes;

    public Usuario(){
        nombreUsuario="";
        nombre = "";
        apellido="";
        direccion="";
        telefono="";
        ciudad="";
        provincia="";
        pais="";
        contraseña="";
        email="";
        mensajes = null;
    }
    public Usuario(String nU, String n, String a, String d, String t, String c, String pr, String pa, String con, String e, List<Mensaje> m){
        nombreUsuario = nU;
        nombre = n;
        apellido = a;
        direccion = d;
        telefono = t;
        ciudad = c;
        provincia = pr;
        pais = pa;
        contraseña = con;
        email = e;
        mensajes = m;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
