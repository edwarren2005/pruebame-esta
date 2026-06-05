package persistence;

import model.Juego;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class GuardarPartida {

    public static void guardarJuego(Juego juego) {

        try {

            ObjectOutputStream salida =
                    new ObjectOutputStream(
                            new FileOutputStream("partida.dat")
                    );

            salida.writeObject(juego);
            salida.close();

            System.out.println("Partida guardada correctamente.");

        } catch (Exception e) {

            System.out.println("Error al guardar la partida.");
        }
    }
}