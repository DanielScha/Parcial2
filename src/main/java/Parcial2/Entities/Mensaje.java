package Parcial2.Entities;

import java.sql.Timestamp;

/**
 * Created by yo on 6/6/2017.
 */
public class Mensaje {
    private int id;
    private Usuario remitente;
    private Usuario recipiente;
    private String asunto;
    private String mensaje;
    private Timestamp fecha;

    public Mensaje(){
        id=0;
        remitente=null;
        recipiente=null;
        asunto="";
        mensaje="";

        fecha=null;
    }
    public Mensaje(Usuario remi, Usuario reci, String a, String m, Timestamp f){
        id=0;
        remitente = remi;
        recipiente = reci;
        asunto = a;
        mensaje = m;
        fecha = f;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public void setRemitente(Usuario remitente) {
        this.remitente = remitente;
    }

    public Usuario getRecipiente() {
        return recipiente;
    }

    public void setRecipiente(Usuario recipiente) {
        this.recipiente = recipiente;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
