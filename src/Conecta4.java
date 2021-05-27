import java.util.Scanner;

public class Conecta4 {

    public static String[][] tablero = new String[5][5];
    public static int columnas;
    public static int filas;
    public static boolean finJuego = false;

    public static void main(String[] args) {
        Conecta4 conecta4 = new Conecta4();
        Scanner scanner = new Scanner(System.in);
        int jugadasMaximas = 1;
        int columna = 0;
        int contador = 0;
        int modoDeJuego = 0;
        String mensajeError = "";
        String jugador1;
        String caracter1;
        String jugador2;
        String caracter2;
        boolean verificador = false;
        boolean verificador2 = false;
        columnas = tablero[1].length;
        filas = tablero.length;
        String auxiliarJugador = "";
        String auxiliarCaracter = "";

        System.out.println("================================================================");
        System.out.println("========================== CONECTA4 ============================");
        System.out.println("================================================================");
        System.out.println("");
        System.out.println("============================================");
        System.out.println("       1.- Jugador 1 vs Jugador 2");
        System.out.println("============================================");

        for (int i = 0; i <= columnas - 1; i++) {
            for (int j = 0; j <= filas - 1; j++) {
                tablero[i][j] = "0";
            }
        }

        while (!verificador) {
            try {
                String auxiliar = scanner.nextLine();
                modoDeJuego = Integer.parseInt(auxiliar);
                if (modoDeJuego == 1 || modoDeJuego == 2) {
                    verificador = true;
                } else {
                    System.out.println("ERROR: Debes ingresar 1");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Debes ingresar 1");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        verificador = false;

        if (modoDeJuego == 1) {
            System.out.println("============================================");
            System.out.println("MODO SELECCIONADO:");
            System.out.println("             Jugador 1 vs Jugador 2");
            System.out.println("Jugador 1 ingresa un nick");
            jugador1 = scanner.nextLine();
            if (jugador1.equals(null) || jugador1.equals("")) {
                jugador1 = "Jugador 1";
            }

            jugador1 = jugador1;
            System.out.println(jugador1 + " ingresa un caracter para usarlo en el juego (Puede ser un signo, numero, letra, etc)");
            caracter1 = scanner.nextLine();
            caracter1 = caracter1.toUpperCase();

            while (caracter1.length() > 1 || caracter1.equals(null) || caracter1.equals("")) {
                if (caracter1.length() > 1) {
                    System.out.println("ERROR: El caracter debe ser de una sola letra");
                    System.out.println("ejemplo: 'X' , 'O' , 'V' , etc...");
                    caracter1 = scanner.nextLine();
                    caracter1 = caracter1.toUpperCase();
                }

                if (caracter1.equals(null) || caracter1.equals("")) {
                    System.out.println("ERROR: El caracter no puede estar vacio, intentalo de nuevo");
                    caracter1 = scanner.nextLine();
                    caracter1 = caracter1.toUpperCase();
                }
            }

            System.out.println("======================================================================================");
            System.out.println("Jugador 2 ingresa un nick");
            jugador2 = scanner.nextLine();

            while (jugador2.equals(jugador1)) {
                System.out.println("ERROR: El jugador 1 ya ha elegido este nick, ingresa otro distinto");
                jugador2 = scanner.nextLine();
            }

            if (jugador2.equals(null) || jugador2.equals("")) {
                jugador2 = "Jugador 2";
            }

            jugador2 = jugador2;

            System.out.println(jugador2 + " ingresa un caracter para usarlo en el juego (Puede ser un signo, numero, letra, etc)");
            caracter2 = scanner.nextLine();
            caracter2 = caracter2.toUpperCase();

            while (caracter2.equals(caracter1) || caracter2.length() > 1 || caracter2.equals(null) || caracter2.equals("")) {
                if (caracter2.equals(null) || caracter2.equals("")) {
                    System.out.println("ERROR: El caracter no puede estar vacio, intentalo de nuevo");
                    caracter2 = scanner.nextLine();
                    caracter2 = caracter2.toUpperCase();
                }

                if (caracter2.equals(caracter1)) {
                    System.out.println("ERROR: El caracter es igual al del jugador 1, ingresa otro");
                    caracter2 = scanner.nextLine();
                    caracter2 = caracter2.toUpperCase();
                }

                if (caracter2.length() > 1) {
                    System.out.println("ERROR: El caracter debe ser de una sola letra, intentalo de nuevo");
                    caracter2 = scanner.nextLine();
                    caracter2 = caracter2.toUpperCase();
                }
            }

            verificador = false;
            caracter1 = caracter1;
            caracter2 = caracter2;

            while (!finJuego) {

                while (!verificador) {

                    conecta4.mostrarTablero(tablero);
                    System.out.println(mensajeError);
                    mensajeError = "";

                    try {
                        System.out.println(jugador1 + " escribe el numero de la columna para poner tu ficha:");
                        String auxiliar = scanner.nextLine();
                        columna = Integer.parseInt(auxiliar) - 1;

                        if (columna > columnas - 1 || columna < 0) {
                            mensajeError = "ERROR: Debes ingresar un numero entre el 1 al " + columnas;

                        } else {
                            contador = 1;
                            for (int i = columnas - 1; i > -1; i = i - 1) {
                                if (!verificador2) {

                                    if (tablero[i][columna] != caracter1 && tablero[i][columna] != caracter2) {
                                        tablero[i][columna] = caracter1;
                                        verificador2 = true;
                                        verificador = true;
                                        jugadasMaximas++;
                                    } else {
                                        contador += 1;
                                    }
                                }

                                if (contador == filas + 1) {
                                    mensajeError = "ERROR: Esta columna esta completa, escoje otra";
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        mensajeError = "ERROR: Has ingresado un caracter no valido, debes ingresar un NUMERO";
                    } catch (Exception e) {
                        mensajeError = "ERROR: Debes ingresar un numero entre el 1 al " + columnas;
                    }
                }

                verificador2 = false;
                verificador = false;
                auxiliarJugador = jugador1;
                auxiliarJugador = caracter1;

                conecta4.verificadorGanador(jugador1, caracter1);

                if (jugadasMaximas == (filas * columnas) + 1) {
                    finJuego = true;
                    conecta4.mostrarTablero(tablero);
                    System.out.println("======================");
                    System.out.println("¡¡¡EMPATE!!!");
                    System.out.println("El tablero esta lleno");
                    System.out.println("======================");
                }

                if (finJuego == false) {

                    while (!verificador) {
                        conecta4.mostrarTablero(tablero);
                        System.out.println(mensajeError);
                        mensajeError = "";

                        try {
                            System.out.println(jugador2 + " escribe el numero de la columna para poner tu ficha:");
                            String auxiliar = scanner.nextLine();
                            columna = Integer.parseInt(auxiliar) - 1;

                            if (columna > columnas - 1 || columna < 0) {
                                mensajeError = "ERROR: Debes ingresar un numero entre el 1 al " + columnas;
                            } else {
                                contador = 1;
                                for (int i = columnas - 1; i > -1; i = i - 1) {
                                    if (!verificador2) {
                                        if (tablero[i][columna] != caracter2 && tablero[i][columna] != caracter1) {
                                            tablero[i][columna] = caracter2;
                                            verificador2 = true;
                                            verificador = true;
                                            jugadasMaximas++;

                                        } else {
                                            contador += 1;
                                        }
                                    }

                                    if (contador == filas + 1) {
                                        mensajeError = "ERROR: Esta columna esta completa, escoje otra";
                                    }
                                }
                            }
                        } catch (NumberFormatException e) {
                            mensajeError = "ERROR: Has ingresado un caracter no valido, debes ingresar un NUMERO";
                        } catch (Exception e) {
                            mensajeError = "ERROR: Debes ingresar un numero entre el 1 al " + columnas;
                        }
                    }

                    verificador2 = false;
                    verificador = false;
                    auxiliarJugador = jugador2;
                    auxiliarJugador = caracter2;
                    conecta4.verificadorGanador(jugador2, caracter2);

                    if (jugadasMaximas == (filas * columnas) + 1) {
                        finJuego = true;
                        conecta4.mostrarTablero(tablero);
                        System.out.println("=======================");
                        System.out.println("¡¡¡EMPATE!!!");
                        System.out.println("El tablero esta lleno");
                        System.out.println("=======================");
                    }
                }
            }
        }
    }

    public void mostrarTablero(String[][] tabla) {
        System.out.print("          ");
        for (int i = 1; i < columnas + 1; i += 1) {
            System.out.print(i + "   ");
        }

        System.out.println("");
        System.out.print("         ");
        for (int i = 0; i < columnas; i += 1) {
            System.out.print("____");
        }

        System.out.println("");
        for (int i = 0; i < filas; i++) {
            System.out.print("       ");
            for (int j = 0; j < filas; j++) {
                System.out.print(" | " + tabla[i][j]);
            }

            System.out.println(" |");
            if (i < filas - 1) {
                System.out.print("        |");
                for (int p = 1; p < columnas - 1; p += 1) {
                    System.out.print("––––––");
                }
                System.out.println("|");
            }
        }

        System.out.print("         ");
        for (int i = 1; i < columnas + 1; i += 1) {
            System.out.print("¯¯¯¯");
        }
        System.out.println("");
    }

    public void verificadorGanador(String auxiliarJugador, String auxilarCaracter) {
        for (int i = 1; i < filas; i += 1) {
            for (int j = 0; j < columnas - 3; j += 1) {
                if (tablero[i][j].equals(auxilarCaracter) && tablero[i][j + 1].equals(auxilarCaracter) && tablero[i][j + 2].equals(auxilarCaracter) && tablero[i][j + 3].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTANDO 4 HORIZONTALMENTE!!!");
                }
            }
        }

        for (int i = 0; i < filas; i += 1) {
            for (int j = 0; j < columnas - 3; j += 1) {
                if (tablero[j][i].equals(auxilarCaracter) && tablero[j + 1][i].equals(auxilarCaracter) && tablero[j + 2][i].equals(auxilarCaracter) && tablero[j + 3][i].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTANDO 4 VERTICALMENTE!!!");
                }
            }
        }

        for (int i = 0; i < columnas - 4 + 1; i += 1) {
            for (int j = 0; j < filas - 4 + 1; j += 1) {
                if (tablero[j][i].equals(auxilarCaracter) && tablero[j + 1][i + 1].equals(auxilarCaracter) && tablero[j + 2][i + 2].equals(auxilarCaracter) && tablero[j + 3][i + 3].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTANDO 4 DIAGONALMENTE!!!");
                }
            }
        }

        for (int i = columnas; i > 3; i -= 1) {
            for (int j = 0; j < filas - 3; j += 1) {
                if (tablero[j][i - 1].equals(auxilarCaracter) && tablero[j + 1][i - 2].equals(auxilarCaracter) && tablero[j + 2][i - 3].equals(auxilarCaracter) && tablero[j + 3][i - 4].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTANDO 4 DIAGONALMENTE!!!");
                }
            }
        }
    }
}