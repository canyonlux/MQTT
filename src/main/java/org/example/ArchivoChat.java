package org.example;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ArchivoChat {

    private static final String DIRECTORIO_CHATS = "chats/";

    // Método para guardar un chat en un archivo
    public static void guardarChat(String chatKey, Chat chat) {
        try {
            // Asegurarse de que el directorio exista
            Files.createDirectories(Paths.get(DIRECTORIO_CHATS));

            // Escribir en el archivo
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(DIRECTORIO_CHATS + chatKey + ".txt"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND)) {
                writer.write(chat.toString());
            }
        } catch (IOException e) {
            System.err.println("No se pudo guardar el chat: " + e.getMessage());
        }
    }


    // Método para leer un chat de un archivo
    public static Chat leerChat(String claveChat) {
        String nombreArchivo = DIRECTORIO_CHATS + claveChat + ".txt";
        Chat chat = new Chat();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {

                chat.agregarMensaje(new Mensaje("Emisor", "Receptor", linea));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chat;
    }
}
