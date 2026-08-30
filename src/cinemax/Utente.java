import java.io.*;
import java.util.*;


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

    //Construtore
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

    //Metodi
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

        
    public static Utente eseguiLogin(String username, String passwordChiara) 
    {
        String passwordCifrata = PasswordUtils.cifrapassword(passwordChiara);
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

//metodi getter per fare accedere/confrontare negli altri file (es. per sicurezza e login)
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

}