package CineMax;

import prog.io.*;
import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
            registrareCliente(scanner);
        }
        if (scelta == 3) 
        {
            //accesso come ospite

        }
        

        //Guest
        if (scelta == 2) {

            }
        }


    }