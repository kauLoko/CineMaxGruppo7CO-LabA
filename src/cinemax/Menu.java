
import java.io.*;
import java.util.*;

public class Menu
{
    public static void main (String[] args) throws IOException 
    {
        Scanner scanner = new Scanner(System.in);

        String fileUtenti = "data/datiUtenti.csv";
        String fileProiezioni = "data/proiezioni.csv";


        System.out.print("\n1.Log In\n2.Registrazione\n3.Accedi come ospite\n");
        int scelta = scanner.nextInt();



        switch(scelta) 
        {
            case 1:     //LOG IN
                System.out.print("Username: ");
                String username = scanner.nextLine();

                System.out.print("Password: ");
                String password  = scanner.nextLine();

                boolean autenticazione  = false;
                
                Scanner fileScannerP = new Scanner(new File(fileProiezioni));
                Scanner fileScannerPD = new Scanner(new File(fileProiezioni));
                
                try (Scanner fileScannerU = new Scanner(new File(fileUtenti))) 
                {
                    while (fileScannerU.hasNextLine()) 
                    {
                        String linea = fileScannerU.nextLine();
                        String[] dati = linea.split(",");

                        if (dati.length == 7 && dati[2].equals(username) && dati[3].equals(password)) 
                        {
                            autenticazione  = true;
                            break;
                        }
                    }
                }

                if (!autenticazione) 
                {
                    throw new Exception("Utente non trovato o credenziali errate.");
                }
                else //ACCESSO EFFETTUATO
                {
                    System.out.println("Login effettuato!\n\nBenvenuto" + username);    
                }
                
                boolean inSessione = true;
                
                //MENU PRINCIPALE CLIENTE

                while (inSessione) 
                {
                    System.out.println("\n--- MENU CLIENTE ---");
                    System.out.println("1. Cercare proiezioni e fare Prenotazione");
                    System.out.println("2. Visualizzare le proprie prenotazioni");
                    System.out.println("3. Modificare e cancellare le proprie prenotazioni");
                    System.out.println("4. Logout");
                    System.out.print("Scelta: ");
                
                    int sceltaClienti = scanner.nextInt();

                    switch(sceltaClienti)
                    {
                        case 1:
                            gestireRicercaEPrenotazione(scanner, fileProiezioni);
                            break;
                        case 2: 
                            System.out.println("Visualizza prenotazioni");
                            break;
                        case 3:
                            System.out.println("Modifica/Cancella prenotazione");
                            break;
                        case 4:
                            System.out.println("Logout effettuato con successo.");
                            inSessione = false;
                            break;
                        default:
                            System.out.println("Opzione non valida.");
                            break;
                    }

                }

            case 2:     //REGISTRAZIONE
                
                break;
            case 3:     //ACCESSO COME OSPITE
                
                break;
            default:
                System.out.println("Scelta non valida");
                break;
        }
        

       
        if (scelta == 1) 
        {


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