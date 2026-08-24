import java.util.*;
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
        
        if() //codice per confrontare dati csv e dati appena inseriti
        {
        System.out.println("\nLogin effettuato con successo! Benvenuto " + /*getNome() */);
        } 
        else 
        {
            System.out.println("Credenziali errate!");
        }
    }


    //Questo metodo e' lungo e potrebbe essere ottimizzato perche' ripete diverse righe piu' volte. Manca prima di questi la distinzione tra guest e cliente registrato
    //Da sistemare il filtraggio per costo e data, non usa ancora il range.
    public static void cercaProiezione(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        //Ottengo i criteri di ricerca dall'utente; imposto valori di default per capire se non vengono impostati dall'utente
        String titolo = null;
        String genere = null;
        String regista = null;
        int anno = -1;
        String dataOrario = null;
        int durata = -1;
        int etaMinima = -1; 
        double costo = -1.0;

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
            System.out.print("Vuoi cercare una proiezione per anno? (s/n): ");
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
            boolean inputValido = false;

            while(!inputValido) 
            {
                System.out.print("Anno del film: ");

                try 
                {
                    anno = Integer.parseInt(scanner.nextLine().trim());
                    inputValido = true;
                }
                catch (NumberFormatException e) 
                {
                    System.out.println("Input non valido. Inserisci un numero intero per l'anno.");
                }
            }
        }

        while (true) 
        {
            System.out.print("Vuoi cercare una proiezione per data e orario? (s/n): ");
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
            System.out.print("Data e orario della proiezione (formato: gg/mm/aaaa hh:mm): ");
            dataOrario = scanner.nextLine().trim();
        }

        while (true) 
        {
            System.out.print("Vuoi cercare una proiezione per durata? (s/n): ");
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
            boolean inputValido = false;

            while(!inputValido) 
            {
                System.out.print("Durata del film (in minuti): ");

                try 
                {
                    durata = Integer.parseInt(scanner.nextLine().trim());
                    inputValido = true;
                }
                catch (NumberFormatException e) 
                {
                    System.out.println("Input non valido. Inserisci un numero intero per la durata.");
                }
            }
        }

        while (true) 
        {
            System.out.print("Vuoi cercare una proiezione per eta minima? (s/n): ");
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
            boolean inputValido = false;

            while(!inputValido) 
            {
                System.out.print("Eta minima per il film: ");

                try
                {
                    etaMinima = Integer.parseInt(scanner.nextLine().trim());
                    inputValido = true;
                }
                catch (NumberFormatException e) 
                {
                    System.out.println("Input non valido. Inserisci un numero intero per l'eta' minima.");
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
            boolean inputValido = false;

            while(!inputValido) 
            {
                System.out.print("Costo del biglietto: ");

                try
                {
                    costo = Double.parseDouble(scanner.nextLine().trim());
                    inputValido = true;
                }
                catch (NumberFormatException e) 
                {
                    System.out.println("Input non valido. Inserisci un numero per il costo.");
                }
            }
        }

        System.out.print("Filtri impostati. Inizio la ricerca...");

       //Prendo la listaProiezioni generata all'avvio del programma e la filtro in base ai criteri impostati dall'utente, poi stampo i risultati della ricerca.
       List<Proiezione> risultatoRicerca = new ArrayList<>();

        for(Proiezione proiezione : listaProiezioni) 
        {
            boolean corrisponde = true;
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
            if(anno != -1 && proiezione.getAnno() != anno) 
            {
                corrisponde = false;
            }
            if(dataOrario != null && !proiezione.getDataOrario().toLowerCase().contains(dataOrario.toLowerCase()))
            {
                corrisponde = false;
            }   
            if(durata != -1 && proiezione.getDurata() != durata) 
            {
                corrisponde = false;
            }
            if(etaMinima != -1 && proiezione.getEtaMinima() != etaMinima) 
            {
                corrisponde = false;
            }
            if(costo != -1.0 && proiezione.getCosto() != costo) 
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

    public static void modificaPrenotazione(Scanner scanner, List<Proiezione> risultatoRicerca, List<Prenotazione> listaPrenotazioni) 
    {
        Boolean entrataValida = false;
        while(!entrataValida) 
        {
            System.out.println("Inserire il codice della prenotazione da modificare (o 'esci' per annullare): ");
            String codiceUnivoco = scanner.nextLine().trim();
            for(Prenotazione prenotazione: listaPrenotazioni) 
            {
                    if(codiceUnivoco.equals(prenotazione.getCodicePrenotazione())) 
                        {
                            entrataValida = true;
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime dataOggi = LocalDateTime.now();
                            LocalDateTime dataPrenotazione = LocalDateTime.parse(prenotazione.getDataOraProiezione(), formatter);
                            if(dataPrenotazione.isAfter(dataOggi)) 
                            {
                                System.out.println("Prenotazione trovata. Scegli la data a cui desideri spostarla tra le seguenti:");
                                for(Proiezione proiezione: risultatoRicerca) 
                                {
                                    if(proiezione.getTitolo().equalsIgnoreCase(prenotazione.getTitoloFilm())) 
                                    {
                                        //prendo orario proiezione
                                        //converto in datatimeobject 
                                        //confronto e se dataProiezione > dataOggi restituisco
                                        //faccio digitare data e orario all'utente o scegliere in qualche modo e aggiorno
                                        //   
                                    }
                                }

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

            if(codiceUnivoco.equalsIgnoreCase("esci")) 
                {
                    break;
                }

            for(Prenotazione p: listaPrenotazioni) 
            {
                    if(codiceUnivoco.equals(p.getCodicePrenotazione())) 
                        {
                            entrataValida = true;
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //MM e HH perchè in minuscolo indicherebbero minuti e formato a 12 ore con AM e PM
                            LocalDateTime dataOggi = LocalDateTime.now();
                            LocalDateTime dataPrenotazione = LocalDateTime.parse(p.getDataOraProiezione(), formatter);
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