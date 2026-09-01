package cinemax;
import java.io.*;
import java.util.*;

/**
 * La classe Utente rappresenta un utente del sistema con i relativi dettagli.
 * Contiene informazioni personali, credenziali di accesso e ruolo dell'utente.
 * Fornisce metodi per la registrazione, il login e la visualizzazione delle prenotazioni.
 * @author Toppi Davide, Matricola: 765309, Sede: CO
 * @author Molteni Davide, Matricola: 765300, Sede: CO 
 * @author Lanza Mattia Antonio, Matricola: 766287, Sede: CO 
 * @author Salmazo Bocatto Kauan, Matricola: 767919, Sede: CO 
 */
public class Utente 
{
    //Campi
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String nascita;
    private String domicilio;
    private Ruolo ruolo;

    public static final String fileUtenti = "data/datiUtenti.csv";

    public enum Ruolo {cliente, proiezionista, bigliettaio};

    /**
     * Costruttore della classe Utente.
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param username Lo username scelto dall'utente.
     * @param password La password cifrata dell'utente.
     * @param nascita La data di nascita dell'utente.
     * @param domicilio Il domicilio dell'utente.
     * @param ruolo Il ruolo dell'utente nel sistema (cliente, proiezionista, bigliettaio).
     */
    public Utente(String nome, String cognome, String username, String password, String nascita, String domicilio, Ruolo ruolo) 
    {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.nascita = nascita;
        this.domicilio = domicilio;
        this.ruolo = ruolo;
    }

    //Metodi getter
    public String getNome() 
    {
        return nome;
    }

    public String getCognome() 
    {
        return cognome;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }

    public String getNascita() 
    {
        return nascita;
    }
    
    public String getDomicilio() 
    {
        return domicilio;
    }
    
    public Ruolo getRuolo() 
    {
        return ruolo;
    }

    /**
     * Salva le informazioni dell'utente nel file CSV specificato.
     * Apre il file in modalità append e scrive i dettagli dell'utente in una nuova riga.
     * Gestisce eventuali eccezioni durante l'operazione di scrittura su file.
     */
    public void salvaSuFile()
    {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileUtenti, true)))) {
            pw.println(nome + "," + cognome + "," + username + "," + password + "," + nascita + "," + domicilio + "," + ruolo);
            System.out.println(">> Utente registrato e salvato con successo!");
        } 
        catch (IOException e) 
        {
            System.err.println("Errore durante il salvataggio su file: " + e.getMessage());
        }
    }

    /**
     * Esegue il login dell'utente confrontando le credenziali inserite con quelle presenti nel file CSV.
     * Cifra la password inserita e verifica se esiste un utente con lo stesso username e password cifrata.
     * @param username Lo username inserito dall'utente.
     * @param passwordChiara La password in chiaro inserita dall'utente.
     * @return Un oggetto Utente se il login ha successo, altrimenti null.
     */
    public static Utente eseguiLogin(String username, String passwordChiara) 
    {
        String passwordCifrata = PasswordUtils.cifraPassword(passwordChiara);
        try (BufferedReader br = new BufferedReader(new FileReader(fileUtenti))) 
        {
            br.readLine(); //saltare intestazione
            String riga;

            while ((riga = br.readLine()) != null) 
            {
                if (riga.trim().isEmpty()) continue;

                String[] campi = riga.split(",", -1); //-1 è il limit, serve per dire di continuare a fare split più volte possibile

                // Bastano >= 7 campi per accedere agli indici da 0 a 6 in sicurezza
                if (campi.length >= 7 && campi[2].trim().equals(username) && campi[3].trim().equals(passwordCifrata)) 
                {
                    System.out.println("\nAccesso consentito! Benvenuto/a nel sistema.");
                    // Recuperoo il ruolo dell'utente dal CSV per gestire dopo i menù e permessi
                    Ruolo ruoloUtente = Ruolo.valueOf(campi[6].trim().toLowerCase());
                    return new Utente(
                        campi[0].trim(), 
                        campi[1].trim(), 
                        campi[2].trim(), 
                        campi[3].trim(), 
                        campi[4].trim(), 
                        campi[5].trim(),
                        ruoloUtente
                    );
                }
            }
        }    
        catch (IOException e) 
        {
            System.err.println("Errore durante la lettura del file utenti: " + e.getMessage());
        }

        System.out.println("\nCredenziali errate o utente non trovato.");
        return null;
    }

    /**
     * Metodo che gestisce la logica della registrazione di un nuovo cliente cifrando la password, creando l'oggetto Utente e aggiornando il file csv
     * @param nome Il nome dell'utente
     * @param cognome Il cognome dell'utente
     * @param username Lo username scelto dall'utente
     * @param passwordChiara La password inserita dall'utente prima di essere cifrata
     * @param nascita La data di nascita inserita dall'utente
     * @param domicilio Il domicilio inserito dall'utente
     * @return true se la registrazione è avvenuta con successo
     */
    public static boolean registraNuovoCliente(String nome, String cognome, String username, String passwordChiara, String nascita, String domicilio) 
    {
        //Cifro la password
        String passwordCifrata = PasswordUtils.cifraPassword(passwordChiara);    
        //Do un ruolo
        Ruolo ruolo = Ruolo.cliente;
        //Creo l'utente vero e proprio e lo salvo nel CSV
        Utente nuovoUtente = new Utente(nome, cognome, username, passwordCifrata, nascita, domicilio, ruolo);
        nuovoUtente.salvaSuFile();
        return true; 
    }

    /**
     * Visualizza le prenotazioni dell'utente in base al suo ruolo.
     * I clienti possono visualizzare le proprie prenotazioni, mentre i bigliettai possono cercare e visualizzare prenotazioni specifiche.
     * I proiezionisti non hanno accesso alla gestione delle prenotazioni.
     * @param scanner Lo scanner per leggere l'input dell'utente.
     * @param utente L'oggetto Utente che rappresenta l'utente attualmente loggato.
     * @param listaPrenotazioni La lista delle prenotazioni disponibili nel sistema.
     */
    public static void visualizzaPrenotazione(Scanner scanner, Utente utente, List<Prenotazione> listaPrenotazioni) 
    {
        Ruolo ruolo = utente.getRuolo();

        switch(ruolo) 
        {
            case cliente:
                List<Prenotazione> risultatoRicerca = new ArrayList<>();

                if(utente.getNome() != null && utente.getCognome() != null) 
                {
                    String infoCliente = utente.getNome().trim() + " " + utente.getCognome().trim();
                    
                    for(Prenotazione prenotazione: listaPrenotazioni) 
                    {
                        String nomeCognomeCliente = prenotazione.getNomeCliente().trim() + " " + prenotazione.getCognomeCliente().trim();
                        
                        if(nomeCognomeCliente.equalsIgnoreCase(infoCliente)) 
                        {
                            risultatoRicerca.add(prenotazione);
                        }
                    }

                    if(risultatoRicerca.isEmpty()) 
                    {
                        System.out.println("Nessuna prenotazione trovata" );
                    }
                    else 
                    {
                        System.out.println(risultatoRicerca.size() + " prenotazioni trovate:");
                        
                        for(Prenotazione p: risultatoRicerca) 
                        {
                            System.out.println(p.toString());
                        }
                    }
                } break;

            case bigliettaio:
                // Viene evocato dopo aver usato il cercaPrenotazione dal bigliettaio, quindi alla fine è come il visualizzaProiezione dell'utente. Chiedi "Vuoi visualizzare una proiezione in particolare?" e metti codice e confronti
                boolean trovata = false;
                while(!trovata) 
                {
                    System.out.println("Inserisci il codice della prenotazione da visualizzare (o inserisci 'esci' per uscire): ");
                    String codiceUnivoco = scanner.nextLine().trim();
                    if(codiceUnivoco.equalsIgnoreCase("esci")) 
                    {
                        System.out.println("Operazione annullata");
                        break;
                    }
                    if(codiceUnivoco.isEmpty()) 
                    {
                        System.out.println("Il codice non può essere vuoto");
                        continue;
                    }

                    for(Prenotazione prenotazione: listaPrenotazioni) 
                    {
                        if(prenotazione.getCodicePrenotazione().equalsIgnoreCase(codiceUnivoco)) 
                        {
                            System.out.println(prenotazione);
                            trovata = true;
                            break;
                        }
                    }
                    if(!trovata) 
                    {
                        System.out.println("Errore: nessuna prenotazione trovata con il codice inserito");
                    }
                } break;

            case proiezionista:
                System.out.println("Accesso negato: la gestione e visualizzazione delle prenotazioni non è di competenaa del proiezionista");
                break;
            default:
                System.out.println("Ruolo non riconosciuto");
                break;
        }
    }

    @Override
    public String toString() 
    {
        return String.format("""
            
            --- SCHEDA UTENTE ---
            Nome: %s %s
            Username: %s
            Password: %s
            Nascita: %s
            Domicilio: %s
            Ruolo: %s""",
            nome, cognome, username, password, nascita, domicilio, ruolo);
    }    

}