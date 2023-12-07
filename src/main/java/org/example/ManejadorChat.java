package org.example;

import java.util.HashMap;
import java.util.Map;

public class ManejadorChat {
    public interface ChatCallback {
        void onMessageProcessed(String chatKey, Chat chat);
    }

    private Map<String, Chat> chatsPrivados;
    private Chat chatGrupal;
    private ChatCallback callback; // Añadir callback

    // Constructor
    public ManejadorChat(ChatCallback callback) {
        this.chatsPrivados = new HashMap<>();
        this.chatGrupal = new Chat();
        this.callback = callback; // Inicializar callback
    }

    // Método para enviar un mensaje
    public void enviarMensaje(String emisor, String receptor, String contenido) {
        String chatKey = receptor.equals("todos") ? "todos" : crearClaveChat(emisor, receptor);
        Mensaje nuevoMensaje = new Mensaje(emisor, receptor, contenido);

        if ("todos".equals(receptor)) {
            chatGrupal.agregarMensaje(nuevoMensaje);
        } else {
            chatsPrivados.computeIfAbsent(chatKey, k -> new Chat()).agregarMensaje(nuevoMensaje);
        }

        // Invocar el callback
        if (callback != null) {
            Chat chat = "todos".equals(receptor) ? chatGrupal : chatsPrivados.get(chatKey);
            callback.onMessageProcessed(chatKey, chat);
        }
    }

    // Método para obtener un chat
    public Chat obtenerChat(String emisor, String receptor) {
        if (receptor.equals("todos")) {
            return chatGrupal;
        } else {
            String chatKey = crearClaveChat(emisor, receptor);
            return chatsPrivados.getOrDefault(chatKey, new Chat());
        }
    }

    // Método privado para crear una clave única para cada chat privado
    private String crearClaveChat(String emisor, String receptor) {
        return emisor + "-" + receptor;
    }
}
