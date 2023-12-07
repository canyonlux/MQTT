package org.example;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    private List<Mensaje> mensajes;

    // Constructor
    public Chat() {
        mensajes = new ArrayList<>();
    }

    // Método para agregar un mensaje al chat
    public void agregarMensaje(Mensaje mensaje) {
        mensajes.add(mensaje);
    }

    // Método para obtener todos los mensajes del chat
    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Mensaje mensaje : mensajes) {
            sb.append(mensaje.toString()).append("\n");
        }
        return sb.toString();
    }
}
