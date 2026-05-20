package CineMax;

import java.io.*;
import java.util.*;

import static CineMax.Proiezioni.*;
import static CineMax.Utenti.*;

public class Menu
{
    public static void main (String[] args) throws IOException 
    {
        Scanner scanner = new Scanner(System.in);
        String fileUtenti = "InfoUtenti.txt";
        String fileProiezioni = "Proiezione.txt";


        System.out.print("\n1.Log In\n2.Registrazione\n3.Ospite\n");
        int scelta = scanner.nextInt();
        // Scelta diversa
        if ((scelta != 1) && (scelta != 2) && (scelta != 3)){
            System.out.println("Scelta non valida");
        }

       
        if (scelta == 1) 
        {
            //login
           // fareLogin(scanner);
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            Scanner fileScannerU = new Scanner(new File(fileUtenti));
            Scanner fileScannerP = new Scanner(new File(fileProiezioni));
            String linea = "";
            String[] dati = new String[0];

            boolean autenticazione  = false;
            try (fileScannerU) {
                while (fileScannerU.hasNextLine()) {
                    linea = fileScannerU.nextLine();
                    dati = linea.split(",");

                    if (dati.length == 7 && dati[2].equals(username) && dati[3].equals(password)) {
                        autenticazione  = true;
                        break;
                    }
                }
            }

            //Acesso autorizato
            if (autenticazione ) {
                System.out.println("Login bene-sucedido!\n\nBene-venuto " + username + "!!");

                //Tipo di Menu a Aprire
                try (fileScannerU) {
                    {
                        if (dati[2].equals(username) && dati[3].equals(password)) {

                            //Tipo Menu:Clienti
                            if (dati[6].equals("Clienti")){
                                String userPrenotaPath = "src/CineMax/UtentiProiezione/" + username + "_prenotazione.txt";
                                int sceltaClienti = 0;
                                boolean continua = true;
                                while(continua) {
                                    System.out.print("1.Cercare proiezioni e fare Prenotazione\n2.Visualizzare le proprie prenotazioni\n3.Modificare e cancellare le proprie prenotazioni\n4.Logout\n");
                                    sceltaClienti = scanner.nextInt();
                                    if (sceltaClienti == 1 || sceltaClienti == 2 || sceltaClienti == 3 || sceltaClienti == 4){
                                        continua = false;

                                        //Cercare proiezioni e fare Prenotazione
                                        if(sceltaClienti == 1){
                                            int sceltaMenuCerca = 0;
                                            continua = true;
                                            while(continua) {
                                                System.out.print("1.Tutti gli Proiezioni\n2.Cercare per nome\n3.Filtri\n");
                                                sceltaMenuCerca = scanner.nextInt();
                                                if (sceltaMenuCerca == 1 || sceltaMenuCerca == 2 || sceltaMenuCerca == 3) {
                                                    continua = false;

                                                    //Tutte Proiezioni
                                                    if(sceltaMenuCerca == 1){
                                                        String tuttiProizione = "";
                                                        try (fileScannerP) {
                                                            while (fileScannerP.hasNextLine()) {
                                                                tuttiProizione = tuttiProizione.concat(fileScannerP.nextLine() +"\n");
                                                            }
                                                            System.out.print(tuttiProizione);

                                                            //Prenotare Film
                                                            try(fileScannerP) {
                                                                System.out.print("\nSceglie quale prenotare(solo il nome del film):");
                                                                String filmPrenotato = scanner.nextLine();

                                                                while (fileScannerP.hasNextLine()) {
                                                                    linea = fileScannerP.nextLine();
                                                                    dati = linea.split(",");

                                                                    if (dati.length == 7 && dati[0].equals(filmPrenotato)) {
                                                                        continua = true;
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            if (continua){
                                                                System.out.println("buono");
                                                            }
                                                            else{
                                                                System.out.println("paia");
                                                            }
                                                        }

                                                    }
                                                }

                                                else{
                                                    System.out.println("Scelta non valida!\n");
                                                }
                                            }


                                            //farePrenotazione


                                        }
                                        //Visualizzare le proprie prenotazioni
                                        else if(sceltaClienti == 2){

                                        }
                                        //Modificare e cancellare le proprie prenotazioni
                                        else if(sceltaClienti == 3){

                                        }
                                        //Logout
                                        else if(sceltaClienti == 4){
                                            break;
                                        }
                                    }
                                    else{
                                        System.out.println("\nScelta non valida!");
                                    }
                                }
                            }

                            //Tipo Menu:Proiezionista
                            else if (dati[6].equals("Proiezionista")){
                                int sceltaProiezionista = 0;
                                boolean continua = true;
                                while(continua) {
                                    System.out.print("1.Inserire un film\n2.Modificare la data di una proiezione\n3.Eliminare una proiezione\n4.Logout\n");
                                    sceltaProiezionista = scanner.nextInt();
                                    if (sceltaProiezionista == 1 || sceltaProiezionista == 2 || sceltaProiezionista == 3 || sceltaProiezionista == 4){
                                        continua = false;

                                        //Inserire un film
                                        if(sceltaProiezionista == 1){
                                            aggiungiProiezione(scanner);
                                        }
                                        //Modificare la data di una proiezione
                                        else if(sceltaProiezionista == 2){

                                        }
                                        //Eliminare una proiezione
                                        else if(sceltaProiezionista == 3){

                                        }
                                        //Logout
                                        else if(sceltaProiezionista == 4){
                                            break;
                                        }

                                    //Scelta non valida
                                    }else{
                                        System.out.println("\nScelta non valida!");
                                    }
                                }

                            }

                            //Tipo Menu:Balconista
                            else if (dati[6].equals("Balconista")){
                                int sceltaBalconista = 0;
                                boolean continua = true;
                                while(continua) {
                                    System.out.print("1.Visualizzare le prenotazioni nella data odierna\n2.Cercare una prenotazione\n3.Logout\n");
                                    sceltaBalconista = scanner.nextInt();
                                    if (sceltaBalconista == 1 || sceltaBalconista == 2 || sceltaBalconista == 3){
                                        continua = false;

                                        //IVisualizzare le prenotazioni nella data odierna
                                        if(sceltaBalconista == 1){

                                        }
                                        //Cercare una prenotazione
                                        else if(sceltaBalconista == 2){

                                        }
                                        //Logout
                                        else if(sceltaBalconista == 3){
                                            break;
                                        }

                                    //Scelta non valida
                                    }else{
                                        System.out.println("\nScelta non valida!");
                                    }
                                }
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