package CineMax;

import prog.io.*;

import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Menu{
    public static void main (String[] args) throws IOException {
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
                String userLog = in.readLine("Username: ");
                String passLog = in.readLine("Passoword: ");

                File file = new File("UntentiIInfo2.txt");
                Scanner scan = new Scanner(file);
                String fileContent = "";

                while (scan.hasNextLine()) {
                    fileContent = fileContent.concat(scan.nextLine() + "\n");
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter("UntentiIInfo2.txt"));
                writer.write(fileContent);
                writer.close();

                String perLeggere = "";

                while(scan.hasNextLine()){
                    writer.write(perLeggere + "\n");
                    if(perLeggere.contains(userLog) && perLeggere.contains(passLog)){
                        out.println("Login Sucesso!");
                    }
                    else {
                        out.println("Username o Password sbagliato");
                    }
                }

            }
            if (scelta == 2) {
                //Registrare
                String nome = in.readLine("Tuo nome: ");
                String cognome = in.readLine("\nTuo cognome: ");
                String username = in.readLine("\nTuo username: ");
                int password= in.readInt("\nTua password(Solo Numero): ");
                String nascista = in.readLine("\nTua nascita: ");
                String domicilio  = in.readLine("\nTuo Domiciolio: ");
                String role = in.readLine("\nTuo Role(Solo la prima lettera):\nc:clienti\np:proiezionista\nb:bigliettaio\n");

               Utenti nuovoUtenti = new Utenti(nome,cognome,username,password,nascista,domicilio,role);
               nuovoUtenti.registraCliente();
               out.println(nuovoUtenti.toString());
            }
        }

        //Guest
        if (scelta == 2) {

            }
        }


    }