package model;

import java.io.Serializable;

public class Juego implements Serializable {

    private Tablero tablero;
    private boolean terminado;

    public Juego() {
        tablero = new Tablero();
        terminado = false;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void terminarJuego() {
        terminado = true;
    }
}