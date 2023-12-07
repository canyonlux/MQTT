package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Consola {

    private ManejadorChat manejadorChat;
    private Scanner scanner;
    private List<String> usuarios;

    // Constructor
    public Consola() {
        // Crear un callback para guardar los chats en archivos
        ManejadorChat.ChatCallback callback = (chatKey, chat) -> ArchivoChat.guardarChat(chatKey, chat);
        manejadorChat = new ManejadorChat(callback); // Pasar el callback al ManejadorChat
        scanner = new Scanner(System.in);

        // Lista de usuarios
        usuarios = Arrays.asList("Cesar", "Antonio", "Alejandro", "Fullana", "Oscar", "Sam", "Julen", "Charles", "Alex", "Ruben", "Laura", "Adrian", "Jordi", "Javi", "Carol", "Sandra");

    }

    // Método para iniciar la interacción con la consola
    public void iniciar() {
        while (true) {
            System.out.println("Escribe un comando ('send' para enviar, 'chat' para ver chat, 'exit' para salir):");
            String comando = scanner.nextLine();

            if ("send".equals(comando)) {
                manejarEnvio();
            } else if ("chat".equals(comando)) {
                manejarVerChat();
            } else if ("exit".equals(comando)) {
                System.out.println("Saliendo...");
                break;
            } else {
                System.out.println("Comando no reconocido.");
            }
        }
    }

    // Método privado para manejar el envío de mensajes
    private void manejarEnvio() {
        System.out.println("Ingrese el topic y el mensaje (ejemplo: /chat/todos, Hola Juan   o /chat/Ruben/Charles/, hola que tal ):");
        String[] partes = scanner.nextLine().split(",", 2);

        if (partes.length == 2) {
            String[] topic = partes[0].trim().split("/");
            String mensaje = partes[1].trim();

            if (topic.length == 3 && "todos".equals(topic[2])) {
                // Envío a chat grupal
                String emisor = obtenerUsuarioValido();
                if (emisor != null) {
                    manejadorChat.enviarMensaje(emisor, "todos", mensaje);
                    System.out.println("Mensaje enviado al chat grupal.");
                }
            } else if (topic.length == 4 && usuarios.contains(topic[2]) && usuarios.contains(topic[3])) {
                // Envío a chat privado
                String emisor = topic[2];
                String receptor = topic[3];
                manejadorChat.enviarMensaje(emisor, receptor, mensaje);
                System.out.println("Mensaje enviado a " + receptor + ".");
            } else {
                System.out.println("Formato de topic incorrecto o usuarios no válidos.");
            }
        } else {
            System.out.println("Formato incorrecto.");
        }
    }
    private String obtenerUsuarioValido() {
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine().trim();
        return usuarios.contains(nombre) ? nombre : null;
    }

    // Método privado para manejar la visualización de chats
    private void manejarVerChat() {
        System.out.println("Ingrese el topic para ver el chat (ejemplo: /chat/todos/   o /chat/Ruben/Charles/ ):");
        String[] topic = scanner.nextLine().trim().split("/");

        if (topic.length >= 3) {
            String emisor = topic[2];
            String receptor = topic.length == 4 ? topic[3] : "todos"; // Si el topic tiene 4 partes, es privado; si no, es el grupal
            Chat chat = manejadorChat.obtenerChat(emisor, receptor);
            System.out.println(chat);
            ArchivoChat.guardarChat(emisor + "-" + receptor, chat);
        } else {
            System.out.println("Formato de topic incorrecto.");
        }
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        Consola consola = new Consola();
        consola.iniciar();
    }
}
