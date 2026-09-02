package cinemax.Ruoli;
import cinemax.*;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * La classe rappresenta il ruolo di Bigliettaio e gestisce la ricerca delle prenotazioni dell'utente
 * @author Toppi Davide, Matricola: 765309, Sede: CO
 * @author Molteni Davide, Matricola: 765300, Sede: CO 
 * @author Lanza Mattia Antonio, Matricola: 766287, Sede: CO 
 * @author Salmazo Bocatto Kauan, Matricola: 767919, Sede: CO 
 */

public class Bigliettaio extends Utente
{
    /**
     * Costruttore della classe Bigliettaio
     * @param nome Nome inserito in fase di registrazione
     * @param cognome Cognome inserito in fase di registrazione
     * @param username Username scelto dall'utente in fase di registrazione
     * @param password Password cifrata
     * @param nascita Data di nascita inserita in fase di registrazione
     * @param domicilio Domicilio inserito in fase di registrazione
     */
    public Bigliettaio(String nome, String cognome, String username, String password, String nascita, String domicilio) 
    {
        super(nome, cognome, username, password, nascita, domicilio, Ruolo.bigliettaio);
    }

    /**
     * Crea un ArrayList contenente tutte le prenotazioni che rispondono ai criteri inseriti dall'utente che sarà utilizzato per il metodo di visualizzazione della prenotazione
     * @param scanner Scanner utilizzato per ottenere l'input dell'utente 
     * @param listaPrenotazioni Array list contenente tutte le prenotazioni contenute all'interno del file 'prenotazioni.csv'
     * @return ArrayList contenente tutte le prenotazioni che rispondono ai criteri inseriti dall'utente 
     */
    public static List<Prenotazione> cercaPrenotazione(Scanner scanner, List<Prenotazione> listaPrenotazioni) 
    {
        List<Prenotazione> risultatoRicerca = new ArrayList<>();
        String codicePrenotazione = "";
        String nomeCognomeCliente = "";
        String titolo = "";
        String risposta = "";

        try 
        {
            boolean inputValido = false;
            
            while(!inputValido) 
            {
                //Ottengo i criteri di ricerca del bigliettaio
                System.out.println("\nInserisci il criterio di ricerca: ");
                System.out.println("1. Codice prenotazione\n2. Nome e cognome cliente\n3. Titolo film\n4. Data proiezione");

                risposta = scanner.nextLine();
                
                switch (risposta) 
                {
                    case "1":
                        codicePrenotazione = "";

                        while(codicePrenotazione.isEmpty()) 
                        {
                            System.out.println("\nInserisci il codice della prenotazione da cercare (o 'esci' per tornare al menu): ");
                            codicePrenotazione = scanner.nextLine().trim();
                            if(codicePrenotazione.equalsIgnoreCase("esci")) 
                            {
                                System.out.println("\nTornando al menu principale...");
                                return risultatoRicerca;
                            }
                            boolean trovato = false;

                            for(Prenotazione prenotazione : listaPrenotazioni) 
                            {
                                if(prenotazione.getCodicePrenotazione().trim().equals(codicePrenotazione)) 
                                {
                                    System.out.println("- Codice: [" + prenotazione.getCodicePrenotazione() + "] | Cliente: " + prenotazione.getNomeCliente() + " " + prenotazione.getCognomeCliente() + " | Film: " + prenotazione.getTitoloFilm());
                                    risultatoRicerca.add(prenotazione);
                                    inputValido = true;
                                    trovato = true;
                                    break;
                                }
                            }

                            if(!trovato) 
                            {
                                System.out.println("\nPrenotazione non trovata");
                                codicePrenotazione = "";
                            }
                        }
                        break;

                    case "2":
                        nomeCognomeCliente = "";

                        while(nomeCognomeCliente.isEmpty()) 
                        {
                            System.out.println("\nInserisci il nome e cognome del cliente da cercare (o 'esci' per tornare al menu): ");
                            nomeCognomeCliente = scanner.nextLine().trim();
                            if(nomeCognomeCliente.equalsIgnoreCase("esci")) 
                            {
                                System.out.println("\nTornando al menu principale...");
                                return risultatoRicerca;
                            }
                            boolean trovato = false;

                            for(Prenotazione prenotazione : listaPrenotazioni) 
                            {
                                String nomeCognome = prenotazione.getNomeCliente().trim() + " " + prenotazione.getCognomeCliente().trim();

                                if(nomeCognome.equalsIgnoreCase(nomeCognomeCliente)) 
                                {
                                    System.out.println("- Codice: [" + prenotazione.getCodicePrenotazione() + "] | Cliente: " + prenotazione.getNomeCliente() + " " + prenotazione.getCognomeCliente() + " | Film: " + prenotazione.getTitoloFilm());
                                    risultatoRicerca.add(prenotazione);
                                    inputValido = true;
                                    trovato = true;
                                }
                            }

                            if(!trovato) 
                            {
                                System.out.println("\nPrenotazione non trovata");
                                nomeCognomeCliente = "";
                            }
                        }
                        break; 

                    case "3":
                        titolo = "";

                        while(titolo.isEmpty()) 
                        {
                            System.out.println("\nInserisci il titolo del film da cercare (o 'esci' per tornare al menu): ");
                            titolo = scanner.nextLine().trim();
                            if(titolo.equalsIgnoreCase("esci")) 
                            {
                                System.out.println("\nTornando al menu principale...");
                                return risultatoRicerca;
                            }
                            boolean trovato = false;

                            for(Prenotazione prenotazione : listaPrenotazioni) 
                            {
                                if(prenotazione.getTitoloFilm().trim().equalsIgnoreCase(titolo)) 
                                {
                                    System.out.println("- Codice: [" + prenotazione.getCodicePrenotazione() + "] | Cliente: " + prenotazione.getNomeCliente() + " " + prenotazione.getCognomeCliente() + " | Film: " + prenotazione.getTitoloFilm());
                                    risultatoRicerca.add(prenotazione);
                                    inputValido = true;
                                    trovato = true;
                                }
                            }

                            if(!trovato) 
                            {
                                System.out.println("\nPrenotazione non trovata");
                                titolo = "";
                            }
                        }
                        break;

                    case "4":
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                        LocalDate dataInizio = null;
                        LocalDate dataFine = null;
                        System.out.println("Puoi inserire un intervallo, premere invio per visualizzare tutte le prenotazioni o inserire 'esci' per tornare al menu");
                        boolean inizioValido = false;
                        while(!inizioValido) 
                        {
                            System.out.print("Data iniziale (gg/mm/aaaa) o 'esci' per tornare al menu: ");
                            String inizio = scanner.nextLine().trim();
                            if(inizio.isEmpty()) 
                            {
                                inizioValido = true; 
                            }
                            else if(inizio.equalsIgnoreCase("esci")) 
                            {
                                System.out.println("\nTornando al menu principale...");
                                return risultatoRicerca;
                            }
                            else 
                            {
                                try 
                                {
                                    dataInizio = LocalDate.parse(inizio, formatter);
                                    inizioValido = true;
                                } 
                                catch (Exception e) 
                                {
                                    System.out.println("\nErrore di formato: assicurati di usare esattamente gg/mm/aaaa");
                                }
                            }
                        }
                        boolean fineValida = false;
                        while(!fineValida) 
                        {
                            System.out.print("Data finale (gg/mm/aaaa)");
                            String fine = scanner.nextLine().trim();
                            if(fine.isEmpty()) 
                            {
                                fineValida = true; 
                            }
                            else 
                            {
                                try 
                                {
                                    dataFine = LocalDate.parse(fine, formatter);
                                    if(dataInizio != null && dataFine.isBefore(dataInizio)) 
                                    {
                                        System.out.println("\nErrore: la data finale non può essere precedente alla data iniziale");
                                    }
                                    else 
                                    {
                                        fineValida = true;
                                    }
                                } 
                                catch (Exception e) 
                                {
                                    System.out.println("\nErrore di formato: assicurati di usare esattamente gg/mm/aaaa");
                                }
                            }
                        }
                        boolean dataTrovata = false;
                        DateTimeFormatter formatCSV = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        for(Prenotazione prenotazione: listaPrenotazioni) 
                        {
                            LocalDate dataPrenotazione = LocalDateTime.parse(prenotazione.getDataOraPrenotazione(), formatCSV).toLocalDate();
                            boolean inRange = true;
                            if(dataInizio != null && dataPrenotazione.isBefore(dataInizio)) inRange = false;
                            if(dataFine != null && dataPrenotazione.isAfter(dataFine)) inRange = false;
                            if(inRange) 
                            {
                                System.out.println("- Codice: [" + prenotazione.getCodicePrenotazione() + "] | Cliente: " + prenotazione.getNomeCliente() + " " + prenotazione.getCognomeCliente() + " | Film: " + prenotazione.getTitoloFilm());
                                risultatoRicerca.add(prenotazione);
                                dataTrovata = true;
                            }
                        }
                        if(!dataTrovata) 
                        {
                            System.out.println("\nNessuna prenotazione trovata in questo periodo");
                        }
                    inputValido = true;
                    break;
                    default:
                        System.out.println("\nScelta non valida, inserisci un numero tra 1 e 4");
                }
            }                
        } 
        catch (Exception e) 
        {
            System.out.println("\nErrore durante la ricerca della prenotazione: " + e.getMessage());
        }
        return risultatoRicerca;
    }

}

