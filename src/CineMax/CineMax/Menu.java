package CineMax;

import prog.io.*;
import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static CineMax.Utenti.*;

public class Menu{
    public static void main (String[] args) throws IOException {
        ConsoleOutputManager out = new ConsoleOutputManager();
        ConsoleInputManager in = new ConsoleInputManager();
        Scanner scanner = new Scanner(System.in);

        int scelta = in.readInt("Buon giorno! Sceigli como voule logare: \n1.Log In\n2.Guest\n");
        // Scelta diversa
        if ((scelta != 1) && (scelta != 2)){
            out.println("scelta non valida");
        }

        //LogIn
        if (scelta == 1) {
            scelta = in.readInt("Scegli:\n 1.login\n 2.Registrare\n");
            if ((scelta != 1) & (scelta != 2)){
                out.println("scelta non valida");
            }
            if (scelta == 1) {
                //fare login
                fareLogin(scanner);
            }
            if (scelta == 2) {
                //Registrare
                registrareCliente(scanner);
            }
        }

        //Guest
        if (scelta == 2) {

            }
        }


    }