package cinemax;
import cinemax.Ruoli.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Classe che rappresenta i menù dei diversi ruoli assegnabili all'utente e utilizzati per gestire input e output
 * @author Toppi Davide, Matricola: 765309, Sede: CO
 * @author Molteni Davide, Matricola: 765300, Sede: CO 
 * @author Lanza Mattia Antonio, Matricola: 766287, Sede: CO 
 * @author Salmazo Bocatto Kauan, Matricola: 767919, Sede: CO 
 * 
 *  */

public class Menu
{

    public static Utente menuEseguiLogin(Scanner scanner) 
    {
        System.out.println("-- LOGIN --");
        // Ottengo username
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        // Ottengo password
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        // Passo i dati al metodo eseguilogin vero e proprio
        return Utente.eseguiLogin(username, password);
    }

    /**
     * Mostra il menù principale all'avvio del programma
     * @param scanner Scanner per raccogliere l'input dell'utente
     * @return La scelta dell'utente 
     */
    public static String mostraMenuPrincipale(Scanner scanner) 
    {
        System.out.println("=================================================");
        System.out.println("=          BENVENUTO NEL SISTEMA CINEMAX        =");
        System.out.println("=================================================");

        System.out.println("\n--- MENU PRINCIPALE ---");
        System.out.println("1. Login");
        System.out.println("2. Registrati come Cliente");
        System.out.println("3. Prosegui come Ospite (Guest)");
        System.out.println("4. Esci");
        System.out.print("Scelta: ");
        return scanner.nextLine().trim();
    }

    /**
     * Mostra il menù del cliente registrato una volta eseguito il login
     * @param scanner Scanner per ottenere l'input dell'utente
     * @param utente Profilo dell'utente utilizzato per modifcare le sue prenotazioni
     * @param listaProiezioni Array list contenente tutte le proiezioni contenute all'interno del file 'proiezioni.csv'
     * @param listaPrenotazioni Array list contenente tutte le prenotazioni contenute all'interno del file 'prenotazioni.csv'
     */
    public static void menuUtenteLoggato(Scanner scanner, Utente utente, List<Proiezione> listaProiezioni, List<Prenotazione> listaPrenotazioni) 
    {
        String scelta;
        do {
            System.out.println("\n--- MENU' CLIENTE (" + utente.getUsername() + ") ---");
            System.out.println("1. Inserisci una prenotazione");
            System.out.println("2. Visualizza le tue prenotazioni");
            System.out.println("3. Modifica una prenotazione");
            System.out.println("4. Cancella una prenotazione");
            System.out.println("0. Logout (Torna al menu principale)");
            System.out.print("Seleziona: ");
            scelta = scanner.nextLine().trim();
            switch (scelta) {
                case "1":
                    List<Proiezione> risultati = datiProiezioni.cercaProiezione(scanner, listaProiezioni);
                    if(risultati != null && !risultati.isEmpty()) 
                    {
                        System.out.println("Vuoi visualizzare i dettagli di una di queste proiezioni? (s/n)");
                        String risposta = scanner.nextLine().trim().toLowerCase();
                        if (risposta.equals("s")) 
                        {
                            datiProiezioni.visualizzaProiezione(scanner, risultati);
                        }
                        System.out.println("\nVuoi effettuare una prenotazione per una di queste proiezioni? (s/n)");
                        String rispostaPrenotazione = scanner.nextLine().trim().toLowerCase();
                        if (rispostaPrenotazione.equals("s")) 
                        {
                            ClienteRegistrato.creaPrenotazione(scanner, risultati, utente, listaPrenotazioni);
                        }
                    }
                    break;
                case "2":
                    Utente.visualizzaPrenotazione(scanner, utente, listaPrenotazioni);
                    break;
                case "3":
                    ClienteRegistrato.modificaPrenotazione(scanner, listaProiezioni, listaPrenotazioni);
                    break;
                case "4":
                    ClienteRegistrato.eliminaPrenotazione(scanner, listaPrenotazioni);
                    break;
                case "0":
                    System.out.println("\nLogout effettuato\n");
                    break;
                default:
                    System.out.println("\nOpzione non valida Inserisci un numero tra 0 e 4");
                    break;
            }
        } 
        while(!scelta.equals("0"));
    }
    
    /**
     * Mostra il menù del proiezionista una volta eseguito il login
     * @param scanner Scanner per ottenere l'input dell'utente 
     * @param utente Profilo del proiezionista
     * @param listaProiezioni Array list contenente tutte le proiezioni contenute all'interno del file 'proiezioni.csv'
     * @param listaPrenotazioni Array list contenente tutte le prenotazioni contenute all'interno del file 'prenotazioni.csv'
     */
    public static void menuProiezionista(Scanner scanner, Utente utente, List<Proiezione> listaProiezioni, List<Prenotazione> listaPrenotazioni) 
    {
        String scelta;
        do {
            System.out.println("\n--- MENU PROIEZIONISTA (" + utente.getUsername() + ") ---");
            System.out.println("1. Aggiungi una nuova proiezione");
            System.out.println("2. Modifica una proiezione esistente");
            System.out.println("3. Elimina una proiezione");
            System.out.println("0. Logout (Torna al menu principale)");
            System.out.print("Seleziona: ");
            scelta = scanner.nextLine().trim();
            switch (scelta) {
                case "1":
                    Proiezionista.aggiungiProiezione(scanner, listaProiezioni);
                    break;
                case "2":
                    Proiezionista.modificaProiezione(scanner, listaProiezioni);
                    break;
                case "3":
                    Proiezionista.eliminaProiezione(scanner, listaProiezioni);
                    break;
                case "0":
                    System.out.println("Logout effettuato");
                    break;
                default:
                    System.out.println("Opzione non valida: inserisci un numero tra 0 e 3");
                    break;
            }
        } 
        while(!scelta.equals("0"));
    }

    /**
     * Mostra il menù del bigliettaio una volta eseguito il login
     * @param scanner Scanner per ottenere l'input dell'utente
     * @param utente Profilo del bigliettaio
     * @param listaProiezioni Array list contenente tutte le proiezioni contenute all'interno del file 'proiezioni.csv'
     * @param listaPrenotazioni Array list contenente tutte le prenotazioni contenute all'interno del file 'prenotazioni.csv'
     */
    public static void menuBigliettaio(Scanner scanner, Utente utente, List<Proiezione> listaProiezioni, List<Prenotazione> listaPrenotazioni) 
    {
        String scelta;
        do {
            System.out.println("\n--- MENU BIGLIETTAIO (" + utente.getUsername() + ") ---");
            System.out.println("1. Cerca una prenotazione");
            System.out.println("2. Visualizza dettagli prenotazione (tramite codice univoco)");
            System.out.println("0. Logout (Torna al menu principale)");
            System.out.print("Seleziona: ");
            scelta = scanner.nextLine().trim();
            switch (scelta) {
                case "1":
                    Bigliettaio.cercaPrenotazione(scanner, listaPrenotazioni);
                    break;
                case "2":
                    Bigliettaio.visualizzaPrenotazione(scanner, utente, listaPrenotazioni);;
                    break;
                case "0":
                    System.out.println("Logout effettuato");
                    break;
                default:
                    System.out.println("Opzione non valida: inserisci un numero tra 0 e 2");
                    break;
            }
        } 
        while(!scelta.equals("0"));
    }

    /**
     * Mostra il menù dell'utente guest non registrato offrendo la possibilità di registrarsi o visualizzare una proiezione
     * @param scanner Scanner per ottenere l'input dell'utente
     * @param listaProiezioni Array list contenente tutte le proiezioni contenute all'interno del file 'proiezioni.csv'
     */
    public static void menuGuest(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        String scelta;
        do {
            System.out.println("\n--- OPZIONI GUEST ---");
            System.out.println("1. Cerca Proiezioni");
            System.out.println("2. Registrati come cliente");
            System.out.println("0. Torna al menu principale");
            System.out.print("Seleziona: ");
            scelta = scanner.nextLine().trim();
            switch (scelta) 
            {
                case "1":
                    List<Proiezione> risultati = datiProiezioni.cercaProiezione(scanner, listaProiezioni);
                    if(risultati != null && !risultati.isEmpty()) 
                    {
                        System.out.println("Vuoi visualizzare i dettagli di una di queste proiezioni? (s/n)");
                        String risposta = scanner.nextLine().trim().toLowerCase();
                        if (risposta.equals("s")) 
                        {
                            datiProiezioni.visualizzaProiezione(scanner, risultati);
                        }
                    }
                    break;
                case "2":
                    Menu.menuRegistrazioneUtente(scanner);
                    break;
                
                case "0":
                    System.out.println("Grazie per aver usato CineMax. Arrivederci!");
                    break;
                default:
                    System.out.println("Opzione non valida: inserire un numero tra 0 e 2");
            }
        }
        while(!scelta.equals("0"));
    }  

    /**
     * Mostra il menù per effettuare la registrazione di un nuovo utente e raccoglie i dati 
     * @param scanner Scanner per ottenere l'input dell'utente
     */
    public static void menuRegistrazioneUtente(Scanner scanner) 
    {
        System.out.println("-- REGISTRAZIONE CLIENTE --");
        String nome = "";
        while (nome.isEmpty()) 
        {
            System.out.print("Nome: ");
            nome = scanner.nextLine().trim();
            if (nome.isEmpty())
            {
                 System.out.println("Errore: il nome non può essere vuoto.");
            }    
        }

        String cognome = "";
        while (cognome.isEmpty()) 
        {
            System.out.print("Cognome: ");
            cognome = scanner.nextLine().trim();
            if (cognome.isEmpty()) 
            {
                System.out.println("Errore: il cognome non può essere vuoto.");
            } 
        }

        String username = "";
        while (username.isEmpty()) 
        {
            System.out.print("Username: ");
            username = scanner.nextLine().trim();
            if (username.isEmpty()) 
            {
                System.out.println("Errore: lo username non può essere vuoto.");
            } 
        }

        String passwordChiara = "";
        while (passwordChiara.isEmpty()) 
        {
            System.out.print("Password: ");
            passwordChiara = scanner.nextLine().trim();
            if (passwordChiara.isEmpty()) 
            {
                System.out.println("Errore: la password non può essere vuoto.");
            } 
        }

        String nascita = "";
        boolean dataValida = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        while (!dataValida) {
            System.out.print("Data di nascita (gg/mm/aaaa): ");
            nascita = scanner.nextLine().trim();
            if (nascita.isEmpty()) {
                System.out.println("Errore: la data di nascita non può essere vuota.");
            } else {
                try {
                    LocalDate.parse(nascita, formatter);
                    dataValida = true; 
                } catch (Exception e) {
                    System.out.println("Errore di formato: usa esattamente gg/mm/aaaa (es. 15/05/2002).");
                }
            }
        }

        String domicilio = "";
        while (domicilio.isEmpty()) 
        {
            System.out.print("Domicilio: ");
            domicilio = scanner.nextLine().trim();
            if (domicilio.isEmpty()) 
            {
                System.out.println("Errore: il domicilio non può essere vuoto.");
            } 
        }

        boolean successo = Utente.registraNuovoCliente(nome, cognome, username, passwordChiara, nascita, domicilio);
        if(successo) 
        {
            System.out.println("Nuovo utente registrato con sucesso! Ora puoi effettuare il login dal menù principale");
        }
        else 
        {
            System.out.println("Si è verificato un errore durante la registrazione");
        }
    }
}

