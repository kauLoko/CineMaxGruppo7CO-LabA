import java.io.*;
import java.util.*;

public class Bigliettaio extends Utente
{
    //Costruttore
    public Bigliettaio(String nome, String cognome, String username, String password, String nascita, String domicilio) 
    {
        super(nome, cognome, username, password, nascita, domicilio, Ruolo.bigliettaio);
    }

    public static void cercaPrenotazione(Scanner scanner, List<Prenotazione> listaPrenotazioni) 
    {
        
        String codicePrenotazione = "";
        String nomeCognomeCliente = "";
        String titolo = "";
        String data = "";
        String risposta = "";

        try 
        {
            boolean inputValido = false;
            
            while(!inputValido) 
            {
                System.out.println("\nInserisci il criterio di ricerca: ");
                System.out.println("1. Codice prenotazione\n2. Nome e cognome cliente\n3. Titolo film\n4. Data proiezione");

                risposta = scanner.nextLine();
                
                switch (risposta) 
                {
                    case "1":
                        codicePrenotazione = "";

                        while(codicePrenotazione.isEmpty()) 
                        {
                            System.out.println("Inserisci il codice della prenotazione da cercare: ");

                            codicePrenotazione = scanner.nextLine().trim();
                            boolean trovato = false;

                            for(Prenotazione prenotazione : listaPrenotazioni) 
                            {
                                if(prenotazione.getCodicePrenotazione().trim().equals(codicePrenotazione)) 
                                {
                                    System.out.println("Prenotazione trovata: " + prenotazione);
                                    inputValido = true;
                                    trovato = true;
                                    break;
                                }
                            }

                            if(!trovato) 
                            {
                                System.out.println("Prenotazione non trovata");
                                codicePrenotazione = "";
                            }
                        }
                        break;

                    case "2":
                        nomeCognomeCliente = "";

                        while(nomeCognomeCliente.isEmpty()) 
                        {
                            System.out.println("\nInserisci il nome e cognome del cliente da cercare: ");
                            nomeCognomeCliente = scanner.nextLine().trim();
                            boolean trovato = false;

                            for(Prenotazione prenotazione : listaPrenotazioni) 
                            {
                                String nomeCognome = prenotazione.getNomeCliente().trim() + " " + prenotazione.getCognomeCliente().trim();

                                if(nomeCognome.equalsIgnoreCase(nomeCognomeCliente)) 
                                {
                                    System.out.println("Prenotazione trovata: " + prenotazione);
                                    inputValido = true;
                                    trovato = true;
                                    break;
                                }
                            }

                            if(!trovato) 
                            {
                                System.out.println("Prenotazione non trovata");
                                nomeCognomeCliente = "";
                            }
                        }
                        break; 

                    case "3":
                        titolo = "";

                        while(titolo.isEmpty()) 
                        {
                            System.out.println("\nInserisci il titolo del film da cercare: ");
                            titolo = scanner.nextLine().trim();
                            boolean trovato = false;

                            for(Prenotazione prenotazione : listaPrenotazioni) 
                            {
                                if(prenotazione.getTitoloFilm().trim().equalsIgnoreCase(titolo)) 
                                {
                                    System.out.println("Prenotazione trovata: " + prenotazione);
                                    inputValido = true;
                                    trovato = true;
                                    break;
                                }
                            }

                            if(!trovato) 
                            {
                                System.out.println("Prenotazione non trovata");
                                titolo = "";
                            }
                        }
                        break;

                    case "4":
                        data = "";
                        //Da capire come confrontare e utilizzare i range di date
                }
            }                
        } 
        catch (Exception e) 
        {
            System.out.println("Errore durante la ricerca della prenotazione: " + e.getMessage());
        }
    }

}

