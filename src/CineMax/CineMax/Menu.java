package CineMax;

import prog.io.*;
import java.io.*;
import java.util.*;

import static CineMax.Proiezioni.*;
import static CineMax.Utenti.*;

public class Menu
{
    public static void main (String[] args) throws IOException 
    {
        ConsoleOutputManager out = new ConsoleOutputManager();
        ConsoleInputManager in = new ConsoleInputManager();
        Scanner scanner = new Scanner(System.in);

        int scelta = in.readInt("\n1.Log In\n2.Registrazione\n3.Ospite\n");
        // Scelta diversa
        if ((scelta != 1) && (scelta != 2) && (scelta != 3)){
            out.println("Scelta non valida");
        }

       
        if (scelta == 1) 
        {
            //login
            fareLogin(scanner);


        }
        if (scelta == 2) 
        {
            //registrare
            registrareUtente(scanner);
        }
        if (scelta == 3) 
        {
            aggiungiProiezione(scanner); //Testing
            //accesso come ospite

        }
    }


}