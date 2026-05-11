package CineMax;

import prog.io.*;

public class Menu{
    public static void main (String[] args) {
        ConsoleOutputManager out = new ConsoleOutputManager();
        ConsoleInputManager in = new ConsoleInputManager();

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
            }
            if (scelta == 2) {
                //Registrare
                String nome = in.readLine("Tuo nome:\n");
                String cognome = in.readLine("\nTuo cognome:\n");
                String username = in.readLine("\nTuo username:\n");
                int password= in.readInt("\nTua password(Solo Numero):\n");
                String nascista = in.readLine("\nTua nascita:\n");
                String domicilio  = in.readLine("\nTuo Domiciolio:\n");
                char role = in.readChar("\nTuo Role(Solo la prima lettera):\nc:clienti\np:proiezionista\nb:bigliettaio");

               Utenti nuovoUtenti = new Utenti(nome,cognome,username,password,nascista,domicilio,role);
               out.println(nuovoUtenti.toString());
            }
        }

        //Guest
        if (scelta == 2) {

            }
        }


    }