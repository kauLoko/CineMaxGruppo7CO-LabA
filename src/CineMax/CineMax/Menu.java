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
        String fileUtenti = "InfoUtenti.txt";

        int scelta = in.readInt("\n1.Log In\n2.Registrazione\n3.Ospite\n");
        // Scelta diversa
        if ((scelta != 1) && (scelta != 2) && (scelta != 3)){
            out.println("Scelta non valida");
        }

       
        if (scelta == 1) 
        {
            //login
           // fareLogin(scanner);
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            Scanner fileScanner = new Scanner(new File(fileUtenti));
            String linha = "";
            String[] dados = new String[0];
            String tipoLogin = "";

            boolean autenticazione  = false;
            try (fileScanner) {
                while (fileScanner.hasNextLine()) {
                    linha = fileScanner.nextLine();
                    dados = linha.split(",");

                    if (dados.length == 7 && dados[2].equals(username) && dados[3].equals(password)) {
                        autenticazione  = true;
                        break;
                    }
                }
            }

            //Acesso autorizato
            if (autenticazione ) {
                out.println("Login bene-sucedido! Bene-venuto " + username);

                //tipo di Menu a Aprire
                try (fileScanner) {
                    {

                        if (dados[2].equals(username) && dados[3].equals(password)) {
                            //Tipo Menu:Clienti
                            if (dados[6].equals("Clienti")){
                                int sceltaClienti = 0;
                                boolean continua = true;
                                while(continua) {
                                    sceltaClienti = in.readInt("1.Cercare proiezioni\n2.Visualizzare le proprie prenotazioni\n3.Modificare e cancellare le proprie prenotazioni\n4.Logout\n");
                                    if (sceltaClienti == 1 || sceltaClienti == 2 || sceltaClienti == 3 || sceltaClienti == 4){
                                        continua = false;
                                    }
                                    else{
                                        out.println("\nScelta non valida!");
                                    }
                                }




                            }

                            //Tipo Menu:Proiezionista
                            else if (dados[6].equals("Proiezionista")){
                                out.println("Proiezionista");

                            }

                            //Tipo Menu:Balconista
                            else if (dados[6].equals("Balconista")){
                                out.println("Balconista");

                            }
                        }
                    }
                }
            } else {
                System.out.println("Username o password incorrect.");
            }


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