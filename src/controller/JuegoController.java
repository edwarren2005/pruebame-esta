package controller;

import exceptions.CasillaYaDescubiertaException;
import model.Juego;
import persistence.GuardarPartida;
import view.ConsolaView;

import java.util.Scanner;

public class JuegoController {

    private Juego juego;
    private ConsolaView vista;

    public JuegoController() {
        juego = new Juego();
        vista = new ConsolaView();
    }

    public void iniciarJuego() {

        Scanner scanner = new Scanner(System.in);

        vista.mostrarMensaje("===== BUSCAMINAS =====");

        while (!juego.isTerminado()) {

            juego.getTablero().mostrarTablero();

            try {

                vista.mostrarMensaje("\nIngrese fila (A-J): ");
                String filaTexto = scanner.nextLine().toUpperCase();

                vista.mostrarMensaje("Ingrese columna (1-10): ");
                int columna = Integer.parseInt(scanner.nextLine());

                int fila = filaTexto.charAt(0) - 'A';

                boolean mina =
                        juego.getTablero().descubrirCasilla(fila, columna - 1);

                if (mina) {

                    vista.mostrarMensaje("\n¡Perdiste! Encontraste una mina.");
                    vista.mostrarMensaje("\nTablero final:");
                    juego.getTablero().mostrarTablero();
                    vista.mostrarMensaje("\nPresione Enter para cerrar...");
                    scanner.nextLine();
                    juego.terminarJuego();
                    break;
                }

            } catch (CasillaYaDescubiertaException e) {

                vista.mostrarMensaje(e.getMessage());

            } catch (Exception e) {

                vista.mostrarMensaje("Entrada inválida.");
            }
        }

        GuardarPartida.guardarJuego(juego);

        scanner.close();
    }
}