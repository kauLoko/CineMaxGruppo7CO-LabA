package CineMax;

import prog.io.*;

public class Menu {
    public void main(String[] args) {
        ConsoleOutputManager out = new ConsoleOutputManager();
        ConsoleInputManager in = new ConsoleInputManager();

        int scelta = in.readInt("Buon giorno! Sceigli como voule logare: \n 1.Clienti\n2.Proiezionista\n3.bigliettai\n4.Guest\n");
        // Scelta diversa
        if ((scelta != 1) & (scelta != 2) & (scelta != 3) & (scelta != 4)){
            out.println("scelta non valida");
        }

        //clienti
        if (scelta == 1) {
            scelta = in.readInt("Scegli:\n 1.login\n 2.Registrare\n");
            if ((scelta != 1) & (scelta != 2)){
                out.println("scelta non valida");
            }
            if (scelta == 1) {
                //fare login
            }
            if (scelta == 2) {
                //Registrare
            }
        }

        //Proiezionista
        if (scelta == 2) {
            scelta = in.readInt("Scegli:\n 1.login\n 2.Registrare\n");
            if ((scelta != 1) & (scelta != 2) ){
                out.println("scelta non valida");
            }
            if (scelta == 1) {
                //fare login
            }
            if (scelta == 2) {
                //Registrare
            }
        }

        //Bigletai
        if (scelta == 3) {
            scelta = in.readInt("Scegli:\n 1.login\n 2.Registrare\n");
            if ((scelta != 1) & (scelta != 2) ){
                out.println("scelta non valida");
            }
            if (scelta == 1) {
                //fare login
            }
            if (scelta == 2) {
                //Registrare
            }
        }

        //non registrato
        if (scelta == 4) {
            // trovare Film
        }
    }
}