// package cinemax;

import java.util.*;

public class Menu
{

    public static Utente menuEseguiLogin(Scanner scanner) 
    {
        System.out.println("-- LOGIN --");
        // Ottengo username
        System.out.println("Username: ");
        String username = scanner.nextLine().trim();
        // Ottengo password
        System.out.println("Password: ");
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
                    datiProiezioni.cercaProiezione(scanner, listaProiezioni);
                    datiProiezioni.visualizzaProiezione(scanner, listaProiezioni);
                    ClienteRegistrato.creaPrenotazione(scanner, listaProiezioni, utente, listaPrenotazioni);
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
                    System.out.println("Logout effettuato");
                    break;
                default:
                    System.out.println("Opzione non valida Inserisci un numero tra 0 e 4");
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
     * Mostra il menù per effettuare la registrazione di un nuovo utente
     * @param scanner Scanner per ottenere l'input dell'utente
     */
    public static void menuRegistrazioneUtente(Scanner scanner) 
    {
        System.out.println("-- REGISTRAZIONE CLIENTE --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Cognome: ");
        String cognome = scanner.nextLine().trim();

        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String passwordChiara = scanner.nextLine().trim();

        System.out.print("Data di nascita (gg/mm/aaaa): ");
        String nascita = scanner.nextLine().trim();

        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine().trim();

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

