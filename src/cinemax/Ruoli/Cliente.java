import java.util.*;

public class Cliente extends Utente 
{
    //Costruttore
    public Cliente(String nome, String cognome, String username, String password, String nascita, String domicilio) 
    {
        super(nome, cognome, username, password, nascita, domicilio, Ruolo.cliente);
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

        while (true) 
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
            System.out.println("\n" + risultatoRicerca.size() + " proiezioni trovate:");

            for (Proiezione p : risultatoRicerca) 
            {
                System.out.println(p.toString());              
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
                break;
            }
        }
        if (!trovato) 
        {
            System.out.println("Proiezione non trovata.");
        }
    }

    public static void visualizzaPrenotazioni(Scanner scanner) 
    {
        // Implementazione del metodo per visualizzare le prenotazioni
    }

    public static void creaPrenotazione() 
    {
        // Implementazione del metodo per inserire una prenotazione
    }

    public static void modificaPrenotazione(Scanner scanner) 
    {
        // Implementazione del metodo per modificare una prenotazione
    }
}