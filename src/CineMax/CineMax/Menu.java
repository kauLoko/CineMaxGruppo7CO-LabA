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
            System.out.print("Username: ");
            String username = scanner.next();
            String password = "";
            if(username != null){
            System.out.print("Password: ");
            password  = scanner.next();
            }

            Scanner fileScannerU = new Scanner(new File(fileUtenti));
            Scanner fileScannerP = new Scanner(new File(fileProiezioni));
            Scanner fileScannerPD = new Scanner(new File(fileProiezioni));
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

                                //Variables per quando vai a salvare prenotazione
                                String userPrenotaPath = "src/CineMax/UtentiProiezione/" + username + "_prenotazione.txt";

                                File filePrenotazioneUser = new File(userPrenotaPath);
                                FileWriter fwb = new FileWriter(userPrenotaPath,true);
                                BufferedWriter bwp = new BufferedWriter(fwb);
                                PrintWriter pw = new PrintWriter(bwp);
                                //

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

                                                                System.out.print("\nSceglie quale prenotare(solo il nome del film):");
                                                                String filmPrenotato = scanner.next();

                                                                String lineaF = "";
                                                                String[] datiF = new String[0];
                                                                boolean prenotaBuono = false;
                                                                String infoFilme = "";



                                                                while (fileScannerPD.hasNextLine()) {
                                                                    lineaF = fileScannerPD.nextLine();
                                                                    datiF = lineaF.split(",");

                                                                    if (datiF.length == 7 && datiF[0].equals(filmPrenotato)) {
                                                                        prenotaBuono = true;
                                                                        infoFilme = lineaF;
                                                                        break;
                                                                    }
                                                                }

                                                            if (prenotaBuono){
                                                                try(fwb;bwp;pw) {
                                                                    pw.println(infoFilme);
                                                                }



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
                                        }

                                        //Visualizzare le proprie prenotazioni

                                        else if(sceltaClienti == 2){
                                            try(BufferedReader brP = new BufferedReader(new FileReader(filePrenotazioneUser))){
                                                String userPrenotazione;
                                                while((userPrenotazione = brP.readLine()) != null){
                                                    System.out.print(userPrenotazione+ "\n");

                                                }

                                            }
                                            System.out.println();

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