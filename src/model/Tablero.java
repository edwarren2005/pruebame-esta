package model;

import exceptions.CasillaYaDescubiertaException;

import java.io.Serializable;
import java.util.Random;

public class Tablero implements Serializable {

    private Casilla[][] casillas;
    private final int TAMANIO = 10;
    private final int MINAS = 10;

    public Tablero() {
        casillas = new Casilla[TAMANIO][TAMANIO];

        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                casillas[i][j] = new Casilla();
            }
        }

        colocarMinas();
        calcularNumeros();
    }

    private void colocarMinas() {

        Random random = new Random();
        int minasColocadas = 0;

        while (minasColocadas < MINAS) {

            int fila = random.nextInt(TAMANIO);
            int columna = random.nextInt(TAMANIO);

            if (!casillas[fila][columna].tieneMina()) {
                casillas[fila][columna].setMina(true);
                minasColocadas++;
            }
        }
    }

    private void calcularNumeros() {

        for (int i = 0; i < TAMANIO; i++) {

            for (int j = 0; j < TAMANIO; j++) {

                if (!casillas[i][j].tieneMina()) {

                    int contador = contarMinasVecinas(i, j);
                    casillas[i][j].setMinasAlrededor(contador);
                }
            }
        }
    }

    private int contarMinasVecinas(int fila, int columna) {

        int contador = 0;

        for (int i = fila - 1; i <= fila + 1; i++) {

            for (int j = columna - 1; j <= columna + 1; j++) {

                if (i >= 0 && i < TAMANIO && j >= 0 && j < TAMANIO) {

                    if (casillas[i][j].tieneMina()) {
                        contador++;
                    }
                }
            }
        }

        return contador;
    }

    public boolean descubrirCasilla(int fila, int columna)
            throws CasillaYaDescubiertaException {

        Casilla casilla = casillas[fila][columna];

        if (casilla.estaDescubierta()) {
            throw new CasillaYaDescubiertaException(
                    "La casilla ya fue descubierta."
            );
        }

        casilla.descubrir();

        return casilla.tieneMina();
    }


    public void revelarMinas() {

        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                if (casillas[i][j].tieneMina()) {
                    casillas[i][j].descubrir();
                }
            }
        }
    }

    public void mostrarTablero() {

        System.out.print("   ");

        for (int i = 1; i <= TAMANIO; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i < TAMANIO; i++) {

            System.out.print((char) ('A' + i) + "  ");

            for (int j = 0; j < TAMANIO; j++) {

                Casilla casilla = casillas[i][j];

                if (!casilla.estaDescubierta()) {
                    System.out.print("■ ");
                } else if (casilla.tieneMina()) {
                    System.out.print("X ");
                } else {
                    System.out.print("V ");
                }
            }

            System.out.println();
        }
    }
}