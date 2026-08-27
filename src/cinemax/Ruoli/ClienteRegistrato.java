import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClienteRegistrato extends Utente 
{
    //Costruttore
    public ClienteRegistrato(String nome, String cognome, String username, String password, String nascita, String domicilio) 
    {
        super(nome, cognome, username, password, nascita, domicilio, Ruolo.cliente);
    }

    public static ClienteRegistrato eseguiLogin(Scanner scanner)
    {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        return eseguiLogin(username, password);
    }
        
    public static ClienteRegistrato eseguiLogin(String username, String password) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileUtenti))) 
        {
            br.readLine(); //saltare intestazione
            String riga;

            while ((riga = br.readLine()) != null) 
            {
                if (riga.trim().isEmpty()) continue;

                String[] campi = riga.split(",", -1);

                // Bastano >= 6 campi per accedere agli indici da 0 a 5 in sicurezza
                if (campi.length >= 6 && campi[2].trim().equals(username) && campi[3].trim().equals(password)) 
                {
                    System.out.println("\nAccesso consentito! Benvenuto/a nel sistema.");
                    return new ClienteRegistrato(
                        campi[0].trim(), 
                        campi[1].trim(), 
                        campi[2].trim(), 
                        campi[3].trim(), 
                        campi[4].trim(), 
                        campi[5].trim()
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

    
    public static List<Proiezione> cercaProiezione(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        //Ottengo i criteri di ricerca dall'utente; imposto valori di default per capire se non vengono impostati dall'utente
        String titolo = null;
        String genere = null;
        String regista = null;
        LocalDate dataInizio = null;
        LocalDate dataFine = null;
        double costoMin = 0.0;
        double costoMax = Double.MAX_VALUE;

        String risposta = "";

        while(true) 
        {
            System.out.print("Vuoi cercare una proiezione per titolo? (s/n): ");
            risposta = scanner.nextLine().trim().toLowerCase();
            if (risposta.equals("s") || risposta.equals("n")) 
            {
                break;
            }
            else 
            {
                System.out.println("Risposta non valida. Inserisci 's' per si o 'n' per no.");
            }
        }
        if(risposta.equals("s")) 
        {
            System.out.print("Titolo del film: ");
            titolo = scanner.nextLine().trim();
        }

        while (true) 
        {
            System.out.print("Vuoi cercare una proiezione per genere? (s/n): ");
            risposta = scanner.nextLine().trim().toLowerCase();

            if (risposta.equals("s") || risposta.equals("n")) 
            {
                break;
            }
            else 
            {
                System.out.println("Risposta non valida. Inserisci 's' per si o 'n' per no.");
            }
        }

        if(risposta.equals("s")) 
        {
            System.out.print("Genere del film: ");
            genere = scanner.nextLine().trim();
        }
    
        while (true) 
        {
            System.out.print("Vuoi cercare una proiezione per regista? (s/n): ");
            risposta = scanner.nextLine().trim().toLowerCase();

            if (risposta.equals("s") || risposta.equals("n")) 
            {
                break;
            }
            else 
            {
                System.out.println("Risposta non valida. Inserisci 's' per si o 'n' per no.");
            }
        }

        if(risposta.equals("s")) 
        {
            System.out.print("Regista del film: ");
            regista = scanner.nextLine().trim();
        }

        while (true) 
        {
            System.out.print("Vuoi cercare una proiezione per data? (s/n): ");
            risposta = scanner.nextLine().trim().toLowerCase();

            if (risposta.equals("s") || risposta.equals("n")) 
            {
                break;
            }
            else 
            {
                System.out.println("Risposta non valida. Inserisci 's' per si o 'n' per no.");
            }
        }

        if (risposta.equals("s"))
        {
            DateTimeFormatter formatInput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("Puoi inserire un intervallo temporale, altrimenti premere invio");
            boolean inizioValido = false;
            while(!inizioValido) 
            {
                System.out.print("Data iniziale (gg/mm/aaaa)");
                String inizio = scanner.nextLine().trim();
                if(inizio.isEmpty()) 
                {
                    inizioValido = true;
                }
                else 
                {
                    try 
                    {
                        dataInizio = LocalDate.parse(inizio, formatInput);
                        inizioValido = true;
                    }
                    catch (Exception e) 
                    {
                        System.out.println("Errore: usa il formato gg/mm/aaaa");
                    }
                }
            }

            boolean fineValido = false;
            while(!fineValido) 
            {
                System.out.print("Data finale (gg/mm/aaaa)");
                String fine = scanner.nextLine().trim();
                if(fine.isEmpty()) 
                {
                    fineValido = true;
                }
                else 
                {
                    try 
                    {
                        dataFine = LocalDate.parse(fine, formatInput);
                        if(dataInizio != null && dataFine.isBefore(dataInizio)) 
                        {
                            System.out.println("Errore: la data finale non può precedere la data iniziale");
                        }
                        else 
                        {
                            fineValido = true;
                        }
                    }
                    catch (Exception e) 
                    {
                        System.out.println("Errore: usa il formato gg/mm/aaaa");
                    }
                }
            }
        }

        while (true) 
        {
            System.out.print("Vuoi cercare una proiezione per costo del biglietto? (s/n): ");
            risposta = scanner.nextLine().trim().toLowerCase();

            if (risposta.equals("s") || risposta.equals("n")) 
            {
                break;
            }
            else 
            {
                System.out.println("Risposta non valida. Inserisci 's' per si o 'n' per no.");
            }
        }

        if(risposta.equals("s")) 
        {
            boolean inputValidoMin = false;

            while(!inputValidoMin) 
            {
                System.out.print("Inserisci il costo minimo (es. 8, oppure 0 per non impostare un prezzo minimo): ");

                try
                {
                    costoMin = Double.parseDouble(scanner.nextLine().trim());
                    if(costoMin >= 0) 
                    {
                        inputValidoMin = true;
                    }
                    else 
                    {
                        System.out.println("Errore: il costo non può essere negativo");
                    }
                }
                catch (NumberFormatException e) 
                {
                    System.out.println("Input non valido. Inserisci un numero valido (usa il punto per i decimali, es. '8.50').");
                }
            }

            boolean inputValidoMax = false;

            while(!inputValidoMax) 
            {
                System.out.print("Inserisci il costo massimo (es. 15, oppure 1000 per non impostare un prezzo massimo): ");
                try
                {
                    costoMax = Double.parseDouble(scanner.nextLine().trim());
                    if(costoMax >= costoMin) 
                    {
                        inputValidoMax = true;
                    }
                    else 
                    {
                        System.out.println("Errore: il costo massimo non può essere inferiore al costo minimo (" + costoMin + ").");
                    }
                }
                catch (NumberFormatException e) 
                {
                    System.out.println("Input non valido. Inserisci un numero valido (usa il punto per i decimali, es. '15.00').");
                }
            }
        }

        System.out.print("Filtri impostati. Inizio la ricerca...");
        
        LocalDateTime dataOggi = LocalDateTime.now();

        //Prendo la listaProiezioni generata all'avvio del programma e la filtro in base ai criteri impostati dall'utente, poi stampo i risultati della ricerca.
        List<Proiezione> risultatoRicerca = new ArrayList<>();
    

        for(Proiezione proiezione : listaProiezioni) 
        {
            boolean corrisponde = true;
            // Escludo le proiezioni già avvenute, non avrebbe senso mostrarle
            DateTimeFormatter formatCSV = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            LocalDateTime orarioProiezione = LocalDateTime.parse(proiezione.getDataOrario(), formatCSV);

            if(orarioProiezione.isBefore(dataOggi)) continue; //Salta direttamente al prossimo se già avvenuto
            //Filtro tutto per esclusione, molto più semplice logicamente e da implementare rispetto al filtrare per inclusione.

            if (titolo != null && !proiezione.getTitolo().toLowerCase().contains(titolo.toLowerCase())) 
            {
                corrisponde = false;
            } 

            if(genere != null && !proiezione.getGenere().toLowerCase().contains(genere.toLowerCase())) 
            {
                corrisponde = false;
            }

            if(regista != null && !proiezione.getRegista().toLowerCase().contains(regista.toLowerCase())) 
            {
                corrisponde = false;
            }
            
            if(dataInizio != null || dataFine != null)
            {
                LocalDate dataProiezione = LocalDate.parse(proiezione.getDataOrario().substring(0, 10));

                if(dataInizio != null && dataProiezione.isBefore(dataInizio)) corrisponde = false;

                if(dataFine != null && dataProiezione.isAfter(dataFine)) corrisponde = false;
            }   

            if(proiezione.getCosto() < costoMin || proiezione.getCosto() > costoMax) 
            {
                corrisponde = false;
            }
            if (corrisponde) 
            {
                risultatoRicerca.add(proiezione);
            }
        }

        if (risultatoRicerca.isEmpty()) 
        {
            System.out.println("\nNessuna proiezione trovata con questi filtri.");
        }   
        else 
        {
            Set<String> titoliTrovati = new HashSet<>();
            for (Proiezione p : risultatoRicerca) 
            {
                titoliTrovati.add(p.getTitolo());              
            }        
            System.out.println("Trovate " + risultatoRicerca.size() + " proiezioni distribuite su " + titoliTrovati.size() + " film:");
            for(String t: titoliTrovati) 
            {
                System.out.println("- " + t);
            }
        }
        System.out.println("--------------------------------"); //Per staccare quando verra' chiamato un altro metodo 
        return risultatoRicerca;
    }

    public static void visualizzaProiezione(Scanner scanner, List<Proiezione> risultatoRicerca) 
    {
        System.out.print("Inserisci il titolo della proiezione da visualizzare: ");
        String titolo = scanner.nextLine().trim();
        boolean trovato = false;

        for (Proiezione proiezione : risultatoRicerca) 
        {
            if (proiezione.getTitolo().equalsIgnoreCase(titolo)) 
            {
                System.out.println(proiezione.toString());
                trovato = true;
            }
        }
        if (!trovato) 
        {
            System.out.println("Proiezione non trovata.");
        }
    }

 public static void creaPrenotazione(Scanner scanner, List<Proiezione> risultatoRicerca, Utente utente, List<Prenotazione> listaPrenotazioni) 
    {
        System.out.println("Inserisci data e ora della proiezione da prenotare (formato: gg/mm/aaaa hh:mm): ");
        String dataOra = scanner.nextLine().trim();
        boolean proiezioneTrovata = false;
        for(Proiezione p: risultatoRicerca) 
        {
            if(p.getDataOrario().equals(dataOra)) 
            {
                proiezioneTrovata = true;
                int numeroBiglietti = 0;
                boolean inputValido = false;
                while(!inputValido) 
                { 
                    System.out.println("Quanti biglietti desidera acquistare?");
                    try
                    {
                        numeroBiglietti = Integer.parseInt(scanner.nextLine().trim());
                        if(numeroBiglietti > 0) 
                        {
                            inputValido = true;
                        } 
                        else 
                        {
                            System.out.println("Inserisci un numero maggiore di zero");
                        }
                    } 
                    catch(NumberFormatException e)
                    {
                        System.out.println("Errore: devi inserire un numero intero");
                    }
                }
                if(numeroBiglietti <= p.getPostiDisponibili()) 
                {
                    p.decrementaPosti(numeroBiglietti);
                    //genera codice univoco
                    String codiceUnivoco = generaCodiceUnivoco();
                    //crea oggetto prenotazione e aggiungilo alla lista prenotazioni globale 
                    Prenotazione nuovaPrenotazione = new Prenotazione(codiceUnivoco, utente.getNome(), utente.getCognome(), p.getTitolo(), p.getDataOrario(), p.getCosto(), (p.getCosto() * numeroBiglietti), numeroBiglietti);
                    listaPrenotazioni.add(nuovaPrenotazione); 
                    System.out.println("Prenotazione avvenuta con successo!");
                    System.out.println(nuovaPrenotazione.toString());
                }
                else 
                {
                    System.out.println("Prenotazione non riuscita: posti liberi non sufficienti");
                } break;
            }
        }
        if (!proiezioneTrovata)         
        {
            System.out.println("Nessuna proiezione trovata a questo orario");
        }
    }

    public static void modificaPrenotazione(Scanner scanner, List<Proiezione> listaProiezioni, List<Prenotazione> listaPrenotazioni) 
    {
        Boolean entrataValida = false;
        while(!entrataValida) 
        {
            System.out.println("Inserire il codice della prenotazione da modificare (o 'esci' per annullare): ");
            String codiceUnivoco = scanner.nextLine().trim();

            if(codiceUnivoco.equalsIgnoreCase("esci")) break;

            for(Prenotazione prenotazione: listaPrenotazioni) 
            {
                    if(codiceUnivoco.equals(prenotazione.getCodicePrenotazione())) 
                        {
                            entrataValida = true;
                            DateTimeFormatter formatter =   DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime dataOggi = LocalDateTime.now();
                            LocalDateTime dataPrenotazione = LocalDateTime.parse(prenotazione.getDataOraPrenotazione(), formatter);
                            if(dataPrenotazione.isAfter(dataOggi)) 
                            {
                                System.out.println("Prenotazione trovata");
                                for(Proiezione proiezione: listaProiezioni) 
                                {
                                    if(proiezione.getTitolo().trim().equalsIgnoreCase(prenotazione.getTitoloFilm().trim())) 
                                    {
                                        LocalDateTime opzioni = LocalDateTime.parse(proiezione.getDataOrario(), formatter); 
                                        if(opzioni.isAfter(dataOggi) && !proiezione.getDataOrario().equals(prenotazione.getDataOraPrenotazione())) 
                                        {
                                            System.out.println("\n" + proiezione.getDataOrario());
                                        }
                                    }
                                }
                                System.out.println("Inserire la data in cui desideri spostare la preontazione (yyyy-MM-dd HH:mm:ss): ");
                                LocalDateTime dataNuova = null;
                                String inputUtente = "";
                                boolean formatoValido = false;
                                while(!formatoValido) 
                                    {
                                    inputUtente = scanner.nextLine().trim();
                                    try 
                                    {
                                        dataNuova = LocalDateTime.parse(inputUtente, formatter);
                                        formatoValido = true;
                                    }
                                    catch (Exception e) 
                                    {
                                        System.out.println("Errore di formato: inserisci la data esattamente come richiesto (yyyy-MM-dd HH:mm:ss)");
                                    }
                                }
                                
                                boolean nuovaTrovata = false;
                                int biglietti = prenotazione.getNumeroBiglietti();
                                for(Proiezione nuovaProiezione: listaProiezioni) 
                                {
                                    if(nuovaProiezione.getTitolo().trim().equalsIgnoreCase(prenotazione.getTitoloFilm().trim()) && LocalDateTime.parse(nuovaProiezione.getDataOrario(), formatter).equals(dataNuova)) 
                                    {
                                        nuovaTrovata = true;
                                        if(nuovaProiezione.getPostiDisponibili() >= biglietti) 
                                        {
                                            nuovaProiezione.decrementaPosti(biglietti);
                                            
                                            for(Proiezione vecchiaProiezione: listaProiezioni) 
                                            {
                                                if(vecchiaProiezione.getTitolo().trim().equalsIgnoreCase(prenotazione.getTitoloFilm().trim()) && vecchiaProiezione.getDataOrario().equals(prenotazione.getDataOraPrenotazione())) 
                                                {
                                                    vecchiaProiezione.setPostiDisponibili(vecchiaProiezione.getPostiDisponibili() + biglietti);
                                                    break;
                                                }
                                            }
                                            prenotazione.setDataOraPrenotazione(inputUtente); //Passo inputUtente perchè nel blocco precedente col formatter ho già controllato e imposto che sia nel formato corretto, così posso direttamente aggiornarlo 
                                            System.out.println("Prenotazione modificata con successo");
                                        }
                                        else 
                                        {
                                            System.out.println("Errore: posti insufficienti nella nuova data");
                                        } break;
                                    }
                                    
                                }
                                if(!nuovaTrovata) 
                                {
                                    System.out.println("La data inserita non valida o non corrispondente alkle opzioni");
                                } break;
                            }    
                            else 
                            {
                                System.out.println("Non puoi modificare una prenotazione passata. Riprova");
                            }
                        }
            }
            if(!entrataValida) 
            {
                System.out.println("Il codice prenotazione inserito non è valido");
            }
        }
    }

    public static void eliminaPrenotazione(Scanner scanner, List<Prenotazione> listaPrenotazioni) 
    {   
        Boolean entrataValida = false;
        while(!entrataValida) {
            System.out.println("Inserire il codice della prenotazione da eliminare (o 'esci' per annullare): ");
            String codiceUnivoco = scanner.nextLine().trim();

            if(codiceUnivoco.equalsIgnoreCase("esci")) break;

            for(Prenotazione p: listaPrenotazioni) 
            {
                    if(codiceUnivoco.equals(p.getCodicePrenotazione())) 
                        {
                            entrataValida = true;
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //MM e HH perchè in minuscolo indicherebbero minuti e formato a 12 ore con AM e PM
                            LocalDateTime dataOggi = LocalDateTime.now();
                            LocalDateTime dataPrenotazione = LocalDateTime.parse(p.getDataOraPrenotazione(), formatter);
                            if(dataPrenotazione.isBefore(dataOggi)) 
                            {
                                listaPrenotazioni.remove(p);
                                System.out.println("Prenotazione rimossa con successo");
                            }
                            else
                            {
                                System.out.println("Impossibile rimuovere la prenotazione: la proiezione deve ancora avvenire");
                            }
                            break;
                        }
            }
            if(!entrataValida) 
            {
                System.out.println("Il codice prenotazione inserito non è valido. Riprova");
            }
        }
    }

    public static String generaCodiceUnivoco() 
    {
        String caratteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codice = new StringBuilder(); //Builder perchè modificabile al contrario delle stringhe normali
        java.util.Random random = new java.util.Random();
        for(int i = 0; i < 8; i++) 
        {
            int indice = random.nextInt(caratteri.length());
            char carattere = caratteri.charAt(indice);
            codice.append(carattere);
        }
        return codice.toString();
    } 
}