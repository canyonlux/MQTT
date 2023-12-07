package org.example;

import java.time.LocalDateTime;

public class Mensaje {

    private String emisor;
    private String receptor;
    private String contenido;
    private LocalDateTime timestamp;

    // Constructor
    public Mensaje(String emisor, String receptor, String contenido) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.contenido = contenido;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters
    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    @Override
    public String toString() {
        return "Mensaje{" +
                "emisor='" + emisor + '\'' +
                ", receptor='" + receptor + '\'' +
                ", contenido='" + contenido + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
